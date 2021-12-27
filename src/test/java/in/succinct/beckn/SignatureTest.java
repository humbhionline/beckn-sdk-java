package in.succinct.beckn;

import com.venky.core.collections.IgnoreCaseMap;
import com.venky.core.collections.SequenceMap;
import com.venky.core.security.Crypt;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator;
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jcajce.provider.digest.Blake2b.Blake2b512;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.NamedParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.XECPrivateKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class SignatureTest {
    @org.junit.BeforeClass
    public static void setup(){
    }

    @Test
    public void genKeys(){

    }

    @Test
    public void testsign() throws Exception{
        String payload = "{\"subscriber_id\":\"mandi.succinct.in\",\"challenge\":\"P+3enc5zd44uKYIs3mg8yqOHpHPH+zJjap9buhrHg+Gx+rXFyRgVSoeXJADXmqbOGwXjJ9hkv\\/y++FG3LuSNcSw6GMv8uwhapuTbcdhIgZo6JUBgBaiHJMzaoes0euyogGyY7ktWWNiH7mti6b2N3ZTOtYlGkFM+rlImSEBEX\\/skaoH4mlvIu448sup1EjRwqQqVq\\/PMacrHJgoLk72QyOH41U\\/Gv46NBmB5lgqmrQc3O+WW5iCGht9yqC2cWaSytK0E7Xp7d2LKtQLgUGHj\\/DNk9i6\\/oPuIN5NSIwaUkC2H1UQ7N0iLEoTyS+X7zHYMcpXGDtQk5Y35iAuoozpuvA==\"}";

        Request request = new Request();
        KeyPair pair = Crypt.getInstance().generateKeyPair(EdDSAParameterSpec.Ed25519,256);
        KeyPair pair2 = Crypt.getInstance().generateKeyPair("X25519",256);

        String privateKey = Crypt.getInstance().getBase64Encoded(pair.getPrivate());
        String publicKey  = Crypt.getInstance().getBase64Encoded(pair.getPublic());


        String sign = Request.generateSignature(payload,privateKey);
        Assert.assertTrue(Request.verifySignature(sign,payload,publicKey));


        Assert.assertTrue(Request.verifySignature(sign,payload,publicKey));

    }

    @Test
    public void testKeySizeNew() throws Exception {
        String algo = "Ed25519";
        KeyPair pair = Crypt.getInstance().generateKeyPair(algo,256);
        String pbk  = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
        String pvk  = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());


        PublicKey key = Crypt.getInstance().getPublicKey(Request.SIGNATURE_ALGO,pbk);
        PrivateKey privateKey = Crypt.getInstance().getPrivateKey(Request.SIGNATURE_ALGO,pvk);

        String payload = "Hello";
        String sign1 = Crypt.getInstance().generateSignature(payload,Request.SIGNATURE_ALGO,privateKey);
        System.out.println("Venky's Sign :" + sign1);
        Crypt.getInstance().verifySignature(payload,sign1,Request.SIGNATURE_ALGO,key);



        Ed25519PublicKeyParameters publicKeyParameters = new Ed25519PublicKeyParameters(Base64.getDecoder().decode(pbk),0);



        Ed25519Signer signer2 = new Ed25519Signer();
        signer2.init(false,publicKeyParameters);
        signer2.update(payload.getBytes(StandardCharsets.UTF_8),0,payload.length());
        signer2.verifySignature(Base64.getDecoder().decode(sign1));



        Ed25519PrivateKeyParameters privateKeyParameters = new Ed25519PrivateKeyParameters(Base64.getDecoder().decode(pvk),0);
        Ed25519Signer signer = new Ed25519Signer();
        signer.init(true,privateKeyParameters);
        signer.update(payload.getBytes(StandardCharsets.UTF_8),0,payload.length());
        String sign2 = Base64.getEncoder().encodeToString(signer.generateSignature());


        System.out.println("Sign2:" + sign2);

        Crypt.getInstance().verifySignature(payload,sign2,Request.SIGNATURE_ALGO,key);

        signer2.update(payload.getBytes(StandardCharsets.UTF_8),0,payload.length());
        signer2.verifySignature(Base64.getDecoder().decode(sign2));

        Assert.assertNotEquals(sign1,sign2);
    }
    @Test
    public void testKeySizeNew2() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, InvalidKeySpecException, NoSuchProviderException {
        String algo = "Ed25519";
        Ed25519KeyPairGenerator pairGenerator = new Ed25519KeyPairGenerator();
        pairGenerator.init(new Ed25519KeyGenerationParameters(new SecureRandom()));
        AsymmetricCipherKeyPair pair = pairGenerator.generateKeyPair();
        Ed25519PrivateKeyParameters privateKeyParameters = (Ed25519PrivateKeyParameters) pair.getPrivate();
        Ed25519PublicKeyParameters publicKeyParameters = (Ed25519PublicKeyParameters)pair.getPublic();

        Crypt.getInstance();
        KeyFactory keyFactory = KeyFactory.getInstance("Ed25519", BouncyCastleProvider.PROVIDER_NAME);


        PublicKey key = keyFactory.generatePublic(
                new X509EncodedKeySpec(new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
                        publicKeyParameters.getEncoded()).getEncoded()));
        PrivateKey privateKey = keyFactory.generatePrivate(
                new PKCS8EncodedKeySpec(new PrivateKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
                                new DEROctetString(privateKeyParameters.getEncoded())).getEncoded()));


        /*
        PublicKey key = Crypt.getInstance().getPublicKey(Request.SIGNATURE_ALGO,pbk);
        PrivateKey privateKey = Crypt.getInstance().getPrivateKey(Request.SIGNATURE_ALGO,pvk);
        */

        String payload = "Hello";
        String sign1 = Crypt.getInstance().generateSignature(payload,Request.SIGNATURE_ALGO,privateKey);
        System.out.println("Venky's Sign :" + sign1);
        Crypt.getInstance().verifySignature(payload,sign1,Request.SIGNATURE_ALGO,key);

        Ed25519Signer signer2 = new Ed25519Signer();
        signer2.init(false,publicKeyParameters);
        signer2.update(payload.getBytes(StandardCharsets.UTF_8),0,payload.length());
        signer2.verifySignature(Base64.getDecoder().decode(sign1));



        Ed25519Signer signer = new Ed25519Signer();
        signer.init(true,privateKeyParameters);
        signer.update(payload.getBytes(StandardCharsets.UTF_8),0,payload.length());
        String sign2 = Base64.getEncoder().encodeToString(signer.generateSignature());


        System.out.println("Sign2:" + sign2);

        Crypt.getInstance().verifySignature(payload,sign2,Request.SIGNATURE_ALGO,key);

        signer2.update(payload.getBytes(StandardCharsets.UTF_8),0,payload.length());
        signer2.verifySignature(Base64.getDecoder().decode(sign2));

        Assert.assertEquals(sign1,sign2);
    }
    @Test
    public void retestWithBC()throws  Exception{
        String public_key = "D6P6S2zx8i/YMSrsi6+3oNTX7cTgbTY1iHX7TqW6moU=";
        byte[] binSpec =new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
                Base64.getDecoder().decode(public_key)).getEncoded();

        String jcePubKey = Base64.getEncoder().encodeToString(new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
                Base64.getDecoder().decode(public_key)).getEncoded());

        PublicKey publicKey = Crypt.getInstance().getPublicKey("Ed25519",jcePubKey);


        PublicKey key = KeyFactory.getInstance("Ed25519","BC").generatePublic(
                new X509EncodedKeySpec(new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519),
                        Base64.getDecoder().decode(public_key)).getEncoded()));




    }

    @Test
    public void testNirmal() throws Exception {
        String payload = "{\n" +
                "    \"context\": {\n" +
                "      \"domain\": \"test\",\n" +
                "      \"country\": \"string\",\n" +
                "      \"city\": \"string\",\n" +
                "      \"action\": \"on_support\",\n" +
                "      \"core_version\": \"string\",\n" +
                "      \"bap_id\": \"string\",\n" +
                "      \"bap_uri\": \"string\",\n" +
                "      \"bpp_id\": \"string\",\n" +
                "      \"bpp_uri\": \"string\",\n" +
                "      \"transaction_id\": \"string\",\n" +
                "      \"message_id\": \"string\",\n" +
                "      \"timestamp\": \"2021-03-30T12:25:31.333Z\",\n" +
                "      \"key\": \"string\",\n" +
                "      \"ttl\": \"string\"\n" +
                "    },\n" +
                "    \"message\": {\n" +
                "      \"phone\": \"string\",\n" +
                "      \"email\": \"user@example.com\",\n" +
                "      \"uri\": \"string\"\n" +
                "    }\n" +
                "  }\n";

        System.out.println("Request.hash :\n" + Request.generateBlakeHash(payload));


        Map<String,String> header = new HashMap<>();
        //header.put("Authorization","Signature keyId=\"MOCK_SUB_ID|key1|xed25519\" algorithm=\"xed25519\" created=\"1624423460\" expires=\"1624427060\" headers=\"(created) (expires) digest\" signature=\"VM5BwNtKk3wZy4a37lGMJDta-gEyIeOqbNCNR2rqqpy52ejsPuRAVcwZsTU7BdUQCyl8nQ-TXbr81YO8_NaOAA\"");

        Request request = new Request(payload);
        //KeyPair pair = Crypt.getInstance().generateKeyPair(EdDSAParameterSpec.Ed25519,256);

        String privateKey = "MFECAQEwBQYDK2VwBCIEIHvkevAws5WgG7JQ/C92R/vnIyY7no66orNDNHATNp4xgSEAQTQgyHhsZC9xR9TDdjtkwFVGE6+J3LqeeRdUABWIXAU=";//Crypt.getInstance().getBase64Encoded(pair.getPrivate());
        String publicKey  = "MCowBQYDK2VwAyEAQTQgyHhsZC9xR9TDdjtkwFVGE6+J3LqeeRdUABWIXAU=";//Crypt.getInstance().getBase64Encoded(pair.getPublic());

        System.out.println("PrivateKey:" + privateKey);
        System.out.println("PublicKey:" + publicKey);


        Map<String,String> map = new SequenceMap<>();
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append("MOCK_SUB_ID").append('|')
                .append("key1").append('|').append("xed25519");

        map.put("keyId",keyBuilder.toString());
        map.put("algorithm","xed25519");
        long created_at = 1624423460;
        long expires_at = 1624423460;
        map.put("created",Long.toString(created_at));
        map.put("expires",Long.toString(expires_at));
        map.put("headers","(created) (expires) digest");
        map.put("signature",Request.generateSignature(
                Request.generateBlakeHash(request.getSigningString(created_at,expires_at)),privateKey));

        StringBuilder auth = new StringBuilder("Signature");
        map.forEach((k,v)-> auth.append(" ").append(k).append("=\"").append(v).append("\""));
        System.out.println(auth);
        header.put("Authorization",auth.toString());


        Map<String,String> params = request.extractAuthorizationParams("Authorization",header);
        String signature = params.get("signature");
        String created = params.get("created");
        String expires = params.get("expires");
        String keyId = params.get("keyId");
        StringTokenizer keyTokenizer = new StringTokenizer(keyId,"|");
        String subscriberId = keyTokenizer.nextToken();
        String uniqueKeyId = keyTokenizer.nextToken();

        String hashedSigningString = request.generateBlakeHash(request.getSigningString(Long.valueOf(created),Long.valueOf(expires)));



        Assert.assertTrue(Request.verifySignature(signature,hashedSigningString,publicKey));


    }
    @Test
    public void testBlakeHash(){
        MessageDigest digest = new Blake2b512();
        digest.reset();
        digest.update("Venky".getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();
        String hex = Hex.toHexString(hash);
        String bs64Hex = Base64.getEncoder().encodeToString(hex.getBytes()) ;
        String bs64 = Base64.getEncoder().encodeToString(hash);
        System.out.println(hex);
        System.out.println(bs64Hex);
        System.out.println(bs64);

    }


    @Test
    public void testSignaturePattern1(){
        Map<String,String> httpHeaders = new IgnoreCaseMap<>();
        String signature = "Signature keyId=\"JUSPAY.FMD.UAT.1|juspay-fmd-1-key|ed25519\",algorithm=\"ed25519\",created=1640246819,expires=1640247419,headers=\"(created) (expires) digest\",signature=\"zUwMBABRgJZ57VZY924CMaF/mJRVXvS20/k4JYz8GRJCtGJhQTfz+kMbDcUVrKG86W+tG99m4d7QYY4LzgzEBg==\"";
        httpHeaders.put("AUTHORIZATION",signature);
        Map<String,String> map = new Request().extractAuthorizationParams("Authorization",httpHeaders);
        System.out.println(map.toString());
    }
}
