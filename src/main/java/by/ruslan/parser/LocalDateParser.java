package by.ruslan.parser;

import by.ruslan.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateParser {
    static final Logger logger = LogManager.getLogger();

    public static LocalDate parseStringToDate(String dateToParse) throws ParserException {
        LocalDate date;
        try {
            date = LocalDate.parse(dateToParse);
        }catch (DateTimeParseException e){
            logger.error(e.getMessage());
            throw new ParserException("Invalid date format: " + "cannot parse " + dateToParse + " to date");
        }
        return date;
    }
}
