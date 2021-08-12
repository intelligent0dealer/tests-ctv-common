package models;

import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        List<String> symbolsList = text.chars()
                .mapToObj(symbol -> {
                    try {
                        return URLEncoder.encode(LIT + (char) symbol, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        return new Sequence(symbolsList);
    }
}