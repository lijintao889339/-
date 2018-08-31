package com.teamsking.domain.service.open;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenTopicDto;
import com.teamsking.api.dto.open.OpenTopicDtoMapper;
import com.teamsking.api.dto.open.OpenTopicNameDto;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.entity.open.OpenTopic;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.sys.UserStudentTopic;
import com.teamsking.domain.repository.OpenItemMapper;
import com.teamsking.domain.repository.OpenTopicMapper;
import com.teamsking.domain.repository.OpenUserMapper;
import com.teamsking.domain.repository.UserStudentTopicMapper;
import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OpenTopicService extends BaseService {

    @Autowired
    OpenTopicMapper openTopicMapper;
    @Autowired
    OpenItemMapper openItemMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    UserStudentTopicMapper userStudentTopicMapper;

    /**
     * 根据班课id添加班课讨论
     * @param openTopicDto
     * @return
     */
    public int saveOpenTopic(OpenTopicDto openTopicDto){

        //数据转换
        OpenTopic openTopicEntity = OpenTopicDtoMapper.INSTANCE.dtoToEntity(openTopicDto);
        //向班课讨论表添加信息
        openTopicEntity.setDeleteStatus(2);
        openTopicMapper.insertSelective(openTopicEntity);

        OpenItem openItem = new OpenItem();
        openItem.setContentId(openTopicEntity.getId());
        openItem.setChapterId(openTopicEntity.getChapterId());
        openItem.setSectionId(openTopicEntity.getSectionId());
        openItem.setOpenId(openTopicEntity.getOpenId());
        openItem.setDeleteStatus(2);

        return openItemMapper.insertSelective(openItem);

    }


    /**
     * 根据节id添加班课讨论
     * @param openTopicDto
     * @param sectionId
     * @return
     */
    public int saveOpenTopicBySectionId(OpenTopicDto openTopicDto,Integer sectionId){

        //数据转换
        OpenTopic openTopicEntity = OpenTopicDtoMapper.INSTANCE.dtoToEntity(openTopicDto);
        //向班课讨论表添加信息
        openTopicEntity.setDeleteStatus(2);
        openTopicMapper.insertSelective(openTopicEntity);

        OpenItem openItem = new OpenItem();
        openItem.setContentId(openTopicEntity.getId());
        openItem.setSectionId(sectionId);
        openItem.setChapterId(openTopicEntity.getChapterId());
        openItem.setDeleteStatus(2);
        openItem.setOpenId(openTopicEntity.getOpenId());

        return openItemMapper.insertSelective(openItem);

    }


    /**
     * 根据openId获取讨论内容信息(教学管理讨论功能)
     * @param openId
     * @return
     */
    public Page getOpenOpenTopicListByOpenId(Integer openId,int pageNo,int pageSize){

        OpenTopic newOpenTopic = new OpenTopic();
        newOpenTopic.setDeleteStatus(2);
        newOpenTopic.setOpenId(openId);

        //分页操作
        PageHelper.startPage(pageNo, pageSize);

        List<OpenTopic> openTopicList = openTopicMapper.select(newOpenTopic);

        if(0 != openTopicList.size()){

            //获取该班课下学生数量
            OpenUser openUser = new OpenUser();
            openUser.setDeleteStatus(2);
            openUser.setOpenId(openId);
            int allUserNum = openUserMapper.selectCount(openUser);

            //数据转换
            List<OpenTopicNameDto> openTopicNameDtoList = OpenTopicDtoMapper.INSTANCE.entityListNameToDto(openTopicList);

            //遍历
            for (OpenTopicNameDto openTopicNameDto:openTopicNameDtoList) {

                //班课讨论的已提交人数
                UserStudentTopic userStudentTopic = new UserStudentTopic();
                userStudentTopic.setTopicId(openTopicNameDto.getId());
                userStudentTopic.setDeleteStatus(2);
                int stopUserCount = userStudentTopicMapper.selectCount(userStudentTopic);
                openTopicNameDto.setStopUserCount(stopUserCount);

                //总人数
                openTopicNameDto.setUserCount(allUserNum);
                //未提交人数
                int notUserCount = allUserNum - stopUserCount;
                openTopicNameDto.setNotUserCount(notUserCount);

            }

            return convertPage((Page)openTopicList,openTopicNameDtoList);

        }else {
            Page page =null;
            return page;
        }



    }



    /**
     * 根据id查询班课讨论详情
     * @param id
     * @return
     */
    public OpenTopic getOpenTopicById(Integer id){

        OpenTopic openTopic = new OpenTopic();

        openTopic.setId(id);
        openTopic.setDeleteStatus(2);

        return openTopicMapper.selectOne(openTopic);
    }

}
