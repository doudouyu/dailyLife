package life.bean.com.beanlife.bean;

/**
 * 作者 : bean on 2017/2/21/0021.
 * 注释 :
 */
public class MenuInfo {
    private  String title;
    private  Integer icon;

    public MenuInfo(String title, Integer icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}
