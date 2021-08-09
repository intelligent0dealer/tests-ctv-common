package protocol;

import java.util.LinkedHashMap;
import java.util.Map;

public class Selector {

    private final Map<String, String> attributes = new LinkedHashMap<>();
    private Selector parent;
    private String text;
    private String tag;

    Selector() {

    }

    public Selector addAttribute(String name, String value) {
        if (name != null && !name.isEmpty() && value != null && !value.isEmpty()) {
            attributes.put(name, value);
        }
        return this;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Selector getParent() {
        return parent;
    }

    public Selector setParent(Selector parent) {
        this.parent = parent;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Selector setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getText() {
        return text;
    }

    public Selector setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "Selector{" +
                "text='" + text + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}