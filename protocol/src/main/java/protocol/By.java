package protocol;

public class By {

    public static Selector tag(String tag) {
        Selector selector = new Selector();
        selector.setTag(tag);
        return selector;
    }

    public static Selector text(String text) {
        Selector selector = new Selector();
        selector.setText(text);
        return selector;
    }
}