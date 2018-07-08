package com.teamsking.domain.service.sys;


import com.teamsking.domain.entity.sys.SysRoleMenu;
import com.teamsking.domain.repository.SysRoleMenuMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 查询角色权限列表
     *
     * @return
     */
    public List<SysRoleMenu> list() {
        return sysRoleMenuMapper.selectAll();

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
     * @param id
     * @return
     */
    public int remove(Integer id){
        return sysRoleMenuMapper.deleteByPrimaryKey(id);
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
