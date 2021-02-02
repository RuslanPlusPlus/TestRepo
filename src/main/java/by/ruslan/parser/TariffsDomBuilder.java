package by.ruslan.parser;

import by.ruslan.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TariffsDomBuilder extends AbstractTariffsBuilder{

    static final Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    public TariffsDomBuilder(){
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void buildSetTariffs(String fileName) {
        Document doc;
        try {
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList studentsList = root.getElementsByTagName("tariff");
            for (int i = 0; i < studentsList.getLength(); i++) {
                Element studentElement = (Element) studentsList.item(i);
                Tariff tariff = buildTariff(studentElement);
                tariffs.add(tariff);
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace(); // log
        }
    }

    private Tariff buildTariff(Element element){
        Tariff tariff = new Tariff();
        tariff.setOperator(element.getAttribute("operator"));
        tariff.setName(getElementTextContent(element, "name"));
        tariff.setTariffication(element.getAttribute("tarrification"));
        float connectionPay = Float.parseFloat(element.getAttribute("connectionPay"));
        tariff.setConnectionPay(connectionPay);
        Tariff.Prices prices = tariff.getPrices();
        Element pricesElement =
                (Element) element.getElementsByTagName("prices").item(0);
        float onNetPrice = Float.parseFloat(getElementTextContent(pricesElement, "onNetCall"));
        prices.setOnNetPrice(onNetPrice);
        float outNetPrice = Float.parseFloat(getElementTextContent(pricesElement, "outNetCall"));
        prices.setOutNetPrice(outNetPrice);
        float cityNetPrice = Float.parseFloat(getElementTextContent(pricesElement, "cityNetCall"));
        prices.setCityNetPrice(cityNetPrice);
        float smsPrice = Float.parseFloat(getElementTextContent(pricesElement, "sms"));
        prices.setSmsPrice(smsPrice);
        return tariff;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
