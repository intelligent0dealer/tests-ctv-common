package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Element {
    @SerializedName("models.ActiveElementInfo.XMLName")
    @Expose
    private XMLName xMLName;
    @SerializedName("Attrs")
    @Expose
    private List<Attr> attrs = null;
    @SerializedName("Nodes")
    @Expose
    private List<Element> nodes = null;

    @Override
    public String toString() {
        return "models.ActiveElementInfo{" +
                "xMLName=" + xMLName +
                ", attrs=" + attrs +
                ", nodes=" + nodes +
                '}';
    }

    public XMLName getXMLName() {
        return xMLName;
    }

    public void setXMLName(XMLName xMLName) {
        this.xMLName = xMLName;
    }

    public List<Attr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attr> attrs) {
        this.attrs = attrs;
    }

    public List<Element> getNodes() {
        return nodes;
    }

    public void setNodes(List<Element> nodes) {
        this.nodes = nodes;
    }

    @Generated("jsonschema2pojo")
    public static class XMLName {
        @SerializedName("Space")
        @Expose
        private String space;
        @SerializedName("Local")
        @Expose
        private String local;

        @Override
        public String toString() {
            return "models.ActiveElementInfo.XMLName{" +
                    "space='" + space + '\'' +
                    ", local='" + local + '\'' +
                    '}';
        }

        public String getSpace() {
            return space;
        }

        public void setSpace(String space) {
            this.space = space;
        }

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

    }

    @Generated("jsonschema2pojo")
    public static class Attr {
        @SerializedName("models.ActiveElementInfo.Name")
        @Expose
        private Name name;
        @SerializedName("ImplicitWait")
        @Expose
        private String value;

        @Override
        public String toString() {
            return "models.ActiveElementInfo.Attr{" +
                    "name=" + name +
                    ", value='" + value + '\'' +
                    '}';
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    @Generated("jsonschema2pojo")
    public static class Name {
        @SerializedName("Space")
        @Expose
        private String space;
        @SerializedName("Local")
        @Expose
        private String local;

        @Override
        public String toString() {
            return "models.ActiveElementInfo.Name{" +
                    "space='" + space + '\'' +
                    ", local='" + local + '\'' +
                    '}';
        }

        public String getSpace() {
            return space;
        }

        public void setSpace(String space) {
            this.space = space;
        }

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

    }
}

