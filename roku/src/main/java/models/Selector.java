package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Selector {

    private List<Data> elementData = null;
    private List<Data> parentData = null;

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
        @SerializedName("attribute")
        Attr()
    }

    public static class Builder {

        private final Selector example;

        public Builder() {
            example = new Selector();
        }

        public Builder addElementData(Data... datas) {
            example.elementData = Stream.of(datas).collect(Collectors.toList());
            return this;
        }

        public Builder addParentData(Data... datas) {
            example.parentData = Stream.of(datas).collect(Collectors.toList());
            return this;
        }

        public Selector build() {
            return example;
        }
    }

    public static class Data {

        private LocatorStrategy using;
        private String value;

        public Data(LocatorStrategy using, String value) {
            this.using = using;
            this.value = value;
        }

        public LocatorStrategy getUsing() {
            return using;
        }

        public void setUsing(LocatorStrategy using) {
            this.using = using;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "using=" + using +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}

