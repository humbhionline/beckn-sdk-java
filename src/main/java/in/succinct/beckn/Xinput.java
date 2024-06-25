package in.succinct.beckn;

import org.json.simple.JSONObject;

public class Xinput extends BecknObject{
    public Xinput() {
    }

    public Xinput(String payload) {
        super(payload);
    }

    public Xinput(JSONObject object) {
        super(object);
    }

    public Form getForm(){
        return get(Form.class, "form");
    }
    public void setForm(Form form){
        set("form",form);
    }

    public static class Head extends BecknObject {
        public Head() {
        }

        public Head(String payload) {
            super(payload);
        }

        public Head(JSONObject object) {
            super(object);
        }
        public Descriptor getDescriptor(){
            return get(Descriptor.class, "descriptor");
        }
        public void setDescriptor(Descriptor descriptor){
            set("descriptor",descriptor);
        }

        public Index getIndex(){
            return get(Index.class, "index");
        }
        public void setIndex(Index index){
            set("index",index);
        }
        
        public BecknStrings getHeadings(){
            return get(BecknStrings.class, "headings");
        }
        public void setHeadings(BecknStrings headings){
            set("headings",headings);
        }

    }

    public static class Index extends BecknObject{
        public Index() {
        }

        public Index(String payload) {
            super(payload);
        }

        public Index(JSONObject object) {
            super(object);
        }

        public int getMin(){
            return getInteger("min");
        }
        public void setMin(int min){
            set("min",min);
        }
        public int getCur(){
            return getInteger("cur");
        }
        public void setCur(int cur){
            set("cur",cur);
        }

        public int getMax(){
            return getInteger("max");
        }
        public void setMax(int max){
            set("max",max);
        }

    }
}
