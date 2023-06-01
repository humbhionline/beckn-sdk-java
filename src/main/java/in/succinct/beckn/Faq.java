package in.succinct.beckn;

public class Faq extends BecknObject{

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

    public static class Faqs extends BecknObjects<Faq>{

    }
}
