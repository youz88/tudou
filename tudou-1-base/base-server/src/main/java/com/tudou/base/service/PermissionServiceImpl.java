package com.tudou.base.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tudou.base.enums.EnableStatus;
import com.tudou.base.model.Permission;
import com.tudou.base.model.PermissionExample;
import com.tudou.base.model.RolePermission;
import com.tudou.base.repository.PermissionMapper;
import com.tudou.base.repository.RolePermissionMapper;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.common.date.DateUtil;
import com.tudou.common.util.A;
import com.tudou.common.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public PageInfo<Permission> listByParam(Permission permission, Page page) {
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria = permissionExample.createCriteria();
        if(U.isNotBlank(permission.getName())){
            criteria.andNameLike(U.like(permission.getName()));
        }
        permissionExample.setOrderByClause(" orders asc ");
        return PageInfo.returnList(permissionMapper.selectByExample(permissionExample,page.bounds()));
    }

    @Override
    public int save(Permission permission) {
        long time = DateUtil.nowTime();
        permission.setCreateTime(time)
                .setModifyTime(time);
        if(U.isBlank(permission.getPid())){
            permission.setPid(0L);
        }
        return permissionMapper.insertSelective(permission);
    }

    @Override
    public int update(Permission permission) {
        permission.setModifyTime(DateUtil.nowTime());
        if(U.isBlank(permission.getPid())){
            permission.setPid(0L);
        }
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public int delete(List<Long> ids) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andIdIn(ids);
        return permissionMapper.deleteByExample(permissionExample);
    }

    @Override
    public Permission getById(Long permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public List<Permission> getByType(Integer type) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andStatusEqualTo(EnableStatus.Normal.getCode()).andTypeEqualTo(type);
        return permissionMapper.selectByExample(permissionExample);
    }

    @Override
    public List<Permission> getByRoleId(Long roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Permission> getAll() {
        return permissionMapper.selectByExample(null);
    }

    @Override
    public void updateRolePermission(Long roleId, List<Long> permissionIds) {
        List<Permission> oldPermission = getByRoleId(roleId);
        Set<Long> permissionSet = Sets.newHashSet(permissionIds);
        List<Long> deleteList = Lists.newArrayList();
        List<RolePermission> insertList = Lists.newArrayList();
        for(Permission permission:oldPermission){
            Long id = permission.getId();
            if(permissionSet.contains(id)){
                permissionSet.remove(id);
            }else {
                deleteList.add(id);
            }
        }
        if(A.isNotEmpty(permissionSet)){
            for(Long id:permissionSet){
                RolePermission rolePermission = new RolePermission()
                        .setPermissionId(id)
                        .setRoleId(roleId);
                insertList.add(rolePermission);
            }
            rolePermissionMapper.batchInsert(insertList);
        }
        if(A.isNotEmpty(deleteList)){
            rolePermissionMapper.deleteByIds(roleId,deleteList);
        }
    }
}
