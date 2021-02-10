package by.ruslan.xml_parser.builder;

import by.ruslan.xml_parser.entity.Tariff;
import by.ruslan.xml_parser.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class TariffsStaxBuilder extends AbstractTariffsBuilder{

    static final Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public TariffsStaxBuilder(){
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Tariff> getTariffs(){
        return tariffs;
    }

    @Override
    public void buildSetTariffs(String fileName) throws ParserException {
        String absolutePath = getAbsolutePath(fileName);
        XMLStreamReader reader;
        String tagName;
        try(FileInputStream inputStream = new FileInputStream(new File(absolutePath))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    tagName = reader.getLocalName();
                    if (tagName.equals(TariffsXmlTagType.TARIFF.getValue())){
                        Tariff tariff = buildTariff(reader);
                        tariffs.add(tariff);
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            logger.error(e.getMessage());
            throw new ParserException(e);
        }
    }

    private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException {
        Tariff tariff = new Tariff();
        if (reader != null){
            tariff.setOperator(reader.getAttributeValue(null, TariffsXmlTagType.OPERATOR.getValue()));
            tariff.setTariffication(reader.getAttributeValue(null, TariffsXmlTagType.TARIFFICATION.getValue()));
            float connectionPay = Float.parseFloat(
                    reader.getAttributeValue(null, TariffsXmlTagType.CONNECTION_PAY.getValue()));
            tariff.setConnectionPay(connectionPay);
            String value = reader.getAttributeValue(null, TariffsXmlTagType.BONUS_INTERNET.getValue());
            if (value != null){
                float bonusInternet = Float.parseFloat(value);
                tariff.setBonusInternet(bonusInternet);
            }
            String tagName;
            while (reader.hasNext()){
                int type = reader.next();
                switch (type){
                    case XMLStreamConstants.START_ELEMENT:{
                        tagName = reader.getLocalName();
                        switch (TariffsXmlTagType.valueOf(tagName.toUpperCase().replace('-', '_'))){
                            case NAME:{
                                tariff.setName(getXmlText(reader));
                                break;
                            }
                            case PAYROLL:{
                                float payroll = Float.parseFloat(getXmlText(reader));
                                tariff.setPayroll(payroll);
                                break;
                            }
                            case RELEASE_DATE:{
                                LocalDate releaseDate = LocalDate.parse(getXmlText(reader));
                                tariff.setReleaseDate(releaseDate);
                                break;
                            }
                            case PRICE_LIST:{
                                tariff.setPriceList(buildPriceList(reader, tariff));
                                break;
                            }
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT:{
                        tagName = reader.getLocalName().toUpperCase().replace('-', '_');
                        if (TariffsXmlTagType.valueOf(tagName) == TariffsXmlTagType.TARIFF){
                            return tariff;
                        }
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <tariff>");
    }

    private Tariff.PriceList buildPriceList(XMLStreamReader reader, Tariff tariff) throws XMLStreamException {
        Tariff.PriceList priceList = tariff.getPriceList();
        int type;
        String tagName;
        while (reader.hasNext()){
            type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:{
                    tagName = reader.getLocalName();
                    switch (TariffsXmlTagType.valueOf(tagName.toUpperCase().replace('-', '_'))){
                        case ON_NET_CALL:{
                            float onNetPrice = Float.parseFloat(getXmlText(reader));
                            priceList.setOnNetPrice(onNetPrice);
                            break;
                        }
                        case OUT_NET_CALL:{
                            float outNetPrice = Float.parseFloat(getXmlText(reader));
                            priceList.setOutNetPrice(outNetPrice);
                            break;
                        }
                        case CITY_NET_CALL:{
                            float cityNetPrice = Float.parseFloat(getXmlText(reader));
                            priceList.setCityNetPrice(cityNetPrice);
                            break;
                        }
                        case SMS:{
                            float smsPrice = Float.parseFloat(getXmlText(reader));
                            priceList.setSmsPrice(smsPrice);
                            break;
                        }
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT:{
                    tagName = reader.getLocalName().toUpperCase().replace('-', '_');
                    if (TariffsXmlTagType.valueOf(tagName) == TariffsXmlTagType.PRICE_LIST){
                        return priceList;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <price-list>");
    }

    private String getXmlText(XMLStreamReader reader) throws XMLStreamException {
        String text = "";
        if (reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
