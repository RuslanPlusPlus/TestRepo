package by.ruslan.xml_parser.builder;

import by.ruslan.xml_parser.entity.Tariff;
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
import java.time.LocalDate;
import java.util.Set;

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

    public TariffsDomBuilder(Set<Tariff> tariffs){
        super(tariffs);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void buildSetTariffs(String fileName){
        Document doc;
        try {
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList studentsList = root.getElementsByTagName(TariffsXmlTagType.TARIFF.getValue());
            for (int i = 0; i < studentsList.getLength(); i++) {
                Element studentElement = (Element) studentsList.item(i);
                Tariff tariff = buildTariff(studentElement);
                tariffs.add(tariff);
            }
        } catch (IOException | SAXException e) {
            logger.error(e.getMessage());
        }
    }

    private Tariff buildTariff(Element element){
        Tariff tariff = new Tariff();
        if (element != null) {
            String operator = element.getAttribute(TariffsXmlTagType.OPERATOR.getValue());
            float connectionPay = Float.parseFloat(
                    element.getAttribute(TariffsXmlTagType.CONNECTION_PAY.getValue()));
            String tariffication = element.getAttribute(TariffsXmlTagType.TARIFFICATION.getValue());
            String name = getElementTextContent(element, TariffsXmlTagType.NAME.getValue());
            float payroll = Float.parseFloat(
                    getElementTextContent(element, TariffsXmlTagType.PAYROLL.getValue()));
            String stringBonusInternet = element.getAttribute(TariffsXmlTagType.BONUS_INTERNET.getValue());
            tariff.setOperator(operator);
            tariff.setConnectionPay(connectionPay);
            tariff.setName(name);
            tariff.setTariffication(tariffication);
            if (!stringBonusInternet.isEmpty()){
                float bonusInternet = Float.parseFloat(stringBonusInternet);
                tariff.setBonusInternet(bonusInternet);
            }
            tariff.setPayroll(payroll);
            String dateString = getElementTextContent(element, TariffsXmlTagType.RELEASE_DATE.getValue());
            //check date format
            LocalDate date = LocalDate.parse(dateString);
            tariff.setReleaseDate(date);
            Tariff.PriceList priceList = buildPriceList(element, tariff);
            tariff.setPriceList(priceList);
            logger.info("Tariff object is successfully built");
        }else {
            logger.warn("Null passed to buildTariff method. Impossible to build object");
        }
        return tariff;
    }

    private Tariff.PriceList buildPriceList(Element element, Tariff tariff){
        Tariff.PriceList priceList = tariff.getPriceList();
        if (element != null){
            Element pricesElement =
                    (Element) element.getElementsByTagName(TariffsXmlTagType.PRICE_LIST.getValue()).item(0);
            float onNetPrice = Float.parseFloat(
                    getElementTextContent(pricesElement, TariffsXmlTagType.ON_NET_CALL.getValue()));
            float outNetPrice = Float.parseFloat(
                    getElementTextContent(pricesElement, TariffsXmlTagType.OUT_NET_CALL.getValue()));
            float cityNetPrice = Float.parseFloat(
                    getElementTextContent(pricesElement, TariffsXmlTagType.CITY_NET_CALL.getValue()));
            float smsPrice = Float.parseFloat(
                    getElementTextContent(pricesElement, TariffsXmlTagType.SMS.getValue()));
            priceList.setOnNetPrice(onNetPrice);
            priceList.setOutNetPrice(outNetPrice);
            priceList.setCityNetPrice(cityNetPrice);
            priceList.setSmsPrice(smsPrice);
        }else {
            logger.warn("Null passed to buildTariff method. Impossible to build object");
        }
        return priceList;
    }

    private static String getElementTextContent(Element element, String tagName) {
        NodeList nList = element.getElementsByTagName(tagName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
