package by.ruslan.builder;

import by.ruslan.handler.TariffErrorHandler;
import by.ruslan.handler.TariffsSaxHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class TariffsSaxBuilder extends AbstractTariffsBuilder{

    static final Logger logger = LogManager.getLogger();
    private XMLReader xmlReader;
    private TariffsSaxHandler saxHandler;

    public TariffsSaxBuilder(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            saxHandler = new TariffsSaxHandler();
            xmlReader.setContentHandler(saxHandler);
            xmlReader.setErrorHandler(new TariffErrorHandler());
        } catch (ParserConfigurationException | SAXException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void buildSetTariffs(String fileName) {
        try {
            xmlReader.parse(fileName);
        } catch (IOException | SAXException e) {
            logger.error(e.getMessage());
        }
        tariffs = saxHandler.getTariffs();
    }
}
