package webdriver;

import models.Element;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

public class Utils {

    public static void readAttributes(List<Element> nodes, List<Element.Attr> destination) {
        if (nodes == null || nodes.size() == 0) {
            return;
        }
        for (Element node : nodes) {
            readAttributes(node.getNodes(), destination);
            destination.addAll(node.getAttrs());
        }
    }

    public static Error parseError(Retrofit retrofit, ResponseBody body) throws IOException {
        Converter<ResponseBody, Error> converter = retrofit.responseBodyConverter(Error.class, new Annotation[0]);
        Error error = null;
        try {
            error = converter.convert(body);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return error;
    }
}