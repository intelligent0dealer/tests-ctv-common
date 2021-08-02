package protocol;

public class Selector {

    private String text;

    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Selector{" +
                "text='" + text + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}