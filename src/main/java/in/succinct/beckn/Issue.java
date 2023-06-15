package in.succinct.beckn;

import in.succinct.beckn.Note.Notes;
import in.succinct.beckn.Representative.Complainant;
import in.succinct.beckn.Representative.Representatives;
import in.succinct.beckn.Representative.Respondent;
import in.succinct.beckn.SelectedOdrs.SelectedOdrsList;

import java.util.Date;

public class Issue extends BecknObjectWithId {
    public Complainant getComplainant(){
        return get(Complainant.class, "complainant");
    }
    public void setComplainant(Complainant complainant){
        set("complainant",complainant);
    }

    public Respondent getRespondent(){
        return get(Respondent.class, "respondent");
    }
    public void setRespondent(Respondent respondent){
        set("respondent",respondent);
    }

    public Representative getResolutionProvider(){
        return get(Representative.class, "resolution_provider");
    }
    public void setResolutionProvider(Representative resolution_provider){
        set("resolution_provider",resolution_provider);
    }


    public Representatives getRepresentatives(){
        return get(Representatives.class, "representatives");
    }
    public void setRepresentatives(Representatives representatives){
        set("representatives",representatives);
    }



    public Order getOrder(){
        return get(Order.class, "order");
    }
    public void setOrder(Order order){
        set("order",order);
    }

    public IssueCategory getIssueCategory(){
        return getEnum(IssueCategory.class, "category", IssueCategory.convertor);
    }
    public void setIssueCategory(IssueCategory issue_category){
        setEnum("category",issue_category,IssueCategory.convertor);
    }

    public IssueSubCategory getIssueSubCategory(){
        return getEnum(IssueSubCategory.class, "sub_category",IssueSubCategory.convertor);
    }
    public void setIssueSubCategory(IssueSubCategory issue_sub_category){
        setEnum("sub_category",issue_sub_category,IssueSubCategory.convertor);
    }

    public EscalationLevel getEscalationLevel(){
        return getEnum(EscalationLevel.class, "escalation_level", EscalationLevel.convertor);
    }
    public void setEscalationLevel(EscalationLevel issue_type){
        setEnum("escalation_level",issue_type , EscalationLevel.convertor);
    }

    public Time getExpectedResolutionTime(){
        return get(Time.class, "expected_resolution_time");
    }
    public void setExpectedResolutionTime(Time time){
        set("expected_resolution_time",time);
    }

    public Time getExpectedResponseTime(){
        return get(Time.class, "expected_response_time");
    }
    public void setExpectedResponseTime(Time time){
        set("expected_response_time",time);
    }

    public Status getStatus(){
        return getEnum(Status.class, "status",Status.convertor);
    }
    public void setStatus(Status status){
        setEnum("status",status,Status.convertor);
    }


    public SelectedOdrsList getSelectedOdrsList(){
        return get(SelectedOdrsList.class, "selected_odrs");
    }
    public void setSelectedOdrsList(SelectedOdrsList selected_odrs){
        set("selected_odrs",selected_odrs);
    }
    
    public Odr getFinalizedOdr(){
        return get(Odr.class, "finalized_odr");
    }
    public void setFinalizedOdr(Odr finalized_odr){
        set("finalized_odr",finalized_odr);
    }

    public boolean isSatisfied(){
        return getBoolean("satisfied");
    }
    public void setSatisfied(boolean satisfied){
        set("satisfied",satisfied);
    }

    public Resolution getResolution(){
        return get(Resolution.class, "resolution");
    }
    public void setResolution(Resolution resolution){
        set("resolution",resolution);
    }

    public Notes getNotes(){
        return get(Notes.class, "notes");
    }
    public void setNotes(Notes notes){
        set("notes",notes);
    }

    public Date getCreatedAt(){
        return getTimestamp("created_at");
    }
    public void setCreatedAt(Date created_at){
        set("created_at",created_at,TIMESTAMP_FORMAT);
    }

    public Date getUpdatedAt(){
        return getTimestamp("updated_at");
    }
    public void setUpdatedAt(Date updated_at){
        set("updated_at",updated_at,TIMESTAMP_FORMAT);
    }




    public enum EscalationLevel {
        ISSUE,GRIEVANCE,DISPUTE;
        public static final EnumConvertor<EscalationLevel>  convertor = new EnumConvertor<> (EscalationLevel.class);
    }

    public enum Status {
        OPEN,CLOSED;


        public static final EnumConvertor<Status>  convertor = new EnumConvertor<> (Status.class);
    }


}
