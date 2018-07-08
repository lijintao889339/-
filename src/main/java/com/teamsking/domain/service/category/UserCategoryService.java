package com.teamsking.domain.service.category;


import com.teamsking.domain.entity.category.UserCategory;
import com.teamsking.domain.repository.UserCategoryMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCategoryService {


    @Autowired
    UserCategoryMapper userCategoryMapper;


    /**
     *  获取类别-用户关系管理列表
     * @return
     */
    public List<UserCategory> list(){

        return userCategoryMapper.selectAll();

    }


    /**
     * 添加类别-用户关系管理
     * @param userCategory
     * @return
     */
    public int save(UserCategory userCategory){

        return userCategoryMapper.insert(userCategory);

    }


    /**
     * 删除类别-用户关系管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return userCategoryMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改类别-用户关系管理
     * @param userCategory
     * @return
     */
    public int modify(UserCategory userCategory){

        return userCategoryMapper.updateByPrimaryKeySelective(userCategory);

    }

}
