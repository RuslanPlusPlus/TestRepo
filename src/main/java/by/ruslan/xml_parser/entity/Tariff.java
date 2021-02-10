package by.ruslan.xml_parser.entity;

import java.time.LocalDate;

public class Tariff {
    private String name;
    private float payroll;
    private String operator;
    private String tariffication;
    private PriceList priceList = new PriceList();
    private float connectionPay;
    private float bonusInternet = 0;
    private LocalDate releaseDate;

    public Tariff(){}

    public Tariff(String name, float payroll, String operator, String tariffication, float connectionPay,
                  PriceList priceList, float bonusInternet, LocalDate releaseDate){
        this.name = name;
        this.payroll = payroll;
        this.operator = operator;
        this.connectionPay = connectionPay;
        this.bonusInternet = bonusInternet;
        this.priceList = priceList;
        this.releaseDate = releaseDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getPayroll() {
        return payroll;
    }

    public float getBonusInternet() {
        return bonusInternet;
    }

    public void setBonusInternet(float bonusInternet) {
        this.bonusInternet = bonusInternet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTariffication() {
        return tariffication;
    }

    public void setTariffication(String tariffication) {
        this.tariffication = tariffication;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public void setPayroll(float payroll) {
        this.payroll = payroll;
    }

    public float getConnectionPay() {
        return connectionPay;
    }

    public void setConnectionPay(float connectionPay) {
        this.connectionPay = connectionPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return name.equals(tariff.name) &&
                payroll == tariff.payroll &&
                operator.equals(tariff.operator) &&
                tariffication.equals(tariff.tariffication) &&
                connectionPay == tariff.connectionPay &&
                bonusInternet == tariff.bonusInternet &&
                releaseDate.equals(tariff.releaseDate) &&
                priceList.equals(tariff.priceList);
    }

    @Override
    public int hashCode() {
        int hash = 31;
        int result = 1;
        result = (int) (hash * result + payroll);
        result = (int) (hash * result + connectionPay);
        result = (int) (hash * result + bonusInternet);
        result += priceList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tariff{ ")
                .append("name= ").append(name).append('\'')
                .append(", payroll= ").append(payroll).append('\'')
                .append(", release-date= ").append(releaseDate).append('\'')
                .append(", operator= ").append(operator).append('\'')
                .append(", tariffication= ").append(tariffication).append('\'')
                .append(", connection pay= ").append(connectionPay).append('\'')
                .append(", bonus internet= ").append(bonusInternet).append('\'')
                .append(", priceList= ").append(priceList.toString()).append('\'')
                .append('}');
        return new String(builder);


    }

    public class PriceList {
        private float onNetPrice;
        private float outNetPrice;
        private float cityNetPrice;
        private float smsPrice;

        public PriceList() {
        }

        public PriceList(float onNetPrice, float outNetPrice, float cityNetPrice, float smsPrice) {
            this.onNetPrice = onNetPrice;
            this.outNetPrice = outNetPrice;
            this.cityNetPrice = cityNetPrice;
            this.smsPrice = smsPrice;
        }

        public float getOnNetPrice() {
            return onNetPrice;
        }

        public void setOnNetPrice(float onNetPrice) {
            this.onNetPrice = onNetPrice;
        }

        public float getOutNetPrice() {
            return outNetPrice;
        }

        public void setOutNetPrice(float outNetPrice) {
            this.outNetPrice = outNetPrice;
        }

        public float getCityNetPrice() {
            return cityNetPrice;
        }

        public void setCityNetPrice(float cityNetPrice) {
            this.cityNetPrice = cityNetPrice;
        }

        public float getSmsPrice() {
            return smsPrice;
        }

        public void setSmsPrice(float smsPrice) {
            this.smsPrice = smsPrice;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("PriceList{ ")
                    .append("onNetPrice= ").append(onNetPrice)
                    .append(", outNetPrice= ").append(outNetPrice)
                    .append(", cityNetPrice= ").append(cityNetPrice)
                    .append(", smsPrice= ").append(smsPrice)
                    .append('}');
            return new String(builder);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PriceList priceList = (PriceList) o;
            return onNetPrice == priceList.onNetPrice &&
                    outNetPrice == priceList.outNetPrice &&
                    cityNetPrice == priceList.cityNetPrice &&
                    smsPrice == priceList.smsPrice;
        }

        @Override
        public int hashCode() {
            int hash = 29;
            int result = 1;
            result = (int) (hash * result + onNetPrice);
            result = (int) (hash * result + outNetPrice);
            result = (int) (hash * result + cityNetPrice);
            result = (int) (hash * result + smsPrice);
            return result;
        }
    }
}
