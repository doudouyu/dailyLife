package life.bean.com.beanlife.bean;

/**
 * 作者 : bean on 2017/4/19/0019.
 * 注释 :
 */
public class AccountRingEvent {
    private  String itemName;
    private String itemDetail;
    private  String itemTime;
    private String itemCycle;

    public AccountRingEvent(String itemName, String itemDetail, String itemTime, String itemCycle) {
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.itemTime = itemTime;
        this.itemCycle = itemCycle;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public String getItemTime() {
        return itemTime;
    }

    public String getItemCycle() {
        return itemCycle;
    }
}
