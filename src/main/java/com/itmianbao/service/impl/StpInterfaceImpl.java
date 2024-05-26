package com.itmianbao.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限认证接口扩展，Sa-Token 将从此实现类获取每个账号拥有的权限码
 *
 * @author click33
 * @since 2022-10-13
 */
@Component    // 打开此注解，保证此类被springboot扫描，即可完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        System.out.println("此时的用户权限类型"+loginType);
        if ("user".equals(loginType)) {
            // 给普通用户赋予权限
            list.add("user.get");
            list.add("user.update");
        } else if ("admin".equals(loginType)) {
            // 给管理员用户赋予权限
            list.add("user.get");
            list.add("user.update");
            list.add("user.delete");
            list.add("user.add");
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        if("user".equals(loginType)){
            list.add("user");
        }else if ("admin".equals(loginType)){
            list.add("admin");
            list.add("super-admin");
            list.add("user");
        }
        return list;
    }

}