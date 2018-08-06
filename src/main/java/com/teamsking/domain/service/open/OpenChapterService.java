package com.teamsking.domain.service.open;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.ChapterSectionDto;
import com.teamsking.api.dto.open.OpenChapterDto;
import com.teamsking.api.dto.open.OpenChapterDtoMapper;
import com.teamsking.api.dto.open.SectionTitleAndOrderDto;
import com.teamsking.domain.entity.open.OpenChapter;
import com.teamsking.domain.entity.open.OpenSection;
import com.teamsking.domain.repository.OpenChapterMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenChapterService {

    @Autowired
    OpenChapterMapper openChapterMapper;

    @Autowired
    OpenSectionService openSectionService;

    /**
     * 获取班次-章列表
     * @return
     */
    public List<OpenChapter> list(){

        return openChapterMapper.selectAll();
    }

    /**
     * 根据openId获取班课中的章
     * @param openId
     * @return
     */
    public List<ChapterSectionDto> chapterList(Integer openId){

        List<Integer> chapterIds = Lists.newArrayList();

        //根据班课Id获取该班课下面的章
        OpenChapter openChapterEntity = new OpenChapter();
        openChapterEntity.setOpenId(openId);
        openChapterEntity.setDeleteStatus(2);
        List<OpenChapter> openChapterList = openChapterMapper.select(openChapterEntity);

        //根据章ids获取下面的节
        for (OpenChapter openChapter:openChapterList) {
            chapterIds.add(openChapter.getId());

        }
        List<OpenSection> openSectionList = openSectionService.getSectionListByChapterIds(chapterIds);

        //数据转换
        List<ChapterSectionDto> chapterSectionDtoList = OpenChapterDtoMapper.INSTANCE.entityListToChapterSectionDtoList(openChapterList);

        //遍历
        for (ChapterSectionDto chapterSectionDto:chapterSectionDtoList) {

            chapterSectionDto.setIsFirstLabel(true);

            List<SectionTitleAndOrderDto> sectionList = Lists.newArrayList();

            for (OpenSection openSection:openSectionList) {

                SectionTitleAndOrderDto sectionTitleAndOrderDtoEntity = new SectionTitleAndOrderDto();
                if (chapterSectionDto.getId().intValue() == openSection.getChapterId().intValue()){

                    sectionTitleAndOrderDtoEntity.setId(openSection.getId());
                    sectionTitleAndOrderDtoEntity.setDiaplayOrder(openSection.getDiaplayOrder());
                    sectionTitleAndOrderDtoEntity.setLabel(openSection.getTitle());
                    sectionTitleAndOrderDtoEntity.setIsFirstLabel(false);
                    sectionList.add(sectionTitleAndOrderDtoEntity);
                    chapterSectionDto.setChildren(sectionList);
                }

            }

        }

        return chapterSectionDtoList;


    }



    /**
     * 添加班次-章
     * @param openChapter
     * @return
     */
    public int save(OpenChapter openChapter){

        return openChapterMapper.insert(openChapter);
    }

    /**
     * 根据班课id添加章
     * @param openChapterDto
     * @return
     */
    public int saveChapter(OpenChapterDto openChapterDto){
        //查出该班课一共有多少节
        OpenChapter openChapter = new OpenChapter();
        openChapter.setOpenId(openChapterDto.getOpenId());
        int count = openChapterMapper.selectCount(openChapter);

        //数据转换
        OpenChapter openChapterEntity = OpenChapterDtoMapper.INSTANCE.dtoToEntity(openChapterDto);
        openChapterEntity.setDeleteStatus(2);
        openChapterEntity.setDisplayOrder(count + 1);
        openChapterEntity.setDeleteStatus(1);

        return openChapterMapper.insertSelective(openChapterEntity);

    }



    /**
     * 删除班次-章
     * @param id
     * @return
     */
    public int remove(int id){

        return openChapterMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改班次-章
     * @param openChapter
     * @return
     */
    public int modify(OpenChapter openChapter){

        return openChapterMapper.updateByPrimaryKeySelective(openChapter);
    }

}
