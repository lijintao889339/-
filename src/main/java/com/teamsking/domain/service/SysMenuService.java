package com.teamsking.domain.service;

import com.teamsking.domain.entity.SysMenu;
import com.teamsking.domain.repository.SysMenuMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ynfeng
 */
@Service
@Slf4j
public class SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;

    /**
     * 获取系统菜单列表
     *
     * @return 菜单列表
     */
    public List<SysMenu> list() {
        return sysMenuMapper.selectAll();
    }

    public int save(SysMenu sysMenu){

        return sysMenuMapper.insert(sysMenu);
    }

    /**
     *删除系统菜单
     * @param sysMenu
     * @return
     */
    public int remove(SysMenu sysMenu){
        return sysMenuMapper.delete(sysMenu);
    }

    /**
     * 修改系统菜单
     * @param sysMenu
     * @return
     */
    public int modify(SysMenu sysMenu){

        return sysMenuMapper.updateByPrimaryKey(sysMenu);
    }
}
