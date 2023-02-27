package in.succinct.beckn;

import com.venky.core.string.StringUtil;
import org.json.simple.JSONAware;

public class Tags extends BecknObject{
    public Tags(){
        super();
    }
    public Tags(Object... pairs){
        for (int i = 0 ; i < pairs.length ; i += 2){
            String key = (String) pairs[i];
            Object value = pairs[i+1];
            if (value instanceof BecknAware){
                set((String) pairs[i],(BecknAware<? extends JSONAware>)pairs[i+1]);
            }else {
                set(key, StringUtil.valueOf(value));
            }
        }
    }
    @Override
    public boolean hasAdditionalProperties() {
        return true;
    }
}
