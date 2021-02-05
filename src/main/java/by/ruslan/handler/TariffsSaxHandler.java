package by.ruslan.handler;

import by.ruslan.builder.TariffsXmlType;
import by.ruslan.entity.Tariff;
import by.ruslan.exception.ParserException;
import by.ruslan.parser.LocalDateParser;
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
    private TariffsXmlType currentTag;
    private EnumSet<TariffsXmlType> withText;
    private final static String ELEMENT_TARIFF = TariffsXmlType.TARIFF.getValue();

    public TariffsSaxHandler(){
        tariffs = new HashSet<>();
        withText = EnumSet.range(TariffsXmlType.NAME, TariffsXmlType.SMS);
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
            TariffsXmlType temp = TariffsXmlType.valueOf(qName.toUpperCase().replace('-', '_'));
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
                    try {
                        LocalDate date = LocalDateParser.parseStringToDate(data);
                        currentTariff.setDate(date);
                    } catch (ParserException e) {
                        logger.info(e.getMessage());
                    }
                    break;
                }
                case ON_NET_CALL:{
                    float onNetCall = Float.parseFloat(data);
                    currentTariff.getPrices().setOnNetPrice(onNetCall);
                    break;
                }
                case OUT_NET_CALL:{
                    float outNetCall = Float.parseFloat(data);
                    currentTariff.getPrices().setOutNetPrice(outNetCall);
                    break;
                }
                case CITY_NET_CALL:{
                    float cityNetCall = Float.parseFloat(data);
                    currentTariff.getPrices().setCityNetPrice(cityNetCall);
                    break;
                }
                case SMS:{
                    float sms = Float.parseFloat(data);
                    currentTariff.getPrices().setSmsPrice(sms);
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
