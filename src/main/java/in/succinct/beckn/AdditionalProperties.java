package in.succinct.beckn;

import in.succinct.json.ExtendedJSONAware;

import java.lang.reflect.ParameterizedType;

public class AdditionalProperties<T extends ExtendedJSONAware> extends BecknObject{

    Class<T> clazz;
    public AdditionalProperties(){
        ParameterizedType pt = (ParameterizedType)(getClass().getGenericSuperclass());
        clazz = (Class<T>)pt.getActualTypeArguments()[0];
    }
    public AdditionalProperties(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public T get(String key) {
        return super.get(clazz,key);
    }


    public static class AdditionalSources extends AdditionalProperties<ResolutionSource>{

    }
}
