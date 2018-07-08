package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenChapter;
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

    /**
     * 获取班次-章列表
     * @return
     */
    public List<OpenChapter> list(){

        return openChapterMapper.selectAll();
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
