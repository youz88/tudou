package com.tudou.user.service;

import com.tudou.user.model.BasePermission;

import java.util.List;

public interface PermissionService {

    /**
     * 查询用户权限
     * @param userId 用户ID
     * @return
     */
    List<BasePermission> getPermissionByUser(Long userId);
}
