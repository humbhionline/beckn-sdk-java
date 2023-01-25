package in.succinct.beckn;

public class Feedback extends BecknObjectWithId {
    public Feedback() { super();}

    public FeedbackForm getFeedbackForm(){
        return get(FeedbackForm.class,"feedback_form");
    }
    public void setFeedbackForm(FeedbackForm feedback_form){
        set("feedback_form",feedback_form.getInner());
    }

    public FeedbackUrl getFeedbackUrl(){
        return get(FeedbackUrl.class,"feedback_url");
    }
    public void setFeedbackUrl(FeedbackUrl feedback_url){
        set("feedback_url",feedback_url.getInner());
    }
}
