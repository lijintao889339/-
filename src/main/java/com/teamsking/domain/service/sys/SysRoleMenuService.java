package com.teamsking.domain.service.sys;




import com.teamsking.domain.entity.sys.SysRoleMenu;
import com.teamsking.domain.repository.SysRoleMenuMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 查询角色权限列表
     * @param
     * @return
     */
    public List<SysRoleMenu> list(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.select(sysRoleMenu);

    }

    /**
     * 添加角色权限列表
     * @param sysRoleMenu
     * @return
     */
    public int save(SysRoleMenu sysRoleMenu){

        return sysRoleMenuMapper.insert(sysRoleMenu);

    }

    /**
     * 角色权限删除操作
     * @param sysRoleMenu
     * @return
     */
    public int remove(SysRoleMenu sysRoleMenu){
        return sysRoleMenuMapper.delete(sysRoleMenu);
    }

    /**
     * 角色权限修改操作
     * @param sysRoleMenu
     * @return
     */
    public int modify(SysRoleMenu sysRoleMenu){
        return sysRoleMenuMapper.updateByPrimaryKeySelective(sysRoleMenu);
    }

}
