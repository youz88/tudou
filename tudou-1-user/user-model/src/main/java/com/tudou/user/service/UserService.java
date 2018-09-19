package com.tudou.user.service;

import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.user.model.Permission;
import com.tudou.user.model.Role;
import com.tudou.user.model.User;

import java.util.List;

public interface UserService {

    /** 用户登录 */
    User login(User userData);

    /** 根据用户查询角色 */
    List<Role> listRoleByUser(Long userId);

    /** 根据角色查询权限 */
    List<Permission> listPermissionByRole(List<Role> roles);

    /** 获取用户详情 */
    User getByUserId(Long userId);

    /** 分页查询用户列表 */
    PageInfo<User> listByParam(User user, Page page);

    /** 保存用户 */
    int save(User user);

    /** 更新用户 */
    int update(User user);

    /** 删除用户 */
    int delete(Long userId);
}
