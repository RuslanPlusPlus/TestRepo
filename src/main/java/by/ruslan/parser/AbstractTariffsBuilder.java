package by.ruslan.parser;

import by.ruslan.entity.Tariff;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTariffsBuilder {
    protected Set<Tariff> tariffs;
    public AbstractTariffsBuilder(){
        this.tariffs = new HashSet<>();
    }

    public Set<Tariff> getTariffs() {
        return this.tariffs;
    }

    public abstract void buildSetTariffs(String fileName);
}
