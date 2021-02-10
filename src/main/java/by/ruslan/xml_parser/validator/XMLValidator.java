package by.ruslan.xml_parser.validator;

import by.ruslan.xml_parser.exception.ParserException;
import by.ruslan.xml_parser.handler.TariffErrorHandler;
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
        String filePath;
        String schemaPath;
        try {
            filePath = getAbsolutePath(fileName);
            schemaPath = getAbsolutePath(schemaName);
        } catch (ParserException e) {
            logger.error(e.getMessage());
            return false;
        }
        File schemaLocation = new File(schemaPath);
        try {
            Schema schema = schemaFactory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(filePath);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
            result = true;
        } catch (SAXException | IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    private static String getAbsolutePath(String fileName) throws ParserException {
        ClassLoader classLoader = XMLValidator.class.getClassLoader();
        if (classLoader == null){
            throw new ParserException("Cannot load file " + fileName);
        }
        if (classLoader.getResource(fileName) == null){
            throw new ParserException("Cannot load file " + fileName);
        }
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}
