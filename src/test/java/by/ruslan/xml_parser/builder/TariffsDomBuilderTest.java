package by.ruslan.xml_parser.builder;

import by.ruslan.xml_parser.entity.Tariff;
import by.ruslan.xml_parser.exception.ParserException;
import by.ruslan.xml_parser.factory.TariffsBuilderFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class TariffsDomBuilderTest {

    private TariffsDomBuilder domBuilder;
    private String fileName;
    private Set<Tariff> expectedSet;

    @BeforeMethod
    public void setUp() {
        domBuilder = (TariffsDomBuilder) TariffsBuilderFactory.createTariffBuilder("dom");
        fileName = "testData/tariffsTest.xml";
        expectedSet = new HashSet<>();
        Tariff ComfortM = new Tariff();
        ComfortM.setName("Comfort M");
        ComfortM.setConnectionPay(2);
        ComfortM.setBonusInternet(10);
        ComfortM.setPayroll((float) 21.73);
        ComfortM.setReleaseDate(LocalDate.parse("2018-05-23"));
        ComfortM.setTariffication("minute tarrification");
        ComfortM.setOperator("A1");
        Tariff.PriceList comfortMPriceList = ComfortM.getPriceList();
        comfortMPriceList.setOnNetPrice(0);
        comfortMPriceList.setOutNetPrice((float) 0.5);
        comfortMPriceList.setCityNetPrice((float) 0.104);
        comfortMPriceList.setSmsPrice((float) 0.078);
        ComfortM.setPriceList(comfortMPriceList);
        expectedSet.add(ComfortM);

        Tariff ComfortS = new Tariff();
        ComfortS.setName("Comfort S");
        ComfortS.setConnectionPay(0);
        ComfortS.setPayroll((float) 13.83);
        ComfortS.setReleaseDate(LocalDate.parse("2019-12-08"));
        ComfortS.setTariffication("12-second tarrification");
        ComfortS.setOperator("A1");
        Tariff.PriceList comfortSPriceList = ComfortS.getPriceList();
        comfortSPriceList.setOnNetPrice(0);
        comfortSPriceList.setOutNetPrice((float) 0.85);
        comfortSPriceList.setCityNetPrice((float) 0.104);
        comfortSPriceList.setSmsPrice((float) 0.078);
        ComfortS.setPriceList(comfortSPriceList);
        expectedSet.add(ComfortS);

    }

    @AfterMethod
    public void tearDown() {
        domBuilder = null;
        expectedSet = null;
        fileName = null;
    }

    @Test
    public void testBuildSetTariffs() throws ParserException {
        domBuilder.buildSetTariffs(fileName);
        Set<Tariff> actualSet = domBuilder.getTariffs();
        assertEquals(actualSet, expectedSet);
    }
}