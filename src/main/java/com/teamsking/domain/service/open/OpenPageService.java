package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenPage;
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


    /**
     * 获取班次-页面管理列表
     * @return
     */
    public List<OpenPage> list(){

        return openPageMapper.selectAll();

    }

    /**
     * 添加班次-页面管理
     * @param openPage
     * @return
     */
    public int save(OpenPage openPage){

        return openPageMapper.insert(openPage);

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
