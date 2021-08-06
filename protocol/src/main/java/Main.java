import models.Element;
import models.Selector;
import protocol.Configuration;
import protocol.Configuration.Builder;
import protocol.Platform;
import webdriver.RokuDriver;
import webdriver.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static models.Selector.Data.attr;

public class Main {

    public static void main(String[] args) {

        Configuration configuration = new Builder()
                .platform(Platform.ROKU)
                .driverUrl("http://localhost:9000")
                .clientIpAddress("192.168.0.4")
                .log(true)
                .build();

        RokuDriver driver = null;
        try {
            //1. initialize driver
            driver = new RokuDriver(
                    configuration.getDriverUrl(),
                    configuration.getClientIpAddress(),
                    configuration.isLogEnabled()
            );

            //2. find element by specific selector
            Selector.Builder builder = new Selector.Builder();
            Selector selector = builder
                    .addElementData(attr("focused", "true"))
//                    .addElementData(tag("Label"))
                    .addParentData(attr("name", "home_screen"))
                    .addParentData(attr("extends", "MsTvGroup"))
//                    .addParentData(tag("MsTvButton"))
                    .build();
            Element element = driver.findElement(selector);
            System.out.println(element);

            //3. read 'text' attribute
            List<Element.Attr> attributes = new ArrayList<>();
            Utils.readAttributes(Collections.singletonList(element), attributes);

            Optional<String> optional = element.getAttrs().stream()
                    .filter(Main::filterAttr)
                    .map(Element.Attr::getValue)
                    .findFirst();
            String name = optional.orElseGet(() -> optional.orElse(""));

            System.out.println("!!! " + name);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (driver != null) {
                driver.closeSession();
            }
        }
    }

    static boolean filterAttr(Element.Attr attr) {
        if (attr.getName() != null && attr.getName().getLocal() != null) {
            return attr.getName().getLocal().equals("name");
        }
        return false;
    }
}

/*
* {
  "elementData": [
    {
    "using": "attr",
    "attribute": "focused",
    "value": "true"
   },
   {
       "using": "tag",
       "value": "Label"
   }
  ],
  "parentData": [
    {
        "using": "attr",
        "attribute": "name",
        "value": "settings_screen"
    },
    {
        "using": "tag",
        "value": "MsTvButton"
    }
 ]
}*/