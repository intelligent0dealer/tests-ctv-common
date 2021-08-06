package protocol;

public class By {

    public static Selector attr(String name, String value) {
        Selector selector = new Selector();
        selector.addAttribute(name, value);
        return selector;
    }

    public static Selector parent(Selector parent) {
        Selector selector = new Selector();
        selector.setParent(parent);
        return selector;
    }

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