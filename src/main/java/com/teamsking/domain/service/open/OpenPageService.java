package com.teamsking.domain.service.open;


import com.teamsking.api.dto.open.AddOpenPageDto;
import com.teamsking.api.dto.open.OpenPageDtoMapper;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.entity.open.OpenPage;
import com.teamsking.domain.repository.OpenItemMapper;
import com.teamsking.domain.repository.OpenPageMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenPageService {


    @Autowired
    OpenPageMapper openPageMapper;
    @Autowired
    OpenItemMapper openItemMapper;


    /**
     * 获取班次-页面管理列表
     * @return
     */
    public List<OpenPage> list(){

        return openPageMapper.selectAll();

    }

    /**
     * 根据班课id添加班课页面
     * @param addOpenPageDto
     * @return
     */
    public int saveOpenPage(AddOpenPageDto addOpenPageDto){

        //数据转换
        OpenPage openPageEntity = OpenPageDtoMapper.INSTANCE.dtoToPageEntity(addOpenPageDto);
        openPageEntity.setDeleteStatus(2);
        //添加数据
        openPageMapper.insertSelective(openPageEntity);

        OpenItem openItem = new OpenItem();
        openItem.setContentId(openPageEntity.getId());
        openItem.setChapterId(openPageEntity.getChapterId());
        openItem.setSectionId(openPageEntity.getSectionId());
        openItem.setOpenId(openPageEntity.getOpenId());


        return openItemMapper.insertSelective(openItem);

    }


    /**
     * 根据节id添加页面信息
     * @param addOpenPageDto
     * @param sectionId
     * @return
     */
    public int saveOpenPageBySectionId(AddOpenPageDto addOpenPageDto,Integer sectionId){

        //数据转换
        OpenPage openPageEntity = OpenPageDtoMapper.INSTANCE.dtoToPageEntity(addOpenPageDto);
        openPageEntity.setDeleteStatus(2);
        //添加数据
        openPageMapper.insertSelective(openPageEntity);

        OpenItem openItem = new OpenItem();
        openItem.setContentId(openPageEntity.getId());
        openItem.setChapterId(openPageEntity.getChapterId());
        openItem.setSectionId(sectionId);
        openItem.setOpenId(openPageEntity.getOpenId());
        openItem.setDeleteStatus(2);

        return openItemMapper.insertSelective(openItem);

    }


    /**
     * 删除班次-页面管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openPageMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-页面管理
     * @param openPage
     * @return
     */
    public int modify(OpenPage openPage){

        return openPageMapper.updateByPrimaryKeySelective(openPage);

    }




}
