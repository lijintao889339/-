package com.teamsking.domain.service.open;

import com.teamsking.api.dto.open.OpenSectionDto;
import com.teamsking.api.dto.open.OpenSectionDtoMapper;
import com.teamsking.domain.entity.open.OpenSection;
import com.teamsking.domain.repository.OpenSectionMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenSectionService {

    @Autowired
    OpenSectionMapper openSectionMapper;

    /**
     * 获取班次-节管理列表
     * @return
     */
    public List<OpenSection> list(){

        return openSectionMapper.selectAll();
    }

    /**
     * 添加班次-节管理
     * @param openSection
     * @return
     */
    public int save(OpenSection openSection){

        return openSectionMapper.insert(openSection);
    }

    /**
     * 删除班次-节管理
     * @param id
     * @return
     */
    public int remove(int id){

        return openSectionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改班次-节管理
     * @param openSection
     * @return
     */
    public int modify(OpenSection openSection){

        return openSectionMapper.updateByPrimaryKeySelective(openSection);
    }


    /**
     * 根据章ids查询下面的节信息
     * @param chapterIds
     * @return
     */
    public List<OpenSection> getSectionListByChapterIds(List<Integer> chapterIds){

        Example sectionExample = new Example(OpenSection.class);
        sectionExample.and().andEqualTo("deleteStatus",2);
        sectionExample.and().andIn("chapterId",chapterIds);
        //Example.Criteria cri = sectionExample.createCriteria();
        ///cri.andIn("chapterId",chapterIds);
        //cri.andEqualTo("deleteStatus",2);
        return openSectionMapper.selectByExample(sectionExample);
    }


    /**
     * 根据班课章id添加节
     * @param openSectionDto
     * @return
     */
    public int saveOpenSection(OpenSectionDto openSectionDto){

        //首先查询此章有多少节
        OpenSection openSection = new OpenSection();
        openSection.setChapterId(openSectionDto.getChapterId());
        int count = openSectionMapper.selectCount(openSection);

        //数据转换
        OpenSection openSectionEntity = OpenSectionDtoMapper.INSTANCE.dtoToEntity(openSectionDto);
        openSectionEntity.setDeleteStatus(2);
        openSectionEntity.setDiaplayOrder(count + 1);

        return openSectionMapper.insertSelective(openSectionEntity);


    }


}
