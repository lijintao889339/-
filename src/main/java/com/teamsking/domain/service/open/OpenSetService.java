package com.teamsking.domain.service.open;


import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.OpenSetDtoMapper;
import com.teamsking.api.dto.open.QueryOpenSetDto;
import com.teamsking.domain.entity.open.OpenSet;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.sys.SysUser;
import com.teamsking.domain.repository.OpenSetMapper;
import java.util.List;

import com.teamsking.domain.repository.OpenUserMapper;
import com.teamsking.domain.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenSetService {


    @Autowired
    OpenSetMapper openSetMapper;
    @Autowired
    OpenUserService openUserService;
    @Autowired
    SysUserService sysUserService;


    /**
     * 获取班次-成绩管理列表
     * @return
     */
    public List<OpenSet> list(){

        return openSetMapper.selectAll();

    }


    /**
     * 根据班课id查询成绩管理列表
     * @param openId
     * @return
     */
//    public List<OpenSet> queryOpenSet(Integer openId){
//
//        //OpenSet openSetEntity = OpenSetDtoMapper.INSTANCE.insertDtoQuerySet(queryOpenSetDto);
//        OpenSet openSet = new OpenSet();
//        openSet.setDeleteStatus(2);
//        openSet.setOpenId(openId);
//        OpenSet openSetEntity = openSetMapper.selectOne(openSet);
//
//
//
//        QueryOpenSetDto queryOpenSetDtoEntity = OpenSetDtoMapper.INSTANCE.entityToQuerySet(openSetEntity);
//
//        queryOpenSetDtoEntity.setOpenSet(openSet);
//
//        //根据openId查询用户选课关系表信息
//        List<OpenUser> openUserList = openUserService.getOpenUserByOpenId(openId);
//
//        //遍历列表
//        List<Integer> userIdList = Lists.newArrayList();
//        for (OpenUser openUser : openUserList){
//            userIdList.add(openUser.getOpenId());
//        }
//
//        //根据用户IdList查询用户信息
//        List<SysUser> sysUserList = sysUserService.getSysUserByUserIdList(userIdList);
//
//        for (QueryOpenSetDto queryOpenSetDto : ){
//
//        }
//        for (SysUser sysUser : sysUserList){
//            queryOpenSetDtoEntity.setUserName(sysUser.getUserName());
//        }
//
//
//        return queryOpenSetDtoEntity;
//    }



    /**
     * 添加班次-成绩管理
     * @param openSet
     * @return
     */
    public int save(OpenSet openSet){

        return openSetMapper.insert(openSet);

    }


    /**
     * 删除班次-成绩管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openSetMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改班次-成绩管理
     * @param openSet
     * @return
     */
    public int modify(OpenSet openSet){

        return openSetMapper.updateByPrimaryKeySelective(openSet);

    }

}
