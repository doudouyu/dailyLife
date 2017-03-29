package life.bean.com.beanlife.bean;

/**
 * 作者 : bean on 2017/3/27/0027.
 * 注释 :
 */
public class RingDetailBean {
    private  String itemName;
    private String itemDetail;
    private  String itemTime;
    private String itemCycle;

    public RingDetailBean(String itemName, String itemDetail, String itemTime, String itemCycle) {
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.itemTime = itemTime;
        this.itemCycle = itemCycle;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String itemTime) {
        this.itemTime = itemTime;
    }

    public String getItemCycle() {
        return itemCycle;
    }

    public void setItemCycle(String itemCycle) {
        this.itemCycle = itemCycle;
    }
}
