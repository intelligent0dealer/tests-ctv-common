package protocol;

public class PlatformElement {

    private String id;
    private String text;
    private boolean isVisible;
    private int focusedItemIndex;
    private boolean isFocused;
    private String uri;
    private int childCount;

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getFocusedItemIndex() {
        return focusedItemIndex;
    }

    public void setFocusedItemIndex(int focusedItemIndex) {
        this.focusedItemIndex = focusedItemIndex;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}