package in.succinct.beckn;

import in.succinct.beckn.BecknObjectWithId;

import java.util.HashSet;
import java.util.Set;

public class FeedbackFormElement extends BecknObjectWithId {
    public FeedbackFormElement() { super();}

    public String getParentId(){
        return get("parent_id");
    }
    public void setParentId(String parent_id){
        set("parent_id",parent_id);
    }

    public String getQuestion(){
        return get("question");
    }
    public void setQuestion(String question){
        set("question",question);
    }

    public String getAnswer(){
        return get("answer");
    }
    public void setAnswer(String answer){
        set("answer",answer);
    }

    public String getAnswerType(){
        return get("answer_type");
    }
    static final Set<String> ANSWER_TYPE = new HashSet<String>(){{
        add("radio");
        add("checkbox");
        add("text");
    }};
    public void setAnswerType(String answer_type){
        if (!ANSWER_TYPE.contains(answer_type)){
            throw new IllegalArgumentException();
        }
        set("answer_type",answer_type);
    }
}
