package com.teamsking.domain.service.category;

import com.teamsking.domain.entity.category.Category;

import com.teamsking.domain.repository.CategoryMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
     * 创建课程分类
     * @param category
     * @return
     */
    public int save(Category category){
        category.setDeleteStatus(2);
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
     * 删除一级分类
     * @param id
     * @return
     */
    public int removeFirstCategoryById(Integer id){


        Category category = new Category();
        category.setDeleteStatus(1);

        Example categoryExample = new Example(Category.class);
        categoryExample.or().andEqualTo("parentId",id);
        categoryExample.or().andEqualTo("id",id);
        return categoryMapper.updateByExampleSelective(category,categoryExample);

    }


    /**
     * 删除二级分类
     * @param id
     * @return
     */
    public int removeCategoryById(Integer id){

        Category category = new Category();
        category.setDeleteStatus(1);

        Example categoryExample = new Example(Category.class);
        categoryExample.or().andEqualTo("id",id);
        return categoryMapper.updateByExampleSelective(category,categoryExample);

    }


    /**
     * 修改类别管理
     * @param category
     * @return
     */
    public int modify(Category category){
        category.setDeleteStatus(2);
        return categoryMapper.updateByPrimaryKeySelective(category);

    }

}
