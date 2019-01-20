package com.frame.easy.modular.sys.service;

import com.frame.easy.modular.sys.model.SysUser;
import org.apache.shiro.session.Session;

import java.util.List;

/**
 * Shiro 相关接口
 *
 * @Author tengchong
 * @Date 2018/9/4
 */
public interface ShiroService {
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户信息
     */
    SysUser getSysUserByUserName(String username);

    /**
     * 查询用户权限
     * @param sysUser 用户信息
     * @return 用户信息
     */
    SysUser queryUserPermissions(SysUser sysUser);

    /**
     * 更新用户最后登录时间
     * @param userId 用户id
     */
    void updateUserLastLoginDate(Long userId);

    /**
     * 根据用户获取相同账号会话
     * @param user 正在登录的用户
     * @return 会话列表
     */
    List<Session> getLoginedSession(SysUser user);

    /**
     * 根据用户踢出相同账号其他会话
     * @param user 正在登录的用户
     * @return boolean
     */
    boolean kickOutSession(SysUser user);
}
