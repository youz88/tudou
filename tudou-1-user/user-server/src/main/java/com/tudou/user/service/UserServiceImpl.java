package com.tudou.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.tudou.common.Const;
import com.tudou.common.date.DateUtil;
import com.tudou.common.util.A;
import com.tudou.common.util.U;
import com.tudou.user.model.BaseUser;
import com.tudou.user.model.BaseUserExample;
import com.tudou.user.repository.BaseUserMapper;
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
public class UserServiceImpl implements UserService{

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public void registe(BaseUser baseUser) {
        BaseUserExample example = new BaseUserExample();
        example.or().andUserNameEqualTo(baseUser.getUserName());
        int i = baseUserMapper.countByExample(example);
        U.assertException(U.greater0(i),"注册失败，该用户名已存在");
        baseUser.setCreateTime(DateUtil.now().getTime());
        baseUserMapper.insertSelective(baseUser);
    }

    @Override
    public BaseUser login(BaseUser baseUser) {
        BaseUserExample example = new BaseUserExample();
        example.or().andUserNameEqualTo(baseUser.getUserName()).andPasswordEqualTo(baseUser.getPassword());
        List<BaseUser> baseUserList = baseUserMapper.selectByExample(example);
        U.assertEmpty(baseUserList,"用户名或密码错误");
        return A.first(baseUserList);
    }

}
