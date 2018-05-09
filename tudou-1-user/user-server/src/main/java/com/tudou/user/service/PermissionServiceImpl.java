package com.tudou.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.tudou.common.Const;
import com.tudou.user.model.BasePermission;
import com.tudou.user.repository.BasePermissionMapper;
import com.tudou.user.repository.BaseRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>类上的注解相当于下面的配置</p>
 *
 * &lt;dubbo:service exported="false" unexported="false"
 *     interface="com.wolianw.erp.user.service.UserService"
 *     listener="" version="1.0" filter="" timeout="5000"
 *     id="com.wolianw.erp.user.service.UserService" /&gt;
 */
@Service(version = Const.DUBBO_VERSION, timeout = Const.DUBBO_TIMEOUT, filter = Const.DUBBO_FILTER)
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private BasePermissionMapper basePermissionMapper;

    @Override
    public List<BasePermission> getPermissionByUser(Long userId) {
        return basePermissionMapper.selectPermissionByUser(userId);
    }

}
