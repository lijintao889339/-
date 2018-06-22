package com.teamsking.domain.service;
import com.teamsking.domain.entity.SysUser;
import com.teamsking.domain.repository.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 查询所有的用户
     * @return
     */
    public List<SysUser> list(){

        return sysUserMapper.selectAll();
    }

    /**
     * 插入用户
     * @param sysUser
     * @return
     */
    public int save(SysUser sysUser){

        return sysUserMapper.insert(sysUser);
     }

    /**
     * 删除用户
     * @param sysUser
     * @return
     *
     */
    public int remove(SysUser sysUser){

        return sysUserMapper.delete(sysUser);
    }

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    public int modify(SysUser sysUser){

        return sysUserMapper.updateByPrimaryKey(sysUser);
    }


}
