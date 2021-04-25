package in.succinct.beckn;

import org.json.simple.JSONArray;

public class BecknStrings extends BecknAware<JSONArray> {
    public BecknStrings(){
        super(new JSONArray());
    }

    public void add(String t){
        getInner().add(t);
    }
    public void remove(String t){
        getInner().remove(t);
    }

    public String get(int index){
        return (String)getInner().get(index);
    }

    public int size(){
        return getInner().size();
    }

    @Override
    public boolean equals(Object o) {
        if ( o != null && getClass() == o.getClass() ){
            BecknStrings other = (BecknStrings)o;
            return (other == o || other.getInner().equals(getInner()));
        }
        return false;
    }

}
