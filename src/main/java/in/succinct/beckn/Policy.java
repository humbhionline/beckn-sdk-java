package in.succinct.beckn;

public class Policy extends BecknObjectWithId{
    public Descriptor getDescriptor(){
        return get(Descriptor.class, "descriptor");
    }
    public void setDescriptor(Descriptor descriptor){
        set("descriptor",descriptor);
    }

    public String getParentPolicyId(){
        return get("parent_policy_id");
    }
    public void setParentPolicyId(String parent_policy_id){
        set("parent_policy_id",parent_policy_id);
    }

    public Time getTime(){
        return get(Time.class, "time");
    }
    public void setTime(Time time){
        set("time",time);
    }
}
