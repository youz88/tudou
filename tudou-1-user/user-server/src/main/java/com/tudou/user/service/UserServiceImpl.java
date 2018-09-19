package com.tudou.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.tudou.common.Const;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.common.date.DateUtil;
import com.tudou.common.util.A;
import com.tudou.common.util.U;
import com.tudou.user.model.*;
import com.tudou.user.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(version = Const.DUBBO_VERSION, timeout = Const.DUBBO_TIMEOUT, filter = Const.DUBBO_FILTER)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User login(User userData) {
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(userData.getUsername()).andPasswordEqualTo(userData.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        Assert.isTrue(A.isNotEmpty(users),"用户名或密码错误");
        User user = A.first(users);
        return user;
    }

    @Override
    public List<Role> listRoleByUser(Long userId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andUserIdEqualTo(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        Assert.isTrue(A.isNotEmpty(userRoles),"用户无登录角色");
        Set<Long> collectRole = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toSet());
        RoleExample roleExample = new RoleExample();
        roleExample.or().andIdIn(new ArrayList(collectRole));
        List<Role> roles = roleMapper.selectByExample(roleExample);
        Assert.isTrue(A.isNotEmpty(roles),"用户无登录角色");
        return roles;
    }

    @Override
    public List<Permission> listPermissionByRole(List<Role> roles) {
        List<Long> collectRole = roles.stream().map(role -> role.getId()).collect(Collectors.toList());
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.or().andRoleIdIn(collectRole);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        Assert.isTrue(A.isNotEmpty(rolePermissions),"用户无登录权限");
        Set<Long> collectPermission = rolePermissions.stream().map(rolePermission -> rolePermission.getPermissionId()).collect(Collectors.toSet());
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andIdIn(new ArrayList(collectPermission));
        permissionExample.setOrderByClause(" pid asc,orders asc ");
        List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
        Assert.isTrue(A.isNotEmpty(permissions),"用户无登录权限");
        return permissions;
    }

    @Override
    public User getByUserId(Long userId) {
        UserExample example = new UserExample();
        example.or().andIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(example);
        return A.first(users);
    }

    @Override
    public PageInfo<User> listByParam(User user, Page page) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(U.isNotBlank(user.getUsername())){
            criteria.andUsernameEqualTo(user.getUsername());
        }
        return PageInfo.returnList(userMapper.selectByExample(userExample, page.bounds()));
    }

    @Override
    public int save(User user) {
        long now = DateUtil.now().getTime();
        user.setCreateTime(now)
                .setModifyTime(now);
        return userMapper.insertSelective(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delete(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

}
