package in.succinct.beckn;

import com.venky.core.util.ObjectUtil;
import org.json.simple.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BecknObjectBase extends BecknAware<JSONObject> {
    public BecknObjectBase(JSONObject value) {
        super(value);
    }

    public BecknObjectBase(String payload) {
        super(payload);
    }
    public BecknObjectBase(){
        this(new JSONObject());
    }

    public <T extends  BecknObjectBase> void update(T from){
        update(from,true);
    }
    public <T extends  BecknObjectBase> void update(T from, boolean reset){
        update(from, getObjectCreator(),reset);
    }
    private <T extends  BecknObjectBase> void update(T fromSource , JSONAwareWrapperCreator boCreator){
        update(fromSource,boCreator,true);
    }
    private <T extends  BecknObjectBase> void update(T fromSource , JSONAwareWrapperCreator boCreator, boolean reset){
        if (!hasCommonAncestor(this,fromSource)){
            throw new IllegalArgumentException("Incompatible type of the parameter");
        }

        Class<? extends BecknObjectBase> sourceClass = fromSource.getClass();

        Map<String, Method> targetSetters = new HashMap<>();
        Map<String,Method> targetGetters = new HashMap<>();
        Map<String,Method> sourceGetters = new HashMap<>();
        for (Method m : sourceClass.getMethods()) {
            if ((m.getName().startsWith("get") || m.getName().startsWith("is")) && m.getParameterCount() == 0 && !m.getName().equals("getInner") && BecknObject.class.isAssignableFrom(m.getDeclaringClass())){
                sourceGetters.put(m.getName(),m);
            }
        }

        for (Method m : getClass().getMethods()) {
            if (!BecknObject.class.isAssignableFrom(m.getDeclaringClass())){
                continue;
            }
            if ((m.getName().startsWith("set") && m.getParameterCount() == 1 )) {
                String setterName = m.getName();

                String getterName = String.format("g%s",setterName.substring(1));
                Method sourceGetter = sourceGetters.get(getterName);

                if (sourceGetter == null){
                    getterName = String.format("is%s",setterName.substring(3));
                    sourceGetter = sourceGetters.get(getterName);

                }
                if (sourceGetter !=  null && hasCommonAncestor(m.getParameterTypes()[0],sourceGetter.getReturnType())){
                    targetSetters.put(m.getName(), m);
                }
            }else if ((m.getName().startsWith("get") || m.getName().startsWith("is")) && m.getParameterCount() == 0){
                targetGetters.put(m.getName(),m);
            }
        }

        for (Map.Entry<String,Method> entry : sourceGetters.entrySet()) {

            String getterName = entry.getKey();
            String setterName = getterName.startsWith("get") ? String.format("s%s",getterName.substring(1)) : String.format("set%s",getterName.substring(2));

            Method sourceGetter = entry.getValue();
            Method targetSetter = targetSetters.get(setterName);
            Method targetGetter = targetGetters.get(getterName);

            if (targetSetter == null || targetGetter == null) {
                continue;
            }

            Class<?> sourceFieldType = sourceGetter.getReturnType();
            Class<?> targetFieldType = targetSetter.getParameterTypes()[0];
            try {
                Object sourceField = sourceGetter.invoke(fromSource);
                Object targetField = targetGetter.invoke(this);
                if (sourceField == null){
                    continue;
                }
                if (targetField == null || reset){
                    if (BecknAware.class.isAssignableFrom(targetFieldType)){
                        targetField = boCreator.create(targetFieldType);
                    }else {
                        targetField = sourceField;
                    }
                }


                if (BecknObject.class.isAssignableFrom(sourceFieldType) && BecknObject.class.isAssignableFrom(targetFieldType)) {
                    if (((BecknObject)sourceField).hasAdditionalProperties() ){
                        //IGM Will bomb!! as we need to start right from request
                        ((BecknObject)targetField).setInner(((BecknObject)sourceField).getInner());
                    }else {
                        ((BecknObject) targetField).update((BecknObject) sourceField);
                    }
                } else if (BecknObjects.class.isAssignableFrom(sourceFieldType) && BecknObjects.class.isAssignableFrom(targetFieldType)){
                    BecknObjects sourceFields = (BecknObjects) sourceField;
                    BecknObjects targetFields = (BecknObjects) targetField;
                    for (Object o : sourceFields){
                        if (o instanceof BecknObject){
                            BecknObject listElement = (BecknObject) boCreator.create(targetFields.clazz);
                            if (listElement.getClass() == o.getClass()){
                                listElement.setInner(((BecknObject) o).getInner());
                                targetFields.add(listElement);
                            }else {
                                listElement.update((BecknObject) o);
                                if (listElement instanceof BecknObjectWithId) {
                                    BecknObjectWithId lid = (BecknObjectWithId) listElement;
                                    BecknObjectsWithId lids = ((BecknObjectsWithId) targetFields);
                                    if (lids.get(lid.getId()) == null) {
                                        lids.add(lid);
                                    } else {
                                        lids.get(lid.getId()).update(lid);
                                    }
                                } else {
                                    targetFields.add(listElement);
                                }
                            }
                        }else {
                            targetFields.add(o);
                        }
                    }
                }
                targetSetter.invoke(this, targetField);
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        }


    }

    public static boolean hasCommonAncestor(Class<?> targetType, Class<?> sourceType) {
        Class<?> commonAncestor = targetType;
        while (!commonAncestor.isAssignableFrom(sourceType)){
            commonAncestor = commonAncestor.getSuperclass();
        }
        if (!commonAncestor.isAssignableFrom(sourceType) || commonAncestor == BecknObjectBase.class || commonAncestor == Object.class ){
            return false;
        }


        return true;
    }


    public static  <T extends BecknObjectBase, S extends BecknObjectBase> boolean hasCommonAncestor(T target, S source){
        Class<?> commonAncestor = target.getClass();
        Class<?> sourceType = source.getClass();
        while (!commonAncestor.isAssignableFrom(sourceType)){
            commonAncestor = commonAncestor.getSuperclass();
        }
        if (commonAncestor == BecknObjectBase.class){
            return false;
        }
        return true;
    }

    @Override
    public void set(String key, Number value) {
        super.set(key, value == null ? null : value.toString());
    }

    protected String _flat(String s){
        return _flat(s," ");
    }
    protected String _flat(String s,String prefix){
        return prefix + (ObjectUtil.isVoid(s) ? " " : s) ;
    }
}
