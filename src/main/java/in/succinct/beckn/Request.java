package in.succinct.beckn;

import com.venky.core.collections.IgnoreCaseMap;
import com.venky.core.collections.SequenceMap;
import com.venky.core.security.Crypt;
import com.venky.core.util.ObjectHolder;
import com.venky.core.util.ObjectUtil;
import com.venky.extension.Registry;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCXDHPublicKey;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request extends BecknObject {
    public Request() {
        this(new JSONObject());
    }
    public Request(String payLoad){
        super(payLoad);
    }
    public Request(JSONObject request){
        super(request);
    }

    public Context getContext() {
        return get(Context.class, "context");
    }
    public void setContext(Context context) {
        set("context",context.getInner());
    }


    public Message getMessage() {
        return get(Message.class, "message");
    }
    public void setMessage(Message message) {
        set("message",message.getInner());
    }

    public Error getError(){
        return get(Error.class,"error");
    }
    public void setError(Error error){
        set("error",error.getInner());
    }

    public RatingCategories getRatingCategories(){
        return get(RatingCategories.class, "rating_categories");
    }
    public void setRatingCategories(RatingCategories rating_categories){
        set("rating_categories",rating_categories);
    }

    public FeedbackCategories getFeedbackCategories(){
        return get(FeedbackCategories.class, "feedback_categories");
    }
    public void setFeedbackCategories(FeedbackCategories feedback_categories){
        set("feedback_categories",feedback_categories);
    }

    public CancellationReasons getCancellationReasons(){
        return get(CancellationReasons.class, "cancellation_reasons");
    }
    public void setCancellationReasons(CancellationReasons cancellation_reasons){
        set("cancellation_reasons",cancellation_reasons);
    }

    public ReturnReasons getReturnReasons(){
        return get(ReturnReasons.class, "return_reasons");
    }
    public void setReturnReasons(ReturnReasons return_reasons){
        set("return_reasons",return_reasons);
    }



    public String getSigningString(long created_at, long expires_at) {
        StringBuilder builder = new StringBuilder();
        builder.append("(created): ").append(created_at);
        builder.append("\n(expires): ").append(expires_at);
        builder.append("\n").append("digest: BLAKE-512=").append(hash());
        return builder.toString();
    }

    public boolean verifySignature(String header,Map<String,String> httpRequestHeaders){
        return verifySignature(header,httpRequestHeaders,true);
    }
    public boolean verifySignature(Map<String,String> params, boolean headerMandatory){
        if (params.isEmpty()) {
            return !headerMandatory;
        }

        String signature = params.get("signature");
        String created = params.get("created");
        String expires = params.get("expires");
        String keyId = params.get("keyId");
        String subscriberId = params.get("subscriber_id");
        String pub_key_id  = params.get("pub_key_id");


        String signingString = getSigningString(Long.parseLong(created),Long.parseLong(expires));
        return verifySignature(signature,signingString,getPublicKey(subscriberId,pub_key_id));
    }
    public boolean verifySignature(String header,Map<String,String> httpRequestHeaders, boolean headerMandatory){
        Map<String,String> params = extractAuthorizationParams(header,httpRequestHeaders);
        return verifySignature(params,headerMandatory);
    }

    public static String getPublicKey(String subscriber_id, String keyId ) {
        ObjectHolder<String> publicKeyHolder = new ObjectHolder<>(null);
        Registry.instance().callExtensions("beckn.public.key.get",subscriber_id,keyId,publicKeyHolder);
        return publicKeyHolder.get();
    }
    public static String getPrivateKey(String subscriber_id, String keyId) {
        ObjectHolder<String> privateKeyHolder = new ObjectHolder<>(null);
        Registry.instance().callExtensions("beckn.private.key.get",subscriber_id,keyId,privateKeyHolder);
        return privateKeyHolder.get();
    }

    public String generateAuthorizationHeader(String  subscriberId, String pub_key_id){
        Map<String,String> map = generateAuthorizationParams(subscriberId,pub_key_id);
        StringBuilder auth = new StringBuilder();
        map.forEach((k,v)-> {
            if (auth.length() == 0){
                auth.append("Signature ");
            }else {
                auth.append(",");
            }
            auth.append(k).append("=\"").append(v).append("\"");
        });
        return auth.toString();
    }

    public Map<String, String> extractAuthorizationParams(String header, Map<String, String> httpRequestHeaders) {
        Map<String,String> params = new IgnoreCaseMap<>();
        if (!httpRequestHeaders.containsKey(header)){
            return params;
        }
        String authorization = httpRequestHeaders.get(header).trim();
        String signatureToken  = "Signature ";

        if (authorization.startsWith(signatureToken)){
            authorization = authorization.substring(signatureToken.length());
        }

        Matcher matcher = Pattern.compile("([A-z]+)(=)[\"]*([^\",]*)[\"]*[, ]*").matcher(authorization);
        matcher.results().forEach(mr->{
            System.out.println(mr.group());
            params.put(mr.group(1),mr.group(3));
        });

        if (!params.isEmpty()) {
            String keyId = params.get("keyId");
            if (!ObjectUtil.isVoid(keyId)){
                StringTokenizer keyTokenizer = new StringTokenizer(keyId, "|");
                String subscriberId = keyTokenizer.nextToken();
                String pub_key_id = keyTokenizer.nextToken();
                params.put("subscriber_id",subscriberId);
                params.put("pub_key_id",pub_key_id);
            }
        }

        return params;
    }
    public Map<String,String> generateAuthorizationParams(String subscriberId,String pub_key_id){
        Map<String,String> map = new SequenceMap<>();
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(subscriberId).append('|')
                .append(pub_key_id).append('|').append("ed25519");

        map.put("keyId",keyBuilder.toString());
        map.put("algorithm","ed25519");
        long created_at = System.currentTimeMillis()/1000L;
        long expires_at = created_at + (getContext() == null ? 10 : getContext().getTtl());
        map.put("created",Long.toString(created_at));
        map.put("expires",Long.toString(expires_at));
        map.put("headers","(created) (expires) digest");
        //map.put("signature",generateSignature(generateBlakeHash(getSigningString(created_at,expires_at)),getPrivateKey(subscriberId,pub_key_id)));
        map.put("signature",generateSignature(getSigningString(created_at,expires_at),getPrivateKey(subscriberId,pub_key_id)));
        return map;
    }

    private BecknObject extendedAttributes = new BecknObject();
    public BecknObject getExtendedAttributes(){
        return extendedAttributes;
    }


    public static String SIGNATURE_ALGO = EdDSAParameterSpec.Ed25519;
    public static int SIGNATURE_ALGO_KEY_LENGTH = 256;

    public static String ENCRYPTION_ALGO = XDHParameterSpec.X25519;
    public static int ENCRYPTION_ALGO_KEY_LENGTH = 256;

    public static String generateSignature(String req, String privateKey) {
        PrivateKey key = Crypt.getInstance().getPrivateKey(SIGNATURE_ALGO,privateKey);
        return Crypt.getInstance().generateSignature(req,SIGNATURE_ALGO,key);
    }


    public static boolean verifySignature(String sign, String requestData, String b64PublicKey) {
        PublicKey key = getSigningPublicKey(b64PublicKey);
        return Crypt.getInstance().verifySignature(requestData,sign,SIGNATURE_ALGO,key);
    }

    public static String getSubscriberId(Map<String,String> authParams){
        if (!authParams.isEmpty()){
            String keyId = authParams.get("keyId");
            StringTokenizer keyTokenizer = new StringTokenizer(keyId,"|");
            String subscriberId = keyTokenizer.nextToken();
            return subscriberId;
        }
        return null;
    }

    public static PublicKey getSigningPublicKey(String keyFromRegistry){
        try {
            return Crypt.getInstance().getPublicKey(Request.SIGNATURE_ALGO, keyFromRegistry);
        }catch (Exception ex){
            try {
                byte[] bcBytes = Base64.getDecoder().decode(keyFromRegistry);
                byte[] jceBytes = new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519), bcBytes).getEncoded();
                String pemKey = Base64.getEncoder().encodeToString(jceBytes);
                return Crypt.getInstance().getPublicKey(Request.SIGNATURE_ALGO,pemKey);
            }catch (Exception jceEx){
                return null;
            }
        }
    }
    public static PublicKey getEncryptionPublicKey(String keyFromRegistry){
        try {
            return Crypt.getInstance().getPublicKey(Request.ENCRYPTION_ALGO, keyFromRegistry);
        }catch (Exception ex){
            try {
                byte[] bcBytes = Base64.getDecoder().decode(keyFromRegistry);
                byte[] jceBytes = new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X25519), bcBytes).getEncoded();
                String pemKey = Base64.getEncoder().encodeToString(jceBytes);
                return Crypt.getInstance().getPublicKey(Request.ENCRYPTION_ALGO,pemKey);
            }catch (Exception jceEx){
                return null;
            }
        }
    }

    public static String getRawSigningKey(PublicKey publicKey){
        try {
            BCEdDSAPublicKey k = (BCEdDSAPublicKey) publicKey;
            Field f = k.getClass().getDeclaredField("eddsaPublicKey");
            f.setAccessible(true); //BC Desnot expose this hence this reflection stuff.
            Ed25519PublicKeyParameters publicKeyParameters = (Ed25519PublicKeyParameters) f.get(k);
            return Base64.getEncoder().encodeToString(publicKeyParameters.getEncoded());
        }catch (Exception ex){
            throw new RuntimeException("Unknown Key format");
        }
    }
    public static String getRawEncryptionKey(PublicKey publicKey){
        try {
            BCXDHPublicKey k = (BCXDHPublicKey) publicKey;
            Field f = k.getClass().getDeclaredField("xdhPublicKey");
            f.setAccessible(true); //BC Desnot expose this hence this reflection stuff.
            X25519PublicKeyParameters publicKeyParameters = (X25519PublicKeyParameters) f.get(k);
            return Base64.getEncoder().encodeToString(publicKeyParameters.getEncoded());
        }catch (Exception ex){
            throw new RuntimeException("Unknown Key format");
        }
    }


    public static String getPemSigningKey(String keyFromRegistry){
        return Base64.getEncoder().encodeToString(getSigningPublicKey(keyFromRegistry).getEncoded());
    }
    public static String getPemEncryptionKey(String keyFromRegistry){
        return Base64.getEncoder().encodeToString(getEncryptionPublicKey(keyFromRegistry).getEncoded());
    }
    public static String getRawSigningKey(String keyFromRegistry){
        return getRawSigningKey(getSigningPublicKey(keyFromRegistry));
    }
    public static String getRawEncryptionKey(String keyFromRegistry){
        return getRawEncryptionKey(getEncryptionPublicKey(keyFromRegistry));
    }

}
