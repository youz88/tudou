package com.tudou.base.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tudou.base.model.*;
import com.tudou.base.repository.*;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.common.date.DateUtil;
import com.tudou.common.util.A;
import com.tudou.common.util.Md5Util;
import com.tudou.common.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

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
        userExample.or().andUsernameEqualTo(userData.getUsername());
        User user = A.first(userMapper.selectByExample(userExample));
        Assert.isTrue(user != null,"用户名不存在");
        Assert.isTrue(Md5Util.verify(userData.getPassword(),user.getPassword()),"密码错误");
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
    public User getById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public PageInfo<User> listByParam(User user, Page page) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(U.isNotBlank(user.getUsername())){
            criteria.andUsernameLike(U.like(user.getUsername()));
        }
        return PageInfo.returnList(userMapper.selectByExample(userExample, page.bounds()));
    }

    @Override
    public User save(User user) {
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(user.getUsername());
        List<User> userList = userMapper.selectByExample(userExample);
        Assert.isTrue(A.isEmpty(userList),"该用户已存在");

        long time = DateUtil.nowTime();
        user.setPassword(Md5Util.generate(user.getPassword()))
                .setCreateTime(time)
                .setModifyTime(time);
        userMapper.insertSelective(user);
        return user;
    }

    @Override
    public int update(User user) {
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(user.getUsername());
        List<User> userList = userMapper.selectByExample(userExample);
        Assert.isTrue(A.isEmpty(userList),"该用户已存在");
        user.setModifyTime(DateUtil.nowTime());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delete(List<Long> ids) {
        UserExample userExample = new UserExample();
        userExample.or().andIdIn(ids);
        return userMapper.deleteByExample(userExample);
    }

    @Override
    public User registe(User user) {
        save(user);
        return user;
    }

    @Override
    public List<UserRole> userRole(Long userId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.or().andUserIdEqualTo(userId);
        return userRoleMapper.selectByExample(userRoleExample);
    }

    @Override
    public int updateUserRole(Long userId, List<Long> roleIds) {
        UserRoleExample userRoleExample = new UserRoleExample();
        if (A.isNotEmpty(roleIds)) {
            List<UserRole> userRoleList = userRole(userId);
            Set<Long> userRoleIdSet = Sets.newHashSet();
            if (A.isNotEmpty(userRoleList)) {
                userRoleIdSet = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
            }
            for (Long roleId : roleIds) {
                if (userRoleIdSet.contains(roleId)) {
                    userRoleIdSet.remove(roleId);
                } else {
                    UserRole userRole = new UserRole()
                            .setRoleId(roleId)
                            .setUserId(userId);
                    userRoleMapper.insertSelective(userRole);
                }
            }
            if (A.isNotEmpty(userRoleIdSet)) {
                userRoleExample.or()
                        .andUserIdEqualTo(userId)
                        .andRoleIdIn(Lists.newArrayList(userRoleIdSet));
                return userRoleMapper.deleteByExample(userRoleExample);
            }
        } else {
            userRoleExample.or().andUserIdEqualTo(userId);
            return userRoleMapper.deleteByExample(userRoleExample);
        }
        return 0;
    }

}
