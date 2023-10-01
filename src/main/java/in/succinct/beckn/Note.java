package in.succinct.beckn;

import in.succinct.beckn.Issue.EscalationLevel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

public class Note extends BecknObjectWithId {
    public Descriptor getDescription(){
        return get(Descriptor.class, "description");
    }
    public void setDescription(Descriptor description){
        set("description",description);
    }

    public Date getCreatedAt(){
        return getTimestamp("created_at");
    }
    public void setCreatedAt(Date created_at){
        set("created_at",created_at,TIMESTAMP_FORMAT);
    }

    public Representative getCreatedBy(){
        return get(Representative.class, "created_by");
    }
    public void setCreatedBy(Representative created_by){
        set("created_by",created_by);
    }

    public String getParentNoteId(){
        return get("parent_note_id");
    }
    public void setParentNoteId(String parent_note_id){
        set("parent_note_id",parent_note_id);
    }

    public Action getAction(){
        return get(Action.class, "action");
    }
    public void setAction(Action action){
        set("action",action);
        validateAction();
    }

    @Override
    public void setInner(JSONObject value) {
        super.setInner(value);
        validateAction();
    }

    public void validateAction(){
        Action action = getAction();
        Representative representative = getCreatedBy();
        if (action == null || representative == null){
            return;
        }

        if (representative.getRole().isComplainant() ){
            if (action.getRespondentAction() != null){
                throw  new RuntimeException("Invalid action");
            }
        }else {
            if (action.getComplainantAction() != null){
                throw  new RuntimeException("Invalid action");
            }
        }

    }

    public static class Action extends BecknObject {
        public RepresentativeAction getComplainantAction(){
            return getEnum(RepresentativeAction.class, "complainant_action",RepresentativeAction.convertor);
        }
        public void setComplainantAction(RepresentativeAction complainant_action){
            setEnum("complainant_action",complainant_action,RepresentativeAction.convertor);
            validateAction();
        }

        public RepresentativeAction getRespondentAction(){
            return getEnum(RepresentativeAction.class, "respondent_action",RepresentativeAction.convertor);
        }
        public void setRespondentAction(RepresentativeAction respondentAction){
            setEnum("respondent_action",respondentAction,RepresentativeAction.convertor);
            validateAction();
        }
        private void validateAction(){
            RepresentativeAction respondentAction = getRespondentAction();
            RepresentativeAction complainantAction = getComplainantAction();

            if (respondentAction != null && !respondentAction.isRespondent()){
                throw new RuntimeException("Invalid Action");
            }
            if (complainantAction != null && !complainantAction.isComplainant()){
                throw new RuntimeException("Invalid Action");
            }
            if (respondentAction != null && complainantAction != null){
                throw new RuntimeException("Both respondent and complainant cannot act on the same note.");
            }
        }


        @Override
        public void setInner(JSONObject value) {
            super.setInner(value);
            validateAction();
        }

        public int getCascadedLevel(){
            return getInteger("cascaded_level");
        }
        public void setCascadedLevel(int cascaded_level){
            set("cascaded_level",cascaded_level);
        }
    }



    public enum RepresentativeAction {

        OPEN(Representative.COMPLAINANT),
        ESCALATE(Representative.COMPLAINANT),
        CLOSE(Representative.COMPLAINANT),


        PROCESSING(Representative.RESPONDENT),
        CASCADED(Representative.RESPONDENT),
        RESOLVED(Representative.RESPONDENT),

        INFORMATION_REQUESTED(Representative.BOTH),
        INFORMATION_PROVIDED(Representative.BOTH);


        final int bits;
        RepresentativeAction(int representative){
            this.bits = representative;
        }
        public boolean isComplainant(){
            return (bits & Representative.COMPLAINANT) > 0;
        }
        public boolean isRespondent(){
            return (bits & Representative.RESPONDENT) > 0;
        }

        public boolean isInformation(){
            return isComplainant() && isRespondent();
        }

        public static final EnumConvertor<RepresentativeAction> convertor = new EnumConvertor<>(RepresentativeAction.class);




    }

    public static class Notes extends BecknObjectsWithId<Note> {

        public Notes() {
        }

        public Notes(JSONArray value) {
            super(value);
        }

        public Notes(String payload) {
            super(payload);
        }
    }

}
