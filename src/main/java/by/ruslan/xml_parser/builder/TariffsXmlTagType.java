package by.ruslan.xml_parser.builder;

public enum TariffsXmlTagType {
    TARIFFS("tariffs"),
    TARIFF("tariff"),
    OPERATOR("operator"),
    TARIFFICATION("tariffication"),
    CONNECTION_PAY("connection-pay"),
    BONUS_INTERNET("bonus-internet"),
    NAME("name"),
    PAYROLL("payroll"),
    RELEASE_DATE("release-date"),
    ON_NET_CALL("on-net-call"),
    OUT_NET_CALL("out-net-call"),
    CITY_NET_CALL("city-net-call"),
    SMS("sms"),
    PRICE_LIST("price-list");

    private String value;

    TariffsXmlTagType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
