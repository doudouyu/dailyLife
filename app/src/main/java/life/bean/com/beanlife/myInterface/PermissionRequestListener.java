package life.bean.com.beanlife.myInterface;

import java.util.List;

/**
 * 作者 : bean on 2017/3/23/0023.
 * 注释 :
 */
public interface PermissionRequestListener {
    void onGrantedPermission();
    void onDeniedPermission(List<String> permissionList);
}
