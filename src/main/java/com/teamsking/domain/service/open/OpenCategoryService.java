package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenCategory;
import com.teamsking.domain.repository.OpenCategoryMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenCategoryService {

    @Autowired
    OpenCategoryMapper openCategoryMapper;

    /**
     * 获取班次-科学管理列表
     * @return
     */
    public List<OpenCategory> list(){

        return openCategoryMapper.selectAll();

    }

    /**
     * 添加班次-科学管理
     * @param openCategory
     * @return
     */
    public int save(OpenCategory openCategory){

        return openCategoryMapper.insert(openCategory);

    }

    /**
     * 删除班次-科学管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openCategoryMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-科学管理
     * @param openCategory
     * @return
     */
    public int modify(OpenCategory openCategory){

        return openCategoryMapper.updateByPrimaryKeySelective(openCategory);

    }

}
