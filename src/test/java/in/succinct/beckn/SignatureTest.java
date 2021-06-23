package in.succinct.beckn;

import com.venky.core.security.Crypt;
import org.bouncycastle.jcajce.provider.asymmetric.edec.BCEdDSAPublicKey;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;


public class SignatureTest {
    @Test
    public void testsign() throws Exception{
        String payload = "{\"subscriber_id\":\"mandi.succinct.in\",\"challenge\":\"P+3enc5zd44uKYIs3mg8yqOHpHPH+zJjap9buhrHg+Gx+rXFyRgVSoeXJADXmqbOGwXjJ9hkv\\/y++FG3LuSNcSw6GMv8uwhapuTbcdhIgZo6JUBgBaiHJMzaoes0euyogGyY7ktWWNiH7mti6b2N3ZTOtYlGkFM+rlImSEBEX\\/skaoH4mlvIu448sup1EjRwqQqVq\\/PMacrHJgoLk72QyOH41U\\/Gv46NBmB5lgqmrQc3O+WW5iCGht9yqC2cWaSytK0E7Xp7d2LKtQLgUGHj\\/DNk9i6\\/oPuIN5NSIwaUkC2H1UQ7N0iLEoTyS+X7zHYMcpXGDtQk5Y35iAuoozpuvA==\"}";

        Request request = new Request();
        KeyPair pair = Crypt.getInstance().generateKeyPair(EdDSAParameterSpec.Ed25519,256);

        String privateKey = Crypt.getInstance().getBase64Encoded(pair.getPrivate());
        String publicKey  = Crypt.getInstance().getBase64Encoded(pair.getPublic());


        String sign = Request.generateSignature(payload,privateKey);
        Assert.assertTrue(Request.verifySignature(sign,payload,publicKey));


        Assert.assertTrue(Request.verifySignature(sign,payload,publicKey));

    }

}
