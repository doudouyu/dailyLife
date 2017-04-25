package life.bean.com.beanlife.bean;
/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class RecordDetail {
    private Integer Icon;
    private String type;
    private String detail;
    private int money;

    public RecordDetail(Integer icon, String type, String detail, int money) {
        Icon = icon;
        this.detail = detail;
        this.type = type;
        this.money = money;
    }

    public Integer getIcon() {
        return Icon;
    }

    public void setIcon(Integer icon) {
        Icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}