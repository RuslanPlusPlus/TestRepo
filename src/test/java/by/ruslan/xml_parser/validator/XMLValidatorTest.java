package by.ruslan.xml_parser.validator;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XMLValidatorTest {

    String fileName;
    String schemaName;

    @BeforeMethod
    public void setUp() {
        fileName = "data/tariffs.xml";
        fileName = "data/tariffs.xsd";
    }

    @AfterMethod
    public void tearDown() {
        fileName = null;
        schemaName = null;
    }

    @Test
    public void testIsXmlValid() {
        boolean actualResult = XMLValidator.isXmlValid(fileName, schemaName);
        assertTrue(actualResult);
    }
}