package in.succinct.beckn;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SampleUseCase {
    @BeforeClass
    public static void setup(){
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null){
            Security.addProvider(new BouncyCastleProvider());
        }
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

        byte[] bytes = Base64.getDecoder().decode(B64Ed25519PublicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bytes);
        PublicKey key = KeyFactory.getInstance("Ed25519",BouncyCastleProvider.PROVIDER_NAME).generatePublic(x509EncodedKeySpec);
        Assert.assertNotNull(key);

        bytes = Base64.getDecoder().decode(B64X25519PublicKey);
        x509EncodedKeySpec = new X509EncodedKeySpec(bytes);
        key = KeyFactory.getInstance("X25519",BouncyCastleProvider.PROVIDER_NAME).generatePublic(x509EncodedKeySpec);
        Assert.assertNotNull(key);


        PrivateKey privateKey = KeyFactory.getInstance("Ed25519",BouncyCastleProvider.PROVIDER_NAME).generatePrivate(
                new PKCS8EncodedKeySpec(Base64.getDecoder().decode(B64Ed25519PrivateKey)));

        Assert.assertNotNull(privateKey);
        privateKey = KeyFactory.getInstance("X25519",BouncyCastleProvider.PROVIDER_NAME).generatePrivate(
                new PKCS8EncodedKeySpec(Base64.getDecoder().decode(B64X25519PrivateKey)));

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

}
