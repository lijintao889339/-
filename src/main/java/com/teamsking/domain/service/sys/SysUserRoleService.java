package com.teamsking.domain.service.sys;

import com.teamsking.domain.entity.sys.SysUserRole;
import com.teamsking.domain.repository.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询所有用户权限
     * @return
     */
    public List<SysUserRole> list(SysUserRole sysUserRole){

        return sysUserRoleMapper.select(sysUserRole);
    }

    /**
     * 用户权限添加操作
     * @param sysUserRole
     * @return
     */
    public int save(SysUserRole sysUserRole) {

        return sysUserRoleMapper.insert(sysUserRole);
    }

    /**
     * 用户权限删除操作
     * @param sysUserRole
     * @return
     */
    public int remove(SysUserRole sysUserRole) {

        return sysUserRoleMapper.delete(sysUserRole);
    }

    /**
     * 用户权限修改操作
     * @param sysUserRole
     * @return
     */
    public int modify(SysUserRole sysUserRole) {

        return sysUserRoleMapper.updateByPrimaryKeySelective(sysUserRole);
    }
}
