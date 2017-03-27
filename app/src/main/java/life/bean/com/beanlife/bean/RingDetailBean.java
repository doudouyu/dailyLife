package life.bean.com.beanlife.bean;

/**
 * 作者 : bean on 2017/3/27/0027.
 * 注释 :
 */
public class RingDetailBean {
    private  String itemName;
    private String itemDetail;
    public RingDetailBean(String itemName, String itemDetail) {
        this.itemName = itemName;
        this.itemDetail = itemDetail;
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
}
