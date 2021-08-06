package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Element {
    @Expose
    @SerializedName("Attrs")
    private final List<Attr> attrs = null;

    @Expose
    @SerializedName("Nodes")
    private final List<Element> nodes = null;

    @Expose
    @SerializedName("XMLName")
    private XMLName XMLName;

    @Override
    public String toString() {
        return "Element{" +
                "XMLName=" + XMLName +
                ", attrs=" + attrs +
                ", nodes=" + nodes +
                '}';
    }

    public List<Attr> getAttrs() {
        return attrs;
    }

    public List<Element> getNodes() {
        return nodes;
    }

    public XMLName getXMLName() {
        return XMLName;
    }

    public static class XMLName {

        @Expose
        @SerializedName("Space")
        private String space;

        @Expose
        @SerializedName("Local")
        private String local;

        public String getLocal() {
            return local;
        }

        public String getSpace() {
            return space;
        }

        @Override
        public String toString() {
            return "XMLName{" +
                    "space='" + space + '\'' +
                    ", local='" + local + '\'' +
                    '}';
        }
    }

    public static class Attr {

        @Expose
        @SerializedName("Name")
        private Name name;

        @Expose
        @SerializedName("Value")
        private String value;

        public Name getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Attr{" +
                    "name=" + name +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class Name {
        @Expose
        @SerializedName("Space")
        private String space;

        @Expose
        @SerializedName("Local")
        private String local;

        public String getLocal() {
            return local;
        }

        public String getSpace() {
            return space;
        }

        @Override
        public String toString() {
            return "Name{" +
                    "space='" + space + '\'' +
                    ", local='" + local + '\'' +
                    '}';
        }
    }
}