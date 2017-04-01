package life.bean.com.beanlife.bean;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :银行卡信息
 */
public class BankCardBean {
    private Integer icon;
    private String bankName;
    private String cardId;

    public BankCardBean(Integer icon, String bankName, String cardId) {
        this.icon = icon;
        this.bankName = bankName;
        this.cardId = cardId;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
