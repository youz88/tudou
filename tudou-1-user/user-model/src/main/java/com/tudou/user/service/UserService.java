package com.tudou.user.service;

import com.tudou.user.model.BaseUser;

public interface UserService {

    /** 注册 */
    void registe(BaseUser baseUser);

    /** 登录 */
    BaseUser login(BaseUser baseUser);
}
