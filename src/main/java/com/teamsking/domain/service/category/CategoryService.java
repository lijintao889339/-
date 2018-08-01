package com.teamsking.domain.service.category;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.category.AddCategoryNameDto;
import com.teamsking.api.dto.category.CategoryDtoMapper;
import com.teamsking.api.dto.category.CategoryListViewDto;
import com.teamsking.api.dto.open.OpenDtoMapper;
import com.teamsking.api.dto.open.OpenIdAndNameDto;
import com.teamsking.domain.entity.category.Category;

import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.repository.CategoryMapper;
import java.util.List;

import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.open.OpenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Slf4j
@Service
public class CategoryService extends BaseService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    OpenService openService;

    /**
     * 创建班课一级分类
     * @param category
     * @return
     */
    public int saveFirstLabel(Category category){
        category.setDeleteStatus(2);
        category.setIsFirstLabel(true);
        return categoryMapper.insert(category);

    }

    /**
     * 创建班课二级分类
     * @param category
     * @return
     */
    public int saveSecondLabel(Category category){
        category.setDeleteStatus(2);
        category.setIsFirstLabel(false);
        category.setIsShow(true);
        return categoryMapper.insert(category);

    }


    /**
     * 删除班课分类
     * @param id
     * @return
     */
    public int removeCategoryById(Integer id){


        Category category = new Category();
        category.setDeleteStatus(1);

        Example categoryExample = new Example(Category.class);
        categoryExample.or().andEqualTo("parentId",id);
        categoryExample.or().andEqualTo("id",id);
        return categoryMapper.updateByExampleSelective(category,categoryExample);

    }

    /**
     * 修改类别管理
     * @param category
     * @return
     */
    public int modify(Category category){

        return categoryMapper.updateByPrimaryKeySelective(category);

    }

    /**
     * 根据categoryIds查询班课分类
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

            List<AddCategoryNameDto> children = Lists.newArrayList();
            for (Category categorySecond:categorySecondList) {
                AddCategoryNameDto addCategoryNameDto = new AddCategoryNameDto();
                if (categorySecond.getParentId().intValue() == category.getId().intValue()){
                    addCategoryNameDto.setId(categorySecond.getId());
                    addCategoryNameDto.setLabel(categorySecond.getLabel());
                    addCategoryNameDto.setIsShow(categorySecond.getIsShow());
                    addCategoryNameDto.setIsFirstLabel(categorySecond.getIsFirstLabel());
                    children.add(addCategoryNameDto);
                    category.setChildren(children);
                }
            }
        }
        return categoryListViewDtoList;
   }

    /**
     * 分页获取班课分类下面的班课列表
     * @param pageNo
     * @param pageSize
     * @param id
     * @return
     */
    public Page getCategoryOpensById(int pageNo, int pageSize,int id) {

        List<Integer> categoryIds = Lists.newArrayList();

        //根据分类Id获取分类信息
        Category category = categoryMapper.selectByPrimaryKey(id);


        List<Open> openList;

        //判断是否为一级分类
        if (true == category.getIsFirstLabel()){
            Category secondCategory = new Category();
            secondCategory.setParentId(category.getId());

            //查询一级分类下的二级分类列表
            List<Category> categoryList = categoryMapper.select(secondCategory);
            for (Category categoryEntity : categoryList) {
                categoryIds.add(categoryEntity.getId());
            }

            PageHelper.startPage(pageNo, pageSize);

            //根据分类Ids获取其下面的班次列表
            openList = openService.getOPenListByCategoryIdList(categoryIds);

        }else {
            PageHelper.startPage(pageNo, pageSize);

            //根据分类Id获取其下面的班次列表
            openList = openService.getOpenListByCategoryId(id);

        }

        List<OpenIdAndNameDto>  openIdAndNameDtoList = OpenDtoMapper.INSTANCE.entityToListOpenIdAndName(openList);
        return convertPage((Page)openList,openIdAndNameDtoList);

    }

    /**
     * 获取所有一级分类
     * @return
     */
    public List<Category> getAllFirstCategory() {

        Category category = new Category();
        category.setParentId(0);
        category.setDeleteStatus(2);
        return categoryMapper.select(category);

    }

    /**
     * 获取一级分类下的所有二级分类
     * @param id
     * @return
     */
    public List<Category> getSecondCategoryById(int id) {

        Category category = new Category();
        category.setParentId(id);
        category.setDeleteStatus(2);
        return categoryMapper.select(category);

    }

}
