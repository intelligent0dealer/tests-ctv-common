package models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static models.Selector.LocatorStrategy.*;

public class Selector {

    private final ArrayList<Data> elementData = new ArrayList<>();
    private final ArrayList<Data> parentData = new ArrayList<>();

    private Selector() {
    }

    public List<Data> getElementData() {
        return elementData;
    }

    public List<Data> getParentData() {
        return parentData;
    }

    @Override
    public String toString() {
        return "Selector{" +
                "elementData=" + elementData +
                ", parentData=" + parentData +
                '}';
    }

    public enum LocatorStrategy {
        @SerializedName("text")
        Text(),
        @SerializedName("tag")
        Tag(),
        @SerializedName("attr")
        Attr()
    }

    public static class Builder {

        private final Selector example;

        public Builder() {
            example = new Selector();
        }

        public Builder addElementData(Data... data) {
            example.elementData.addAll(Stream.of(data).collect(Collectors.toList()));
            return this;
        }

        public Builder addParentData(Data... data) {
            example.parentData.addAll(Stream.of(data).collect(Collectors.toList()));
            return this;
        }

        public Selector build() {
            return example;
        }
    }

    public static class Data {

        private LocatorStrategy using;
        private String value;
        private String attribute;

        public static Data text(String text) {
            Data data = new Data();
            data.setUsing(Text);
            data.setValue(text);
            return data;
        }

        public static Data attr(String attribute, String value) {
            Data data = new Data();
            data.setUsing(Attr);
            data.setAttribute(attribute);
            data.setValue(value);
            return data;
        }

        public static Data tag(String tag) {
            Data data = new Data();
            data.setUsing(Tag);
            data.setValue(tag);
            return data;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public LocatorStrategy getUsing() {
            return using;
        }

        public void setUsing(LocatorStrategy using) {
            this.using = using;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "using=" + using +
                    ", value='" + value + '\'' +
                    ", attribute='" + attribute + '\'' +
                    '}';
        }
    }
}

