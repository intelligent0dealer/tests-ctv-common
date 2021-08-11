package models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sequence {

    private static final String LIT = "LIT_";

    @SerializedName("button_sequence")
    private final List<String> sequence;

    private Sequence(List<String> buttons) {
        this.sequence = buttons;
    }

    public static Sequence createSequence(RokuButton... buttons) {
        List<String> buttonsList = Arrays.stream(buttons).map(RokuButton::getValue).collect(Collectors.toList());
        return new Sequence(buttonsList);
    }

    public static Sequence createSequence(String text) {
        List<String> symbolsList = text.chars().mapToObj(symbol -> LIT + (char) symbol).collect(Collectors.toList());
        return new Sequence(symbolsList);
    }
}
