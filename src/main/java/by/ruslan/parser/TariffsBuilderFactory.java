package by.ruslan.parser;

import by.ruslan.exception.ParserException;

public class TariffsBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private TariffsBuilderFactory(){
    }

    public static AbstractTariffsBuilder createTariffBuilder(String typeParser) throws ParserException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM: {
                return new TariffsDomBuilder();
            }

            case SAX: {
                return new TariffsSaxBuilder();
            }

            default:
                throw new ParserException("No such builder name found");
        }
    }
}
