package com.teamsking.domain.service.open;

import com.teamsking.api.dto.open.OpenTopicDto;
import com.teamsking.api.dto.open.OpenTopicDtoMapper;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.entity.open.OpenTopic;
import com.teamsking.domain.repository.OpenItemMapper;
import com.teamsking.domain.repository.OpenTopicMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenTopicService {

    @Autowired
    OpenTopicMapper openTopicMapper;
    @Autowired
    OpenItemMapper openItemMapper;

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

        return openItemMapper.insertSelective(openItem);

    }

}
