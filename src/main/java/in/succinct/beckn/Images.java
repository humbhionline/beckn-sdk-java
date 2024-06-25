package in.succinct.beckn;

public class Images extends BecknObjects<Image>{


    public void add(String o) {
        Image image = new Image();
        image.setUrl(o);
        super.add(image);
    }
}
