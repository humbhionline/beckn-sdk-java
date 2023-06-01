package in.succinct.beckn;

import java.util.Date;

public class Organization extends BecknObject {
    public String getName(){
        return get("name");
    }
    public void setName(String name){
        set("name",name);
    }
    public String getEmail(){
        return get("email");
    }
    public void setEmail(String email){
        set("email",email);
    }

    public BecknStrings getPhones(){
        return get(BecknStrings.class,"phones");
    }
    public boolean isExtendedAttributesDisplayed(){
        return true;
    }

    public Address getAddress(){
        return extendedAttributes.get(Address.class, "address");
    }
    public void setAddress(Address address){
        extendedAttributes.set("address",address);
    }

    public String getIncomeTaxId(){
        return extendedAttributes.get("income_tax_id");
    }
    public void setIncomeTaxId(String income_tax_id){
        extendedAttributes.set("income_tax_id",income_tax_id);
    }

    public String getSalesTaxId(){
        return extendedAttributes.get("sales_tax_id");
    }
    public void setSalesTaxId(String sales_tax_id){
        extendedAttributes.set("sales_tax_id",sales_tax_id);
    }

    public Date getDateOfIncorporation(){
        return extendedAttributes.getDate("date_of_incorporation");
    }
    public void setDateOfIncorporation(Date date_of_incorporation){
        extendedAttributes.set("date_of_incorporation",date_of_incorporation, BecknObject.DATE_FORMAT);
    }

    public User getAuthorizedSignatory(){
        return extendedAttributes.get(User.class, "authorized_signatory");
    }
    public void setAuthorizedSignatory(User authorized_signatory){
        extendedAttributes.set("authorized_signatory",authorized_signatory);
    }

}
