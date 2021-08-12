package models;

public enum RokuButton {
    UP("up"),
    DOWN("down"),
    RIGHT("right"),
    LEFT("left"),
    OK("select"),
    BACK("back"),
    REFRESH("instantReplay"),
    FAST_FORWARD("fwd"),
    REWIND("rev");

    private final String value;

    RokuButton(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}