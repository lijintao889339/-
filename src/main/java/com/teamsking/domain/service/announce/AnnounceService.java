package com.teamsking.domain.service.announce;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.announce.AnnounceDto;
import com.teamsking.api.dto.announce.AnnounceDtoMapper;
import com.teamsking.domain.entity.announce.Announce;
import com.teamsking.domain.entity.announce.AnnounceUser;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.sys.UserTeacher;
import com.teamsking.domain.repository.AnnounceMapper;
import java.util.List;

import com.teamsking.domain.repository.AnnounceUserMapper;
import com.teamsking.domain.repository.OpenUserMapper;
import com.teamsking.domain.repository.UserTeacherMapper;
import com.teamsking.domain.service.sys.UserTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
*@author linhao
*/
@Service
@Slf4j
public class AnnounceService {

    @Autowired
    AnnounceMapper announceMapper;
    @Autowired
    UserTeacherMapper userTeacherMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    AnnounceUserMapper announceUserMapper;

    @Autowired
    UserTeacherService userTeacherService;

    /**
     * 获取公告列表
     * @param openId
     * @return 公告列表
     */
    public List<AnnounceDto> list(int openId){

        //获取该班课下的公告
        Example announceExample = new Example(Announce.class);
        announceExample.and().andEqualTo("openId",openId);
        announceExample.and().andEqualTo("deleteStatus",2);
        announceExample.and().andEqualTo("announceType",3);  //1 系统公告 2学校公告 3课程公告
        announceExample.orderBy("displayOrder");
        List<Announce> announceList = announceMapper.selectByExample(announceExample);

        //遍历集合
        List<Integer> userTeacherIds = Lists.newArrayList();
        List<Integer> announceIds = Lists.newArrayList();
        for (Announce announce : announceList){
            userTeacherIds.add(announce.getCreateId());
            announceIds.add(announce.getId());
        }

        //获取发布人信息
        List<UserTeacher> userTeacherList = userTeacherService.getUserTeacherListByIds(userTeacherIds);

        //获取该班课下学生数量
        OpenUser openUser = new OpenUser();
        openUser.setDeleteStatus(2);
        openUser.setOpenId(openId);
        Integer allUserNums = openUserMapper.selectCount(openUser);

        //查询班课下已读学生数量
        /*AnnounceUser announceUser = new AnnounceUser();
        announceUser.setDeleteStatus(2);
        announceUser.setAnnounceId();*/
        /*Example AnnounceUserExample = new Example(AnnounceUser.class);
        announceExample.and().andEqualTo("deleteSatus",2);
        announceExample.and().andIn("announceId",announceIds);
        List<AnnounceUser> announceUserList = announceUserMapper.selectByExample(announceExample);*/

        //数据转换
        List<AnnounceDto> announceDtoList = AnnounceDtoMapper.INSTANCE.entityListToDtoList(announceList);

        for (AnnounceDto announceDto : announceDtoList){

            for (UserTeacher userTeacher : userTeacherList){
                if (announceDto.getCreateId().intValue() == userTeacher.getId().intValue()){
                    announceDto.setUserName(userTeacher.getUserName());
                }
            }

            //某一班课公告的已读人数
            AnnounceUser announceUser = new AnnounceUser();
            announceUser.setDeleteStatus(2);
            announceUser.setAnnounceId(announceDto.getId());
            Integer readUserNums = announceUserMapper.selectCount(announceUser);
            announceDto.setReadUserNums(readUserNums);

            announceDto.setAllUserNums(allUserNums);
        }

        return announceDtoList;

    }

    /**
     * 添加公告
     * @param announce
     * @return
     */
    public int save(Announce announce){

        return announceMapper.insert(announce);
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    public int remove(int id){

        return announceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改公告
     * @param announce
     * @return
     */
    public int modify(Announce announce){

        return announceMapper.updateByPrimaryKeySelective(announce);
    }

}
