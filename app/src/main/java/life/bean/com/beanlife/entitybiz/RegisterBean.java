package life.bean.com.beanlife.entitybiz;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public class RegisterBean {
    private String Name;
    private String password;
    private String password1;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }
}
