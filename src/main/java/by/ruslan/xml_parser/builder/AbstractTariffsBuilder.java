package by.ruslan.xml_parser.builder;

import by.ruslan.xml_parser.entity.Tariff;
import by.ruslan.xml_parser.exception.ParserException;

import java.io.File;
import java.net.URL;
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

    public abstract void buildSetTariffs(String fileName) throws ParserException;

    protected String getAbsolutePath(String fileName) throws ParserException{
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader.getResource(fileName) == null){
            throw new ParserException("Cannot load file " + fileName);
        }
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}
