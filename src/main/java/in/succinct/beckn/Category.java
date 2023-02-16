package in.succinct.beckn;

public class Category extends BecknObjectWithId{
    public Category(){
        super();
    }
    public Category(String  payload){
        super(payload);
    }
    public Descriptor getDescriptor(){
        return get(Descriptor.class,"descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor.getInner());
    }

    public enum CategoryCode {
        FOOD_AND_BEVERAGES,
        FASHION,
        HOME_DECOR,
        PACKAGED_FOOD,
        PACKAGED_GOODS_NON_FOOD,
    }


    public CategoryCode getCategoryCode(){
        Descriptor descriptor = getDescriptor();
        if (descriptor != null){
            String code = descriptor.getCode();
            if (code != null){
                return CategoryCode.valueOf(code);
            }
        }
        return null;
    }
    public void setCategoryCode(CategoryCode category_code){
        Descriptor descriptor = getDescriptor();
        if (descriptor == null){
            descriptor = new Descriptor();
            setDescriptor(descriptor);
        }
        descriptor.setCode(category_code.toString());
    }
}
