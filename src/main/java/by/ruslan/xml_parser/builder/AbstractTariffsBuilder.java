package by.ruslan.xml_parser.builder;

import by.ruslan.xml_parser.entity.Tariff;
import by.ruslan.xml_parser.exception.ParserException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTariffsBuilder {
    protected Set<Tariff> tariffs;
    public AbstractTariffsBuilder(){
        this.tariffs = new HashSet<>();
    }

    public AbstractTariffsBuilder(Set<Tariff> tariffs){
        this.tariffs = tariffs;
    }

    public Set<Tariff> getTariffs() {
        return this.tariffs;
    }

    public abstract void buildSetTariffs(String fileName);
}
