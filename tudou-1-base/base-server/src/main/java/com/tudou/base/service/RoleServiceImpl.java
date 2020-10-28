package com.tudou.base.service;

import com.tudou.base.model.Role;
import com.tudou.base.model.RoleExample;
import com.tudou.base.repository.RoleMapper;
import com.tudou.common.Page;
import com.tudou.common.PageInfo;
import com.tudou.common.date.DateUtil;
import com.tudou.common.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> page(Role role, Page page) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        if(U.isNotBlank(role.getName())){
            criteria.andNameLike(U.like(role.getName()));
        }
        roleExample.setOrderByClause(" orders asc ");
        return PageInfo.returnList(roleMapper.selectByExample(roleExample,page.bounds()));
    }

    @Override
    public int save(Role role) {
        long time = DateUtil.nowTime();
        role.setCreateTime(time)
                .setModifyTime(time);
        return roleMapper.insertSelective(role);
    }

    @Override
    public int update(Role role) {
        role.setModifyTime(DateUtil.nowTime());
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Long> ids) {
        RoleExample roleExample = new RoleExample();
        roleExample.or().andIdIn(ids);
        return roleMapper.deleteByExample(roleExample);
    }

    @Override
    public Role getById(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> list() {
        return roleMapper.selectByExample(null);
    }
}
