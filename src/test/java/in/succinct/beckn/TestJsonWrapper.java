package in.succinct.beckn;

import org.junit.Assert;
import org.junit.Test;

public class TestJsonWrapper {
    @Test
    public void test(){
        String json = "{\"context\":{\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"std:080\",\"action\":\"select\",\"core_version\":\"1.1.0\",\"bap_id\":\"buyer-app-preprod.ondc.org\",\"bap_uri\":\"https://buyer-app-preprod.ondc.org/protocol/v1\",\"bpp_uri\":\"https://preprod-wiggles.humbhionline.in/bpp\",\"transaction_id\":\"1438cccc-2a59-4846-afbf-4a5620155e79\",\"message_id\":\"367f6ecf-08a2-4c68-95d6-f63239eb8f85\",\"timestamp\":\"2023-07-18T08:03:31.394Z\",\"bpp_id\":\"preprod-wiggles.humbhionline.in\",\"ttl\":\"PT30S\"},\"message\":{\"order\":{\"items\":[{\"id\":\"./local_retail/ind/44773308137766@preprod-wiggles.humbhionline.in.item\",\"quantity\":{\"count\":1},\"location_id\":\"./local_retail/ind/80777412902@preprod-wiggles.humbhionline.in.provider_location\"}],\"provider\":{\"id\":\"preprod-wiggles.humbhionline.in\",\"locations\":[{\"id\":\"./local_retail/ind/80777412902@preprod-wiggles.humbhionline.in.provider_location\"}]},\"fulfillments\":[{\"end\":{\"location\":{\"gps\":\"12.898463, 77.600873\",\"address\":{\"area_code\":\"560076\"}}}}]}}}";
        Request r = new Request(json);


        Assert.assertNotNull(r.getMessage().getOrder().getProvider().getLocations().get(0));
    }

}
