package in.succinct.beckn;

import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BGSignTest {

    @Test
    public void a() throws Exception{
        Class.forName(Issue.Status.class.getName());
    }
    @Test
    public void testnsdlJan82023()throws Exception{
        String key     = "ONtluFKk9FfVIePk0FJjCZE1XlTdcXtcWWRjqzllVSw=";
        String jsonText = "{\"context\":{\"transaction_id\":\"f2547469-8ef7-4fb1-8caf-80126cfe5d98\",\"country\":\"IND\",\"bpp_id\":\"becknify.humbhionline.in.local_retail.BPP/ondc/app1-magnolabs-in\",\"city\":\"std:080\",\"message_id\":\"8fb7885b-4f21-4deb-92c5-95cedf307507\",\"core_version\":\"1.0.0\",\"ttl\":\"PT30S\",\"bap_id\":\"buyer-app.ondc.org\",\"domain\":\"nic2004:52110\",\"bpp_uri\":\"https://becknify.humbhionline.in/local_retail/ondc/app1-magnolabs-in/bpp\",\"action\":\"on_search\",\"bap_uri\":\"https://buyer-app.ondc.org/protocol/v1\",\"timestamp\":\"2023-01-06T07:55:18.949Z\"},\"message\":{\"catalog\":{\"bpp/providers\":[{\"fssai_license_no\":\"12345678901234\",\"id\":\"ondcconnect.myshopify.com\",\"descriptor\":{\"name\":\"ONDC Connect Test Store\",\"short_desc\":\"ONDC Connect Test Store\"},\"items\":[]}],\"bpp/descriptor\":{\"short_desc\":\"ONDC Connect Test Store\",\"long_desc\":\"ONDC Connect Test Store\"}}}}";
        String sign = "WJlPVFBF+T8ySnPLGrZ3arijsOxsYVgDfoLCj0ooFaC3P5mvjnrsHm9Duhop/32xVN4Z0tM5mClX4BPDzj5SCw==";

        String signingString = new Request(jsonText).getSigningString(1672991721L,1672991751L);

        Ed25519PublicKeyParameters pubKey = new Ed25519PublicKeyParameters(Base64.getDecoder().decode(key),0);
        Ed25519Signer signer = new Ed25519Signer();
        signer.init(false,pubKey);
        byte[] signingBytes =  signingString.getBytes(StandardCharsets.UTF_8);
        signer.update(signingBytes,0,signingBytes.length);
        Assert.assertTrue(signer.verifySignature(Base64.getDecoder().decode(sign)));
    }
}
