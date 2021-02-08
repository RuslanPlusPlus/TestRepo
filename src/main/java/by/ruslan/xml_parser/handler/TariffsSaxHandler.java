package by.ruslan.xml_parser.handler;

import by.ruslan.xml_parser.builder.TariffsXmlTagType;
import by.ruslan.xml_parser.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TariffsSaxHandler extends DefaultHandler {

    static final Logger logger = LogManager.getLogger();
    private Set<Tariff> tariffs;
    private Tariff currentTariff;
    private TariffsXmlTagType currentTag;
    private EnumSet<TariffsXmlTagType> withText;
    private final static String ELEMENT_TARIFF = TariffsXmlTagType.TARIFF.getValue();

    public TariffsSaxHandler(){
        tariffs = new HashSet<>();
        withText = EnumSet.range(TariffsXmlTagType.NAME, TariffsXmlTagType.SMS);
    }

    public Set<Tariff> getTariffs(){
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs){
        if (ELEMENT_TARIFF.equals(qName)){
            currentTariff = new Tariff();
            for (int i = 0; i < attrs.getLength(); i++){
                switch (attrs.getQName(i)){
                    case "operator":{
                        currentTariff.setOperator(attrs.getValue(i));
                        break;
                    }
                    case "tariffication":{
                        currentTariff.setTariffication(attrs.getValue(i));
                        break;
                    }
                    case "connection-pay":{
                        float connectionPay = Float.parseFloat(attrs.getValue(i));
                        currentTariff.setConnectionPay(connectionPay);
                        break;
                    }
                    case "bonus-internet":{
                        float bonusInternet = Float.parseFloat(attrs.getValue(i));
                        currentTariff.setBonusInternet(bonusInternet);
                        break;
                    }
                }
            }
        }else {
            TariffsXmlTagType temp = TariffsXmlTagType.valueOf(qName.toUpperCase().replace('-', '_'));
            if (withText.contains(temp)){
                currentTag = temp;
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length){
        String data = new String(ch, start, length).trim();
        if (currentTag != null){
            switch (currentTag){
                case NAME: {
                    currentTariff.setName(data);
                    break;
                }
                case PAYROLL:{
                    float payroll = Float.parseFloat(data);
                    currentTariff.setPayroll(payroll);
                    break;
                }
                case RELEASE_DATE:{
                    //check date format
                    LocalDate date = LocalDate.parse(data);
                    currentTariff.setReleaseDate(date);
                    break;
                }
                case ON_NET_CALL:{
                    float onNetCall = Float.parseFloat(data);
                    currentTariff.getPriceList().setOnNetPrice(onNetCall);
                    break;
                }
                case OUT_NET_CALL:{
                    float outNetCall = Float.parseFloat(data);
                    currentTariff.getPriceList().setOutNetPrice(outNetCall);
                    break;
                }
                case CITY_NET_CALL:{
                    float cityNetCall = Float.parseFloat(data);
                    currentTariff.getPriceList().setCityNetPrice(cityNetCall);
                    break;
                }
                case SMS:{
                    float sms = Float.parseFloat(data);
                    currentTariff.getPriceList().setSmsPrice(sms);
                    break;
                }
            }
            currentTag = null;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if (ELEMENT_TARIFF.equals(qName)){
            tariffs.add(currentTariff);
            logger.info("New Tariff object added");
        }
    }

    @Override
    public void startDocument(){
        logger.info("Parsing using SAX parser started");
    }

    @Override
    public void endDocument(){
        logger.info("Parsing using SAX parser ended");
    }

}
