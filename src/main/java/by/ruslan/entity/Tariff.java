package by.ruslan.entity;

import java.time.LocalDate;

public class Tariff {
    private String name;
    private float payroll;
    private String operator; //remake to enum
    private String tariffication;
    private Prices prices = new Prices();
    private float connectionPay;
    private float bonusInternet = 0;
    private LocalDate date;

    public Tariff(){}

    public Tariff(String name, float payroll, String operator, String tariffication, float connectionPay,
                    Prices prices, float bonusInternet, LocalDate date){
        this.name = name;
        this.payroll = payroll;
        this.operator = operator;
        this.connectionPay = connectionPay;
        this.bonusInternet = bonusInternet;
        this.prices = prices;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tariff{ ")
                .append("name= ").append(name).append('\'')
                .append(", payroll= ").append(payroll).append('\'')
                .append(", operator= ").append(operator).append('\'')
                .append(", tariffication= ").append(tariffication).append('\'')
                .append(", connection pay= ").append(connectionPay).append('\'')
                .append(", bonus internet= ").append(bonusInternet).append('\'')
                .append(", prices= ").append(prices.toString()).append('\'')
                .append('}');
        return new String(builder);

        //equal
    }

    public class Prices{
        private float onNetPrice;
        private float outNetPrice;
        private float cityNetPrice;
        private float smsPrice;

        public Prices() {
        }

        public Prices(float onNetPrice, float outNetPrice, float cityNetPrice, float smsPrice) {
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
            builder.append("Prices{ ")
                    .append("onNetPrice= ").append(onNetPrice)
                    .append(", outNetPrice= ").append(outNetPrice)
                    .append(", cityNetPrice= ").append(cityNetPrice)
                    .append(", smsPrice= ").append(smsPrice)
                    .append('}');
            return new String(builder);
        }
    }
}
