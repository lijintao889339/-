package com.teamsking.domain.service.category;

import com.teamsking.domain.entity.category.Category;
import com.teamsking.domain.repository.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 获取类别管理列表
     * @return
     */
    public List<Category> list(){

        return categoryMapper.selectAll();

    }


    /**
     * 添加类别管理
     * @param category
     * @return
     */
    public int save(Category category){

        return categoryMapper.insert(category);

    }


    /**
     * 删除类别管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return categoryMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改类别管理
     * @param category
     * @return
     */
    public int modify(Category category){

        return categoryMapper.updateByPrimaryKeySelective(category);

    }

}