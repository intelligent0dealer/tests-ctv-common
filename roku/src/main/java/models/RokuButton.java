package models;

public enum RokuButton {
    UP("up"),
    DOWN("down"),
    RIGHT("right"),
    LEFT("left"),
    OK("enter"),
    BACK("back"),
    REFRESH("instantReplay");

    private final String value;

    RokuButton(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}