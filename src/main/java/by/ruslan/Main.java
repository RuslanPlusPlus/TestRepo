package by.ruslan;

import by.ruslan.builder.TariffsSaxBuilder;
import by.ruslan.entity.Tariff;
import by.ruslan.exception.ParserException;
import by.ruslan.factory.TariffsBuilderFactory;
import by.ruslan.builder.TariffsDomBuilder;
import by.ruslan.parser.LocalDateParser;
import by.ruslan.validator.XMLValidator;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        /*if (XMLValidator.isXmlValid("data/tariffs.xml", "data/tariffs.xsd")){
            TariffsSaxBuilder saxBuilder = (TariffsSaxBuilder) TariffsBuilderFactory.createTariffBuilder("sax");
            saxBuilder.buildSetTariffs("data/tariffs.xml");
            Set<Tariff> tariffSet = saxBuilder.getTariffs();
            for (Tariff tariff: tariffSet){
                System.out.println(tariff);
            }
        }*/

        try {
            System.out.println(LocalDateParser.parseStringToDate("2015-10-30"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

}
