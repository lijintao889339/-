package com.teamsking.domain.service.category;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.category.AddCategoryNameDto;
import com.teamsking.api.dto.category.CategoryDtoMapper;
import com.teamsking.api.dto.category.CategoryListViewDto;
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

    /**
     * 根据categoryIds查询课程分类
     * @param categoryIds
     * @return
     */
    public List<Category> getCategoryByCategoryId(List<Integer> categoryIds){

        Example categoryExample = new Example(Category.class);
        return categoryMapper.selectByExample(categoryExample);

    }

    /**
     * 查询分类列表
     */
    public List<CategoryListViewDto> getAllCategory(){

        //查询所有的一级分类列表
        List<Integer> parentIds = Lists.newArrayList();
        Category categoryEntity = new Category();
        categoryEntity.setParentId(0);
        categoryEntity.setDeleteStatus(2);
        List<Category> categoryList = categoryMapper.select(categoryEntity);

        //遍历集合，获取二级分类的父级id
        for (Category category : categoryList) {
            parentIds.add(category.getId());

        }

        //根据父级Id获取二级分类
        Example categoryExample = new Example(Category.class);
        Example.Criteria cri = categoryExample.createCriteria();
        cri.andIn("parentId",parentIds);
        cri.andEqualTo("deleteStatus",2);
        List<Category> categorySecondList = categoryMapper.selectByExample(categoryExample);

        //数据转换
        List<CategoryListViewDto> categoryListViewDtoList = CategoryDtoMapper.INSTANCE.entityToListViewDtoList(categoryList);
        //遍历获取分类名称
        for (CategoryListViewDto category: categoryListViewDtoList) {

            List<AddCategoryNameDto> categories = Lists.newArrayList();
            for (Category categorySecond:categorySecondList) {
                AddCategoryNameDto addCategoryNameDto = new AddCategoryNameDto();
                if (categorySecond.getParentId().intValue() == category.getId().intValue()){
                    addCategoryNameDto.setId(categorySecond.getId());
                    addCategoryNameDto.setCategoryName(categorySecond.getCategoryName());
                    addCategoryNameDto.setParentId(categorySecond.getParentId());
                    categories.add(addCategoryNameDto);
                    category.setCategories(categories);
                }
            }
        }
        return categoryListViewDtoList;
   }

}
