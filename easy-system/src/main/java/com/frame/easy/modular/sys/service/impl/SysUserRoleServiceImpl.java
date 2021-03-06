package com.frame.easy.modular.sys.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.easy.common.constant.CommonConst;
import com.frame.easy.common.status.PermissionsStatus;
import com.frame.easy.common.status.RoleStatus;
import com.frame.easy.common.type.PermissionsType;
import com.frame.easy.modular.sys.dao.SysUserRoleMapper;
import com.frame.easy.modular.sys.model.SysPermissions;
import com.frame.easy.modular.sys.model.SysUserRole;
import com.frame.easy.modular.sys.service.SysUserRoleService;
import com.frame.easy.util.ToolUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色管理
 *
 * @author tengchong
 * @date 2018/12/7
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean saveUserRole(String userId, String roles) {
        ToolUtil.checkParams(userId);
        // 删除原权限
        remove(new QueryWrapper<SysUserRole>().eq("user_id", userId));
        if (Validator.isNotEmpty(roles)) {
            List<SysUserRole> userRoles = new ArrayList<>();
            SysUserRole userRole;
            for (String roleId : roles.split(CommonConst.SPLIT)) {
                userRole = new SysUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(userId);
                userRoles.add(userRole);
            }
            saveBatch(userRoles);
        }
        return true;
    }

    @Override
    public boolean deleteUserRoleByUserIds(String userIds) {
        return remove(new QueryWrapper<SysUserRole>().in("user_id", userIds.split(CommonConst.SPLIT)));
    }

    @Override
    public boolean deleteUserRole(String roles) {
        return remove(new QueryWrapper<SysUserRole>().in("role_id", roles.split(CommonConst.SPLIT)));
    }

    @Override
    public List<String> selectPermissionsByUserId(String userId) {
        return getBaseMapper().selectPermissionsByUserId(userId, PermissionsStatus.ENABLE.getCode());
    }

    @Override
    public List<SysPermissions> selectMenusByUserId(String userId) {
        return getBaseMapper().selectMenusByUserId(userId, PermissionsStatus.ENABLE.getCode(), PermissionsType.ENABLE.getCode());
    }


    @Override
    public List<String> selectRoleByUserId(String userId) {
        return getBaseMapper().selectRoleByUserId(userId, RoleStatus.ENABLE.getCode());
    }
}
