package com.teamsking.domain.service.sys;

import com.google.common.collect.Lists;
import com.teamsking.domain.entity.course.Course;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.entity.sys.SysUserRole;
import com.teamsking.domain.entity.sys.UserTeacher;
import com.teamsking.domain.repository.SysUserMapper;
import java.util.List;

import com.teamsking.domain.repository.SysUserRoleMapper;
import com.teamsking.domain.repository.UserTeacherMapper;
import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class SysUserService extends BaseService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    UserTeacherMapper userTeacherMapper;

    /**
     * 查询所有的用户
     * @return
     */
    public List<SysUser> list(){

        return sysUserMapper.selectAll();
    }

    /**
     * 根据userIds查询用户名称
     * @param userIds
     * @return
     */
    public List<SysUser> getSysUserByUserIdList(List<Integer> userIds){

        Example userExample = new Example(SysUser.class);
        Example.Criteria cri = userExample.createCriteria();
        cri.andIn("id",userIds);
        return sysUserMapper.selectByExample(userExample);

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
     * @param id
     * @return
     *
     */
    public int remove(Integer id){

        return sysUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    public int modify(SysUser sysUser){
       return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 获取所有角色为导学老师的用户列表
     * @return
     */
    public List<SysUser> getUserNameByRoleId() {

        //获取角色为导学老师(角色id为1)的用户idList
        List<Integer> userIdList = Lists.newArrayList();
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(1);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.select(sysUserRole);
        for (SysUserRole userRole : sysUserRoleList){
            userIdList.add(userRole.getUserId());
        }

        //获取userIdList对应的用户姓名
        Example userExample = new Example(SysUser.class);
        Example.Criteria cri = userExample.createCriteria();
        cri.andIn("id",userIdList);
        return sysUserMapper.selectByExample(userExample);
    }


    /**
     * 查询所有角色为(班课)教学老师的列表
     * @return
     */
    public List<SysUser> getOpenUserNameByRoleId() {

        //获取教学老师的idList
        List<Integer> userIdList = Lists.newArrayList();
        List<UserTeacher> userTeacherList = userTeacherMapper.selectAll();
        for (UserTeacher userTeacher : userTeacherList){
            userIdList.add(userTeacher.getUserId());
        }

        /*List<Integer> userIdList = Lists.newArrayList();
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(2);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.select(sysUserRole);
        for (SysUserRole userRole : sysUserRoleList){
            userIdList.add(userRole.getUserId());
        }*/

        //获取userIdList对应的用户姓名
        Example userExample = new Example(SysUser.class);
        Example.Criteria cri = userExample.createCriteria();
        cri.andIn("id",userIdList);
        return sysUserMapper.selectByExample(userExample);
    }


}
