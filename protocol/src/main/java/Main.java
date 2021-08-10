import models.Element;
import models.Selector;
import protocol.Configuration;
import protocol.Configuration.Builder;
import protocol.Platform;
import webdriver.RokuDriver;
import webdriver.Utils;

import static models.Selector.Data.attr;
import static models.Selector.Data.tag;

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
                    4000L,
                    250L,
                    configuration.isLogEnabled()
            );

            //2. find element by specific selector
            Selector.Builder builder = new Selector.Builder();
            Selector selector = builder
                    .addElementData(attr("focused", "true"))
                    .addParentData(attr("extends", "MsTvScreen"))
                    .addParentData(tag("MsTvButton"))
                    .build();
            Element element = driver.findElement(selector);
            System.out.println(element);

            //3. read 'text' attribute
//            List<Element.Attr> attributes = new ArrayList<>();
//            Utils.readAttributes(Collections.singletonList(element), attributes);

            String name = element.getAttrs().stream()
                    .filter(attr -> Utils.filterAttr(attr, "name"))
                    .map(Element.Attr::getValue)
                    .findFirst()
                    .orElse("");

            System.out.println("!!! " + name);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (driver != null) {
                driver.closeSession();
            }
        }
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