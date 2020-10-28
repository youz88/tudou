package com.tudou.base.service;


import com.tudou.base.model.Role;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;

import java.util.List;

public interface RoleService {

    /** 分页查询角色列表 */
    PageInfo<Role> page(Role role, Page page);

    /** 保存角色 */
    int save(Role role);

    /** 更新角色 */
    int update(Role role);

    /** 删除角色 */
    int delete(List<Long> ids);

    /** 通过ID查询 */
    Role getById(Long roleId);

    /** 角色列表 */
    List<Role> list();

}
