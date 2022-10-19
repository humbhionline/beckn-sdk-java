package in.succinct.beckn;

import com.venky.core.io.StringReader;
import com.venky.core.security.Crypt;
import com.venky.core.string.StringUtil;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator;
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.util.OpenSSHPublicKeyUtil;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.OpenSSHPublicKeySpec;
import org.bouncycastle.util.io.pem.PemReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SampleUseCase {
    @BeforeClass
    public static void setup(){
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null){
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    @Test
    public void timestampFormats(){
        for (DateFormat t : BecknObject.TIMESTAMP_FORMATS){
            System.out.println(t.getNumberFormat().toString() + "," + t.format(new Date()));
        }
    }

    @Test
    public void testBikry()throws  Exception{
        String payload = StringUtil.read(getClass().getResourceAsStream("/message.txt"));
        payload=payload.trim();
        Request request = new Request(payload);
        String privateKey = "MFECAQEwBQYDK2VwBCIEIGFb5nbGack1kjlYtbWel9LyxmXti1HGh9Oq6tFoAMZzgSEA1NOJ12n2O9pNTMjxQEOBy/Aqa4z8mfEHMpWQgV15gE4=";

        String signingString = request.getSigningString(1659888942,1659888952);
        System.out.println(signingString);
        PrivateKey privateKey1 = Crypt.getInstance().getPrivateKey(Request.SIGNATURE_ALGO,privateKey);

        //PublicKey key = Request.getSigningPublicKey("MCowBQYDK2VwAyEA1NOJ12n2O9pNTMjxQEOBy/Aqa4z8mfEHMpWQgV15gE4=");
        PublicKey key = Request.getSigningPublicKey("1NOJ12n2O9pNTMjxQEOBy/Aqa4z8mfEHMpWQgV15gE4=");

        String signature = "Nv8WY23Yv8tfSNBIVes3TMQeLW27CLIMvkEQrje9DfbR3P5B7SwTLWjr+LQfsWk0MuCAKF/EaLTp2B6SDW+vBw==";

        Assert.assertTrue(Crypt.getInstance().verifySignature(signingString,signature,Request.SIGNATURE_ALGO,key));
    }

    @Test
    public void testDateFormat() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = format.parse("2021-10-30T18:09:12.506+05:30");
        System.out.println(format.format(date));

    }

    @Test
    public void testIshitaPaytm(){
        String payload = "{\"context\":{\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"std:080\",\"action\":\"search\",\"core_version\":\"0.9.1\",\"bap_id\": \"ondc-staging.paytm.com\",\"bap_uri\": \"https://ondc-staging.paytm.com\",\"transaction_id\":\"txn_test\",\"message_id\":\"a2fe6d52-9fe4-4d1a-9d0b-dccb8b48522d\",\"timestamp\":\"2022-04-19T09:17:55.971Z\",\"ttl\":\"P1M\"},\"message\":{\"intent\":{\"fulfillment\":{\"start\":{\"location\":{\"gps\":\"10.108768, 76.347517\"}},\"end\":{\"location\":{\"gps\":\"10.102997, 76.353480\"}}}}}}";
        Request request = new Request(payload);
        System.out.println(request.hash());

    }
    @Test
    public void generateKeyPair() throws  Exception{
        for (String algo : new String[]{"Ed25519","X25519"}){
            KeyPair agreementKeyPair = KeyPairGenerator.getInstance(algo,BouncyCastleProvider.PROVIDER_NAME).generateKeyPair();
            String publicKey = Base64.getEncoder().encodeToString(agreementKeyPair.getPublic().getEncoded());
            String privateKey = Base64.getEncoder().encodeToString(agreementKeyPair.getPrivate().getEncoded());
            System.out.println("String B64"+algo+"PublicKey=\"" + publicKey +"\";");
            System.out.println("String B64"+algo+"PrivateKey=\"" + privateKey +"\";");
        }
        /*
            Example printed:
            String B64Ed25519PublicKey="MCowBQYDK2VwAyEAop6xU+hDn5C3oSKDkYO7uG9KVcdxm2f0mDrT6X/wuz0=";
            String B64Ed25519PrivateKey="MFECAQEwBQYDK2VwBCIEII2jkV3E8YiU78U2sjlLQSbECJvJcztdKfOIa0f4eH4bgSEAop6xU+hDn5C3oSKDkYO7uG9KVcdxm2f0mDrT6X/wuz0=";
            String B64X25519PublicKey="MCowBQYDK2VuAyEAsysTZ8NnOn86Sko0w5h8jexEkzJdbT4e/tjhHxAdIn0=";
            String B64X25519PrivateKey="MFECAQEwBQYDK2VuBCIEIFBYM49aQt9Yb3dFaUlmkHxE5z1ltk63/ueGAInNSvRFgSEAsysTZ8NnOn86Sko0w5h8jexEkzJdbT4e/tjhHxAdIn0=";
         */
    }

    @Test
    public void readPublicKeyFromB64String() throws Exception{
        String B64Ed25519PublicKey="MCowBQYDK2VwAyEAop6xU+hDn5C3oSKDkYO7uG9KVcdxm2f0mDrT6X/wuz0=";
        String B64Ed25519PrivateKey="MFECAQEwBQYDK2VwBCIEII2jkV3E8YiU78U2sjlLQSbECJvJcztdKfOIa0f4eH4bgSEAop6xU+hDn5C3oSKDkYO7uG9KVcdxm2f0mDrT6X/wuz0=";
        String B64X25519PublicKey="MCowBQYDK2VuAyEAsysTZ8NnOn86Sko0w5h8jexEkzJdbT4e/tjhHxAdIn0=";
        String B64X25519PrivateKey="MFECAQEwBQYDK2VuBCIEIFBYM49aQt9Yb3dFaUlmkHxE5z1ltk63/ueGAInNSvRFgSEAsysTZ8NnOn86Sko0w5h8jexEkzJdbT4e/tjhHxAdIn0=";

        byte[] jceBytes = Base64.getDecoder().decode(B64Ed25519PublicKey);
        PublicKey key = getPublicKey("Ed25519",jceBytes);
        Assert.assertNotNull(key);

        jceBytes = Base64.getDecoder().decode(B64X25519PublicKey);
        key = getPublicKey("X25519",jceBytes);
        Assert.assertNotNull(key);

        byte[] privateJceBytes = Base64.getDecoder().decode(B64Ed25519PrivateKey);
        PrivateKey privateKey = getPrivateKey("Ed25519",privateJceBytes);

        Assert.assertNotNull(privateKey);

        privateJceBytes = Base64.getDecoder().decode(B64X25519PrivateKey);
        privateKey = getPrivateKey("X25519",privateJceBytes);

        Assert.assertNotNull(privateKey);
    }

    @Test
    public void testEncryption() throws Exception{
        /*
        Two Parties generating key pair on their own servers.
         */
        KeyPair keyPairParty1 = KeyPairGenerator.getInstance("X25519", BouncyCastleProvider.PROVIDER_NAME).generateKeyPair();
        KeyPair keyPairParty2 = KeyPairGenerator.getInstance("X25519", BouncyCastleProvider.PROVIDER_NAME).generateKeyPair();

        KeyAgreement atServer1 = KeyAgreement.getInstance("X25519",BouncyCastleProvider.PROVIDER_NAME);
        atServer1.init(keyPairParty1.getPrivate()); // Server1 uses its private key to initialize the aggreement object
        atServer1.doPhase(keyPairParty2.getPublic(),true); //Uses Server2's ppublic Key
        SecretKey key1 = atServer1.generateSecret("TlsPremasterSecret"); //derive secret at server 1. "TlsPremasterSecret" is the algorithm for secret key. It is an aes key actually.

        // Let's see what happens at server 2
        KeyAgreement atServer2 = KeyAgreement.getInstance("X25519",BouncyCastleProvider.PROVIDER_NAME);
        atServer2.init(keyPairParty2.getPrivate()); // Server2 uses its private key to initialize the aggreement object
        atServer2.doPhase(keyPairParty1.getPublic(),true); //Uses Server1's ppublic Key
        SecretKey key2 = atServer2.generateSecret("TlsPremasterSecret"); //derive secret at server 2. "TlsPremasterSecret" is the algorithm for secret key. It is an aes key actually.


        Assert.assertArrayEquals(key1.getEncoded(),key2.getEncoded());
        //Same key was derived in both places and can be used for encrypted message.

        //*Server1
        Cipher cipher1 = Cipher.getInstance("AES",BouncyCastleProvider.PROVIDER_NAME);
        cipher1.init(Cipher.ENCRYPT_MODE,key1);
        byte[] encrypted1 = cipher1.doFinal("Beckn is awesome!".getBytes(StandardCharsets.UTF_8));
        String b64Encryped1 = Base64.getEncoder().encodeToString(encrypted1);


        //At Server2
        Cipher cipher2  = Cipher.getInstance("AES",BouncyCastleProvider.PROVIDER_NAME);
        cipher2.init(Cipher.DECRYPT_MODE,key2); //Same derived key in server 2same as key1
        byte[] decrypted2 = cipher2.doFinal(Base64.getDecoder().decode(b64Encryped1)); // b64 decode the message before decrypting the bytes

        Assert.assertEquals("Beckn is awesome!", new String(decrypted2)); //Ensure that the same encrypted message is received.!


    }

    @Test
    public void testSign() throws Exception{
        /*
        Two Parties generating key pair on their own servers.
         */
        KeyPair keyPairParty1 = KeyPairGenerator.getInstance("Ed25519", BouncyCastleProvider.PROVIDER_NAME).generateKeyPair();
        //KeyPair keyPairParty2 = KeyPairGenerator.getInstance("Ed25519", BouncyCastleProvider.PROVIDER_NAME).generateKeyPair();

        String signingString = "Hello Beckn";

        // At server 1
        Signature signature = Signature.getInstance("Ed25519", BouncyCastleProvider.PROVIDER_NAME); //
        signature.initSign(keyPairParty1.getPrivate());
        signature.update(signingString.getBytes(StandardCharsets.UTF_8));

        byte[] sign = signature.sign();
        String b64Sign = Base64.getEncoder().encodeToString(sign);

        // At Server 2 to verify Server 1 signature
        Signature signature2 = Signature.getInstance("Ed25519",BouncyCastleProvider.PROVIDER_NAME);
        signature.initVerify(keyPairParty1.getPublic()); //Verify using public key of sender.
        signature.update(signingString.getBytes(StandardCharsets.UTF_8)); // Same signing String passed.
        boolean verified = signature.verify(Base64.getDecoder().decode(b64Sign)); //verify the signature bytes = B64Decoded value of the encoded string.
        Assert.assertTrue(verified);

    }
    @Test
    public void testSign1() throws Exception{
        String signingString = "(created): 1645895834\n" +
                "(expires): 1645895854\n" +
                "digest: BLAKE-512=3UY7ZXVmybod2rFlj8/QFnpWoeeSuWnBeEwwOanf6pdu/aYyZAlG+Pq6k8MIdQ471W7/rwXyD7AVzAEjxeUn2g==";

        String passedSignature = "x+xy3NiokZfEIl3kY1PanTuNtGgW8XQC86ILOflR5y3Iad1s5IZ6zNmcoGc1E/CpffHHaG3DmfWyTr7Nq6X/CA==";

        String privateKey = "MFECAQEwBQYDK2VwBCIEIMChGqsZo7FbrwIK7tq6AuKiqtEPCm+rMx7cz+AsqhcFgSEATa9ExVX6Q1c1+4cdmZSFnh1rAR8F0TbIuCCzehTz5U0=";
        String signature = Request.generateSignature(signingString,privateKey);
        System.out.println("Computed:" + signature );
        System.out.println("Passed:" + passedSignature);
        //Assert.assertEquals(signature,passedSignature);

        //boolean verified = Request.verifySignature(signature, s,"MCowBQYDK2VwAyEATa9ExVX6Q1c1+4cdmZSFnh1rAR8F0TbIuCCzehTz5U0=");
        boolean verified = Request.verifySignature(signature, signingString,"Ta9ExVX6Q1c1+4cdmZSFnh1rAR8F0TbIuCCzehTz5U0=");
        Assert.assertTrue(verified);
    }

    @Test
    public void testSignRavi() throws Exception{
        String privateKey = "b3BlbnNzaC1rZXktdjEAAAAABG5vbmUAAAAEbm9uZQAAAAAAAAABAAAAMwAAAAtzc2gtZWQyNTUxOQAAACDC6beMrEFF7VmZK7EY7QW3LYbe8XTik/pEve02ezDa1wAAAIix3rwCsd68AgAAAAtzc2gtZWQyNTUxOQAAACDC6beMrEFF7VmZK7EY7QW3LYbe8XTik/pEve02ezDa1wAAAEBrrQ8SBkbBxNo6Vj9AWQi61Cs+dfycUG7bBXW+g67c+MLpt4ysQUXtWZkrsRjtBbctht7xdOKT+kS97TZ7MNrXAAAAAAECAwQF";

        String publicKey = "X+jgcPXHD4kFzvcbb3eaxWRA5UwB8Mm6IykeTPADofU=" ;
        String signingString = "(created): 1645897554\n" +
                "(expires): 1645897574\n" +
                "digest: BLAKE-512=SzSEdjOtuZVUOBeN6dpCXOZ4DF2kJTFDu9ejBAOneHtyQlAWLa5ravzbGf2wfCyIYAaxBDpaLoHFlCZ8gRh2UA==";

        String passedSignature = "ull+zVvFTdbORi3VCUsA+pL/fiVtf5CJ7nm/kf7Fif4AkZlHE9M9zqA3gwDiMfaTC65HSdgq26grk53bUpEOAQ==";

        String signature = Request.generateSignature(signingString,privateKey);
        System.out.println("Computed:" + signature );
        Assert.assertTrue(Request.verifySignature(signature, signingString,publicKey));
        Assert.assertTrue(Request.verifySignature(passedSignature,signingString,publicKey));
    }

    @Test
    public void generateBlakeHash() throws Exception{
        String stringToHash = "Beckn!";

        MessageDigest digest = MessageDigest.getInstance("BLAKE2B-512",BouncyCastleProvider.PROVIDER_NAME);
        digest.reset();
        digest.update(stringToHash.getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();
        String bs64 = Base64.getEncoder().encodeToString(hash) ;
        System.out.println(bs64);
        System.out.println(toHex(hash)); // We could use bs64 or hex of the hash (Not sure why we need to hex and then base64 it!! Seems overkill.
        //https://8gwifi.org/MessageDigest.jsp

    }

    public String toHex(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i< bytes.length ; i++ ){
            String hex = Integer.toHexString(bytes[i]);
            if (hex.length() == 1){
                hex = "0"+hex;
            }
            hex = hex.substring(hex.length()-2);
            builder.append(hex);
        }
        return builder.toString();
    }
    @Test
    public void testUnderstandingBouncyCastleInternalApis()throws  Exception{
        Ed25519KeyPairGenerator pairGenerator = new Ed25519KeyPairGenerator();
        pairGenerator.init(new Ed25519KeyGenerationParameters(new SecureRandom()));
        AsymmetricCipherKeyPair pair = pairGenerator.generateKeyPair();
        Ed25519PrivateKeyParameters privateKeyParameters = (Ed25519PrivateKeyParameters) pair.getPrivate();
        Ed25519PublicKeyParameters publicKeyParameters = (Ed25519PublicKeyParameters)pair.getPublic();

        byte[] bcBytes = publicKeyParameters.getEncoded();
        byte[] jceBytes = new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),bcBytes).getEncoded(); //Convert bcbytes to jcebytes.
        PublicKey key = getPublicKey("Ed25519",jceBytes); //Convert bc to jce bytes and generate PublicKey.

        Assert.assertArrayEquals(key.getEncoded(),jceBytes);

       //Converting java publicKey to BC publicKeyParams
        BCEdDSAPublicKey k = (BCEdDSAPublicKey) key;
        Field f = k.getClass().getDeclaredField("eddsaPublicKey");
        f.setAccessible(true); //BC Desnot expose this hence this reflection stuff.
        Ed25519PublicKeyParameters publicKeyParameters1 = (Ed25519PublicKeyParameters) f.get(k);

        byte[] bcBytes2 = publicKeyParameters1.getEncoded();
        Assert.assertArrayEquals(bcBytes2,bcBytes);



        //Convert privateKeyParams to java PrivateKey
        byte[] privateJceBytes = new PrivateKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
                new DEROctetString(privateKeyParameters.getEncoded())).getEncoded();

        PrivateKey privateKey = getPrivateKey("Ed25519",privateJceBytes);
        Assert.assertNotNull(privateKey);

        //Convert java PrivateKey to privateKeyParams
        BCEdDSAPrivateKey privateKey1 = (BCEdDSAPrivateKey) privateKey;
        f = privateKey1.getClass().getDeclaredField("eddsaPrivateKey");
        f.setAccessible(true); //BC Desnot expose this hence this reflection stuff.
        Ed25519PrivateKeyParameters privateKeyParameters1 = (Ed25519PrivateKeyParameters) f.get(privateKey1);

        Assert.assertArrayEquals(privateKeyParameters1.getEncoded(),privateKeyParameters.getEncoded());





    }
    @Test
    public void testWhatIsIt() throws Exception{
        // When you get some public/private key b64encoded . How to read it
        // Check if it is bcBytes

        String spublic = "3DQAZxWBPD8g2iTaaqveQyIXNT3h13cwx++b9+6yjN4=";
        String sprivate = "NaKRo2AEFoVPEEXt6AUX72GG4mq0EmMUmo1SJo38znvcNABnFYE8PyDaJNpqq95DIhc1PeHXdzDH75v37rKM3g==";
        byte[] bcBytes = Base64.getDecoder().decode(spublic);
        byte[] jceBytes = new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),bcBytes).getEncoded();
        PublicKey key = getPublicKey("Ed25519",jceBytes); //Convert bc to jce bytes and generate PublicKey.
        Assert.assertNotNull(key);

        PrivateKey privateKey = getPrivateKey("Ed25519",new PrivateKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
                new DEROctetString(Base64.getDecoder().decode(sprivate))).getEncoded());
        Assert.assertNotNull(privateKey);
    }
    @Test
    public void testWhatIsIt2() throws Exception{
        // When you get some public/private key b64encoded . How to read it
        // Check if it is bcBytes
        Assert.assertThrows(InvalidKeySpecException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                //http://ed25519.herokuapp.com/ generated from here.
                String spublic = "3DQAZxWBPD8g2iTaaqveQyIXNT3h13cwx++b9+6yjN4=";
                String sprivate = "NaKRo2AEFoVPEEXt6AUX72GG4mq0EmMUmo1SJo38znvcNABnFYE8PyDaJNpqq95DIhc1PeHXdzDH75v37rKM3g==";
                byte[] jceBytes = Base64.getDecoder().decode(spublic);
                PublicKey key = getPublicKey("Ed25519", jceBytes); //Convert bc to jce bytes and generate PublicKey.
                //This Fails.
                Assert.assertNotNull(key);

                PrivateKey privateKey = getPrivateKey("Ed25519", Base64.getDecoder().decode(sprivate));
                Assert.assertNotNull(privateKey);
            }
        });
    }

    @Test
    public void testReadPem() throws Exception {
        //openssl genpkey -outform pem -algorithm Ed25519 -out private.pem
        //openssl pkey -in private.pem -outform PEM -pubout  -out public.pem
        String spublic = "-----BEGIN PUBLIC KEY-----\nMCowBQYDK2VwAyEAJLTgKuhfvnq998O53E0MfEFrmwK+X0CfoMzTiPK7c+U=\n-----END PUBLIC KEY-----";
        byte[] jceBytes = Base64.getDecoder().decode(spublic.
                replace("-----BEGIN PUBLIC KEY-----\n","").
                replace("\n-----END PUBLIC KEY-----",""));
        PublicKey key = getPublicKey("Ed25519", jceBytes);
        Assert.assertNotNull(key);
    }

    @Test
    public void testNirmal() throws Exception {
        String spublic = "AAAAC3NzaC1lZDI1NTE5AAAAIMLpt4ysQUXtWZkrsRjtBbctht7xdOKT+kS97TZ7MNrX";
        byte[] bytes = Base64.getDecoder().decode(spublic);
        PublicKey key = KeyFactory.getInstance("Ed25519").generatePublic(new OpenSSHPublicKeySpec(bytes));
        Assert.assertNotNull(key);

        PublicKey k2 = Request.getSigningPublicKey("X+jgcPXHD4kFzvcbb3eaxWRA5UwB8Mm6IykeTPADofU=");
        Assert.assertNotNull(k2);

    }
    @Test
    public  void testSandeep() throws  Exception{
        PublicKey key = Request.getSigningPublicKey("sVgSdujT4S+pZm/DwHC39QE/1zTRBScyuMKsqJzuAKM=");
        Assert.assertNotNull(key);
        boolean verified  = Crypt.getInstance().verifySignature("X", "v+k64uPcn8D7BcCgdE0HYh9qtdLgSelfIuBcyo3odc/jV68n0ZHIfi1ZAN/YhwIw65yKvg6fdjZuzlFei8n0Bw==", Request.SIGNATURE_ALGO,key) ;
        Assert.assertTrue(verified);

    }

    @Test
    public void testRawToPem() throws Exception {
        String pv = "RUp8sKmyp0C3IBapYH3BUeQi5GN7zcQgM6Hvn7Y4oGE=";
        String pb = "X+jgcPXHD4kFzvcbb3eaxWRA5UwB8Mm6IykeTPADofU=";

        Ed25519PrivateKeyParameters privateKeyParameters = new Ed25519PrivateKeyParameters(Base64.getDecoder().decode(pv),0);
        Ed25519PublicKeyParameters publicKeyParameters = new Ed25519PublicKeyParameters(Base64.getDecoder().decode(pb),0);

        String pvPem = Base64.getEncoder().encodeToString(new PrivateKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
                new DEROctetString(privateKeyParameters.getEncoded())).getEncoded());
        PrivateKey privateKey = getPrivateKey("Ed25519",Base64.getDecoder().decode(pvPem));
        Assert.assertNotNull(privateKey);
        String  pbPem = Base64.getEncoder().encodeToString(new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),publicKeyParameters.getEncoded()).getEncoded());

        PublicKey publicKey = getPublicKey("Ed25519",Base64.getDecoder().decode(pbPem));
        Assert.assertNotNull(publicKey);

        System.out.println("Private : " +pvPem);
        System.out.println("Public: " + pbPem);

    }
    @Test
    public void testPemToRaw() throws Exception{
        String pv = "MC4CAQAwBQYDK2VwBCIEIEVKfLCpsqdAtyAWqWB9wVHkIuRje83EIDOh75+2OKBh";
        String pb = "MCowBQYDK2VwAyEAX+jgcPXHD4kFzvcbb3eaxWRA5UwB8Mm6IykeTPADofU=";

        PrivateKey privateKey = getPrivateKey("Ed25519",Base64.getDecoder().decode(pv));
        Assert.assertNotNull(privateKey);
        BCEdDSAPrivateKey privateKey1 = (BCEdDSAPrivateKey)privateKey;
        Field f = privateKey1.getClass().getDeclaredField("eddsaPrivateKey");
        f.setAccessible(true); //BC Desnot expose this hence this reflection stuff.
        Ed25519PrivateKeyParameters privateKeyParameters1 = (Ed25519PrivateKeyParameters) f.get(privateKey1);


        PublicKey publicKey = getPublicKey("Ed25519",Base64.getDecoder().decode(pb));
        Assert.assertNotNull(publicKey);
        BCEdDSAPublicKey publicKey1 = (BCEdDSAPublicKey)publicKey;
        f = publicKey1.getClass().getDeclaredField("eddsaPublicKey");
        f.setAccessible(true); //BC Desnot expose this hence this reflection stuff.
        Ed25519PublicKeyParameters publicKeyParameters1 = (Ed25519PublicKeyParameters) f.get(publicKey1);

        String pbRaw = Base64.getEncoder().encodeToString(publicKeyParameters1.getEncoded());
        String pvRaw = Base64.getEncoder().encodeToString(privateKeyParameters1.getEncoded());
        String expectedPvRaw = "RUp8sKmyp0C3IBapYH3BUeQi5GN7zcQgM6Hvn7Y4oGE=";
        String expectedPbRaw = "X+jgcPXHD4kFzvcbb3eaxWRA5UwB8Mm6IykeTPADofU=";
        Assert.assertEquals(pbRaw,expectedPbRaw);
        Assert.assertEquals(pvRaw,expectedPvRaw);

        System.out.println("Private : " +pvRaw);
        System.out.println("Public: " + pbRaw);
    }

    @Test
    public void generatePrivateAndPublicKeyToRaw(){
        Ed25519KeyPairGenerator pairGenerator = new Ed25519KeyPairGenerator();
        pairGenerator.init(new Ed25519KeyGenerationParameters(new SecureRandom()));
        AsymmetricCipherKeyPair pair = pairGenerator.generateKeyPair();
        Ed25519PrivateKeyParameters privateKeyParameters = (Ed25519PrivateKeyParameters) pair.getPrivate();
        Ed25519PublicKeyParameters publicKeyParameters = (Ed25519PublicKeyParameters)pair.getPublic();

        System.out.println("\nPrivate:" + Base64.getEncoder().encodeToString(privateKeyParameters.getEncoded()));
        System.out.println("\nPublic:" + Base64.getEncoder().encodeToString(publicKeyParameters.getEncoded()));



    }

    @Test
    public void testEncryptionKeyFromRegistry(){
        String key = "lCI84I0Q0U0wQ/T+cxP25+a+9sK8sstBpulLa+4iqEY=" ;
        Assert.assertEquals(key,Request.getRawEncryptionKey(key));
        Assert.assertNotNull(Request.getEncryptionPublicKey(key));

        key = "5KHS4StYY8CG4H+1reZtiycN0vCkkUumvZW0tvv6OiQ=";
        Assert.assertEquals(key,Request.getRawEncryptionKey(key));
        Assert.assertNotNull(Request.getEncryptionPublicKey(key));

        key = "zMnN+2TOdCQWau2ck0hpjmifP9RhAF7EqLzDjO564Ec=";
        Assert.assertEquals(key,Request.getRawSigningKey(key));

        key = "H1qQWQzOm3grTCN/C3Lv9IRjkbb0Iqk+QT5858PfmIQ=";
        Assert.assertEquals(key,Request.getRawSigningKey(key));

    }

    @Test
    public void testDhirajCode() throws Exception{
        KeyPair keyPair1 = Crypt.getInstance(BouncyCastleProvider.PROVIDER_NAME).generateKeyPair(Request.ENCRYPTION_ALGO,Request.ENCRYPTION_ALGO_KEY_LENGTH);
        KeyPair keyPair2 = Crypt.getInstance(BouncyCastleProvider.PROVIDER_NAME).generateKeyPair(Request.ENCRYPTION_ALGO,Request.ENCRYPTION_ALGO_KEY_LENGTH);



        SecretKey k1 = getSecretKey(Crypt.getInstance().getBase64Encoded(keyPair1.getPrivate()),Crypt.getInstance().getBase64Encoded(keyPair2.getPublic()));
        SecretKey k2 = getSecretKey(Crypt.getInstance().getBase64Encoded(keyPair2.getPrivate()),Crypt.getInstance().getBase64Encoded(keyPair1.getPublic()));
        Assert.assertArrayEquals(k1.getEncoded(),k2.getEncoded());

        String e = encChallenge("Hello World",nsdlPrivateKey,otherPublicKey);
        System.out.println(e);
        String d = decChallenge(e,otherPrivateKey,nsdlPublicKey);
        Assert.assertEquals(d,"Hello World");

    }
    @Test
    public void testSandeepX25519() throws Exception{
         String  regstryKey = "/re98S+QQonKxutHTNsnfX3qbSjZrsiqZ/drbeLbhis=";
         String privateKEy = "SKROdhs7yeD4gdJi2fOWvHLA9rqYaBzty5v8k6bH/3Q=";
         //SecretKey key = getSecretKey(privateKEy,regstryKey);
         String secret = "0Kpkm/vWrCGE4cUBfO0+jiGoNjpAME43/Alg7kC3Su0MxHYqEi18iAMpX8HD3XBM";

         System.out.println(decChallenge(secret,privateKEy,regstryKey));
    }
    @Test
    public void testSandeepX25519OnRegistrySide() throws Exception{
        String publicKey = "MCowBQYDK2VuAyEA5ZnLsJiemxA+4xnZg9uA1rW9fxULelTIfUDT5CidMjQ=";
        String privateKey = "MFECAQEwBQYDK2VuBCIEICBh1xuhLhT1SqfZiLmRusAl2xESPr6/fnzl5Op7hOBsgSEA/re98S+QQonKxutHTNsnfX3qbSjZrsiqZ/drbeLbhis=";

        KeyAgreement keyAgreement = KeyAgreement.getInstance(ENC_DEC_ALGO_X25519, BouncyCastleProvider.PROVIDER_NAME);
        // atServer1.init(nsdlKeyPair.getPrivate()); // Server1 uses its private
        // key to initialize the aggreement object
        keyAgreement.init(getEncryptionPrivateKey(Base64.getDecoder().decode(privateKey))); // Server1 uses its private key to initialize the aggreement object
        // atServer1.doPhase(tcsKeyPair.getPublic(),true); //Uses Server2's
        // ppublic Key
        keyAgreement.doPhase(getEncryptionPublicKey(Base64.getDecoder().decode(publicKey)), true);

        SecretKey key = keyAgreement.generateSecret("TlsPremasterSecret");


        String sharedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(sharedKey);
        SecretKey k2 = SecretKeyFactory.getInstance("AES").generateSecret(new SecretKeySpec(Base64.getDecoder().decode(sharedKey),"AES"));
        System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));

    }
    @Test
    public void testDhirajCodeDecrypt() throws Exception{
        //String publicKey = "5KHS4StYY8CG4H+1reZtiycN0vCkkUumvZW0tvv6OiQ=";
        //String nsdlPrivateKey = "IcjI/ozl+pbOWDcKlj5uXb10fPl+8oLO8mKeRLt5RZi1VwYQX2DVG9xHJGp/0LgLi979bDjBrnciAlFyr3mhWdoLe5YdOZJj5SOOSEElaHZXPotn3+r+L/4iekZuvTOREI5BXaMlryqx/1M02rEKUp4AkkMTSYNzYJ+kuDLu6P8=";
        System.out.println(Request.getRawEncryptionKey(nsdlPublicKey));
        System.out.println(decChallenge("Jw0vQimQ6cP3r3zHAxYi4A==",otherPrivateKey,nsdlPublicKey));
    }
    String nsdlPublicKey = "MCowBQYDK2VuAyEAqe/iT3XBu/3VfaH9muoQ7s6644LORdDc5KIcldN86wE=";
    String nsdlPrivateKey = "MFECAQEwBQYDK2VuBCIEIDi8orAdzNaZNJZ85xmwbSwBF3uN/Mup4R29f38IPOtsgSEAqe/iT3XBu/3VfaH9muoQ7s6644LORdDc5KIcldN86wE=";
    String otherPublicKey = "5KHS4StYY8CG4H+1reZtiycN0vCkkUumvZW0tvv6OiQ=";
    String otherPrivateKey = "MFECAQEwBQYDK2VuBCIEICDv0YL8eHXl3EDBsBF3YwEnWnxROe+bbtCzusVjkuZHgSEA5KHS4StYY8CG4H+1reZtiycN0vCkkUumvZW0tvv6OiQ="; //Was wrong! I had given encrypted private key.

    /*String nsdlPrivateKey = "MFECAQEwBQYDK2VuBCIEIKDpLFCYkoezrEjedy+wU9GhqYfPR+z8bs3C+d1NtVFMgSEAh0EblrXEhdqECkVdiTuG3ma+n9UCcfJD0wu9LS3UVhU=";
    String otherPublicKey = "MCowBQYDK2VuAyEA6l2nS9ILqzqFPc0ocWlTp/1T2LNdUazs1tusG1+9KUo=";

    String  nsdlPublicKey = "MCowBQYDK2VuAyEAh0EblrXEhdqECkVdiTuG3ma+n9UCcfJD0wu9LS3UVhU=";
    String  otherPrivateKey = "MFECAQEwBQYDK2VuBCIEIPBxDXHvCD1ZiW7X31kre/b0aYfPB+6l/zTTVuFGhLVpgSEA6l2nS9ILqzqFPc0ocWlTp/1T2LNdUazs1tusG1+9KUo=";
    */

    @Test
    public void testRegex(){
        System.out.println(Pattern.compile("\\d{15}").matcher("12345678901235").matches());
    }

    public SecretKey getSecretKey(String privateKey1, String publicKey2) throws Exception{
        System.out.println(String.format("Private:%s\n" ,privateKey1));
        System.out.println(String.format("OtherPublic:%s\n" ,publicKey2));

        KeyAgreement keyAgreement = KeyAgreement.getInstance(ENC_DEC_ALGO_X25519, BouncyCastleProvider.PROVIDER_NAME);
        keyAgreement.init(getEncryptionPrivateKey(Base64.getDecoder().decode(privateKey1))); // Server1 uses its private key to initialize the aggreement object
        keyAgreement.doPhase(getEncryptionPublicKey(Base64.getDecoder().decode(publicKey2)), true); // Uses Server2's ppublic Key
        SecretKey key1 = keyAgreement.generateSecret("TlsPremasterSecret "); // derive secret at server 1. "TlsPremasterSecret" is the algorithm for secret key. It is an aes key actually.
        return key1;
    }
    public static final String ENC_DEC_ALGO_X25519 = Request.ENCRYPTION_ALGO;
    public static String encChallenge(String cs, String nsdlPrivateKey, String otherPublicKey) throws Exception
    {
        KeyAgreement keyAgreement = KeyAgreement.getInstance(ENC_DEC_ALGO_X25519, BouncyCastleProvider.PROVIDER_NAME);
        // atServer1.init(nsdlKeyPair.getPrivate()); // Server1 uses its private
        // key to initialize the aggreement object
        keyAgreement.init(getEncryptionPrivateKey(Base64.getDecoder().decode(nsdlPrivateKey))); // Server1 uses its private key to initialize the aggreement object
        // atServer1.doPhase(tcsKeyPair.getPublic(),true); //Uses Server2's
        // ppublic Key
        keyAgreement.doPhase(getEncryptionPublicKey(Base64.getDecoder().decode(otherPublicKey)), true); // Uses Server2's ppublic Key
        SecretKey key1 = keyAgreement.generateSecret("TlsPremasterSecret"); // derive secret at server 1. "TlsPremasterSecret" is the algorithm for secret key. It is an aes key actually.
        // *Server1
        Cipher cipher1 = Cipher.getInstance("AES", BouncyCastleProvider.PROVIDER_NAME);
        cipher1.init(Cipher.ENCRYPT_MODE, key1);
        byte[] encrypted1 = cipher1.doFinal(cs.getBytes(StandardCharsets.UTF_8));
        String b64Encryped1 = Base64.getEncoder().encodeToString(encrypted1);
        System.out.println("Encrypted : " + b64Encryped1);
        return b64Encryped1;
    }


    public static String decChallenge(String cs, String otherPrivateKey, String nsdlPublicKey) throws  Exception
    {
        //KeyAgreement ka = KeyAgreement.getInstance(ENC_DEC_ALGO_X25519, BouncyCastleProvider.PROVIDER_NAME);
        //ka.init(getPrivateKey(ENC_DEC_ALGO_X25519, Base64.getDecoder().decode(otherPrivateKey)));
        //ka.doPhase(getPublicKey(ENC_DEC_ALGO_X25519, Base64.getDecoder().decode(nsdlPublicKey)), true);


        KeyAgreement keyAgreement = KeyAgreement.getInstance(ENC_DEC_ALGO_X25519, BouncyCastleProvider.PROVIDER_NAME);
        // atServer1.init(nsdlKeyPair.getPrivate()); // Server1 uses its private
        // key to initialize the aggreement object
        keyAgreement.init(getEncryptionPrivateKey(Base64.getDecoder().decode(otherPrivateKey))); // Server1 uses its private key to initialize the aggreement object
        // atServer1.doPhase(tcsKeyPair.getPublic(),true); //Uses Server2's
        // ppublic Key
        keyAgreement.doPhase(getEncryptionPublicKey(Base64.getDecoder().decode(nsdlPublicKey)), true);

        SecretKey key2 = keyAgreement.generateSecret("TlsPremasterSecret");
        Cipher cipher2 = Cipher.getInstance("AES", BouncyCastleProvider.PROVIDER_NAME);
        cipher2.init(Cipher.DECRYPT_MODE, key2);
        byte[] decrypted2 = cipher2.doFinal(Base64.getDecoder().decode(cs));
        //System.out.println("" + new String(decrypted2));
        //System.out.println(String.valueOf(decrypted2));
        return new String(decrypted2);
    }

    public static PublicKey getEncryptionPublicKey(byte[] bytes){
        try {
            return getPublicKey(ENC_DEC_ALGO_X25519,bytes);
        }catch (Exception ex){
            try {
                byte[] jceBytes = new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X25519), bytes).getEncoded();
                return getPublicKey(ENC_DEC_ALGO_X25519, jceBytes);
            }catch (Exception jceEx){
                return null;
            }
        }
    }
    public static PrivateKey getEncryptionPrivateKey(byte[] bytes) {
        try {
            return getPrivateKey(ENC_DEC_ALGO_X25519,bytes);
        }catch (Exception ex){
            try {
                X25519PrivateKeyParameters privateKeyParameters = new X25519PrivateKeyParameters(bytes,0);
                byte[] jceBytes = new PrivateKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X25519),
                        new DEROctetString(privateKeyParameters.getEncoded())).getEncoded();
                return getPrivateKey(ENC_DEC_ALGO_X25519,jceBytes);
            }catch (Exception jceEx){
                return null;
            }
        }


    }


    public static PublicKey getPublicKey(String algo, byte[] jceBytes) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(jceBytes);
        PublicKey key = KeyFactory.getInstance(algo, BouncyCastleProvider.PROVIDER_NAME)
                .generatePublic(x509EncodedKeySpec);
        return key;
    }
    public static PrivateKey getPrivateKey(String algo, byte[] jceBytes) throws Exception {
        PrivateKey key = KeyFactory.getInstance(algo, BouncyCastleProvider.PROVIDER_NAME)
                .generatePrivate(new PKCS8EncodedKeySpec(jceBytes));
        return key;
    }


}
