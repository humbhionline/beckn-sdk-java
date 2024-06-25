package in.succinct.beckn;

public class BankAccount extends BecknObject implements AddressHolder{
    public BankAccount() {
    }
    public String getPrefix(){
        return get("prefix");
    }
    public void setPrefix(String prefix){
        set("prefix",prefix);
    }
    private String key(String name){
        String p = getPrefix();
        if (p == null){
            return "";
        }else {
            return String.format("%s_%s",p,name);
        }
    }

    public Address getAddress(){
        return AddressHolder.super.getAddress();
    }
    public void setAddress(Address address){
        AddressHolder.super.setAddress(address);
    }

    public City getCity(){
        return get(City.class, "city");
    }
    public void setCity(City city){
        set("city",city);
    }
    public State getState(){
        return get(State.class, "state");
    }
    public void setState(State state){
        set("state",state);
    }
    public Country getCountry(){
        return get(Country.class, "country");
    }
    public void setCountry(Country country){
        set("country",country);
    }

    public String getPinCode(){
        return get("pin_code");
    }
    public void setPinCode(String pin_code){
        set("pin_code",pin_code);
    }

    public String getName() {
        return get(key("name"));
    }

    public void setName(String name) {
        set(key("name"), name);
    }


    public String getAccountNo() {
        return get(key("account_no"));
    }

    public void setAccountNo(String account_no) {
        set(key("account_no"), account_no);
    }

    public String getBankCode() {
        return get(key("bank_code"));
    }

    public void setBankCode(String bank_code) {
        set(key("bank_code"), bank_code);
    }

    public String getBankName(){
        return get("bank_name");
    }
    public void setBankName(String bank_name){
        set("bank_name",bank_name);
    }


    public String getVpa() {
        return get(key("virtual_payment_address"));
    }

    public void setVpa(String vpa) {
        set(key("virtual_payment_address"), vpa);
    }
}