package by.ruslan;

import by.ruslan.entity.Tariff;
import by.ruslan.exception.ParserException;
import by.ruslan.parser.TariffsBuilderFactory;
import by.ruslan.parser.TariffsDomBuilder;
import by.ruslan.validator.XMLValidator;

public class Main {
    public static void main(String[] args) {
        System.out.println(XMLValidator.isValid("data/tariffs.xml", "data/tariffs.xsd"));
        try {
            TariffsDomBuilder builder = (TariffsDomBuilder) TariffsBuilderFactory.createTariffBuilder("dom");
            builder.buildSetTariffs("data/tariffs.xml");
            System.out.println(builder.getTariffs());
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

}
