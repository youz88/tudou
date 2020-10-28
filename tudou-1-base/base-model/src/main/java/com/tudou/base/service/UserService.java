package com.tudou.base.service;

import com.tudou.base.model.Permission;
import com.tudou.base.model.Role;
import com.tudou.base.model.User;
import com.tudou.base.model.UserRole;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;

import java.util.List;

public interface UserService {

    /** 用户登录 */
    User login(User userData);

    /** 根据用户查询角色 */
    List<Role> listRoleByUser(Long userId);

    /** 根据角色查询权限 */
    List<Permission> listPermissionByRole(List<Role> roles);

    /** 获取用户详情 */
    User getById(Long userId);

    /** 分页查询用户列表 */
    PageInfo<User> listByParam(User user, Page page);

    /** 保存用户 */
    User save(User user);

    /** 更新用户 */
    int update(User user);

    /** 删除用户 */
    int delete(List<Long> ids);

    /** 注册用户 */
    User registe(User userData);

    /** 查询用户角色 */
    List<UserRole> userRole(Long userId);

    /** 编辑用户角色 */
    int updateUserRole(Long userId, List<Long> roleIds);
}
