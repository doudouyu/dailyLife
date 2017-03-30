package life.bean.com.beanlife.bean;

import android.content.Intent;

/**
 * 作者 : bean on 2017/3/30/0030.
 * 注释 :
 */
public class CategoryInfo {
    private Integer icon;
    private String name;

    public CategoryInfo(Integer icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
