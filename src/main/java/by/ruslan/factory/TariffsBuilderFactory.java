package by.ruslan.factory;

import by.ruslan.builder.AbstractTariffsBuilder;
import by.ruslan.builder.TariffsDomBuilder;
import by.ruslan.builder.TariffsSaxBuilder;
import by.ruslan.exception.ParserException;

public class TariffsBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private TariffsBuilderFactory(){
    }

    public static AbstractTariffsBuilder createTariffBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM: {
                return new TariffsDomBuilder();
            }

            case SAX: {
                return new TariffsSaxBuilder();
            }
            default:
                throw new EnumConstantNotPresentException(type.getClass(), type.name());
        }
    }
}
