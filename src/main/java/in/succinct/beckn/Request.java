package in.succinct.beckn;

import com.venky.core.collections.IgnoreCaseMap;
import com.venky.core.collections.SequenceMap;
import com.venky.core.security.Crypt;
import com.venky.core.util.ObjectHolder;
import com.venky.extension.Registry;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request extends BecknObject {
    public Request() {
        super();
    }
    public Request(String payLoad){
        super(payLoad);
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



    public String getSigningString(long created_at, long expires_at) {
        StringBuilder builder = new StringBuilder();
        builder.append("(created): ").append(created_at);
        builder.append("\n(expires): ").append(expires_at);
        builder.append("\n").append("digest: BLAKE-512=").append(hash());
        return builder.toString();
    }

    public boolean verifySignature(String header,Map<String,String> httpRequestHeaders){
        Map<String,String> params = extractAuthorizationParams(header,httpRequestHeaders);
        String signature = params.get("signature");
        String created = params.get("created");
        String expires = params.get("expires");
        String keyId = params.get("keyId");
        StringTokenizer keyTokenizer = new StringTokenizer(keyId,"|");
        String subscriberId = keyTokenizer.nextToken();
        String uniqueKeyId = keyTokenizer.nextToken();


        String signingString = getSigningString(Long.valueOf(created),Long.valueOf(expires));
        String hashedSigningString = generateBlakeHash(signingString);
        return verifySignature(signature,hashedSigningString,getPublicKey(subscriberId,uniqueKeyId));
    }

    public String getPublicKey(String subscriber_id, String keyId ) {
        ObjectHolder<String> publicKeyHolder = new ObjectHolder<>(null);
        Registry.instance().callExtensions("beckn.public.key.get",subscriber_id,keyId,publicKeyHolder);
        return publicKeyHolder.get();
    }
    public String getPrivateKey(String subscriber_id, String keyId) {
        ObjectHolder<String> privateKeyHolder = new ObjectHolder<>(null);
        Registry.instance().callExtensions("beckn.private.key.get",subscriber_id,keyId,privateKeyHolder);
        return privateKeyHolder.get();
    }

    public String generateAuthorizationHeader(String  subscriberId, String uniqueKeyId){
        Map<String,String> map = generateAuthorizationParams(subscriberId,uniqueKeyId);
        StringBuilder auth = new StringBuilder("Signature");
        map.forEach((k,v)-> auth.append(" ").append(k).append("=\"").append(v).append("\""));
        return auth.toString();
    }

    public Map<String, String> extractAuthorizationParams(String header, Map<String, String> httpRequestHeaders) {
        String authorization = httpRequestHeaders.get(header).trim();
        String signatureToken  = "Signature ";

        if (authorization.startsWith(signatureToken)){
            authorization = authorization.substring(signatureToken.length());
        }
        Matcher matcher = Pattern.compile("([A-z]+=\"[^\"]*\"[ ]*)").matcher(authorization);
        Pattern variableExtractor = Pattern.compile("([A-z]+)(=\")([^\"]*)(\")");
        Map<String,String> params = new IgnoreCaseMap<>();
        matcher.results().forEach(mr->{
            variableExtractor.matcher(mr.group()).results().forEach(r->{
                params.put(r.group(1),r.group(3));
            });
        });
        return params;
    }
    public Map<String,String> generateAuthorizationParams(String subscriberId,String uniqueKeyId){
        Map<String,String> map = new SequenceMap<>();
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(subscriberId).append('|')
                .append(uniqueKeyId).append('|').append("xed25519");

        map.put("keyId",keyBuilder.toString());
        map.put("algorithm","xed25519");
        long created_at = System.currentTimeMillis()/1000L;
        long expires_at = created_at + (getContext() == null ? 10 : getContext().getTtl());
        map.put("created",Long.toString(created_at));
        map.put("expires",Long.toString(expires_at));
        map.put("headers","(created) (expires) digest");
        map.put("signature",generateSignature(getSigningString(created_at,expires_at),getPrivateKey(subscriberId,uniqueKeyId)));
        return map;
    }


    public static String SIGNATURE_ALGO = EdDSAParameterSpec.Ed25519;
    public static String generateSignature(String req, String privateKey) {
        try {
            PrivateKey key = Crypt.getInstance().getPrivateKey(SIGNATURE_ALGO,privateKey);
            return Crypt.getInstance().generateSignature(req,SIGNATURE_ALGO,key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean verifySignature(String sign, String requestData, String b64PublicKey) {
        boolean isVerified = false;
        try {
            PublicKey key = Crypt.getInstance().getPublicKey(SIGNATURE_ALGO,b64PublicKey);
            return Crypt.getInstance().verifySignature(requestData,sign,SIGNATURE_ALGO,key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
