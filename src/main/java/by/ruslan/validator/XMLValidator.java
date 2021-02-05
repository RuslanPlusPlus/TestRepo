package by.ruslan.validator;

import by.ruslan.handler.TariffErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {

    static final Logger logger = LogManager.getLogger();

    public static boolean isXmlValid(String fileName, String schemaName){
        boolean result = false;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            //add sax parser
            Schema schema = schemaFactory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
            result = true;
        } catch (SAXException | IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}
