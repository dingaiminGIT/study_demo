package list;

public class Data {
    private String dt;
    private String hr;
    private String count;
    private String money;

    public Data(String dt, String hr, String count, String money) {
        this.dt = dt;
        this.hr = hr;
        this.count = count;
        this.money = money;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Data{" +
                "dt='" + dt + '\'' +
                ", hr='" + hr + '\'' +
                ", count='" + count + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}





