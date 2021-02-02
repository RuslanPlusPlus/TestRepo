package by.ruslan.entity;

public class Tariff {
    private String name;
    private float payroll;
    private String operator; //remake to enum
    private String tariffication;
    private Prices prices = new Prices();
    private float connectionPay;

    public Tariff(){}
    //create builder for Tariff
    public Tariff(String name, float payroll, String operator, String tariffication, float connectionPay,
                    Prices prices){
        this.name = name;
        this.payroll = payroll;
        this.operator = operator;
        this.connectionPay = connectionPay;
        this.prices = prices;
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
        return "Tariff{" +
                "name='" + name + '\'' +
                ", payroll=" + payroll +
                ", operator='" + operator + '\'' +
                ", tariffication='" + tariffication + '\'' +
                ", prices=" + prices +
                ", connectionPay=" + connectionPay +
                '}';
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
    }
}
