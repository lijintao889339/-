package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.repository.OpenItemMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenItemService {

    @Autowired
    OpenItemMapper openItemMapper;


    /**
     * 获取班次-项管理列表
     * @return
     */
    public List<OpenItem> list(){

        return openItemMapper.selectAll();

    }


    /**
     *  添加班次-项管理
     * @param openItem
     * @return
     */
    public int save(OpenItem openItem){

        return openItemMapper.insert(openItem);

    }

    /**
     * 删除班次-项管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openItemMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-项管理
     * @param openItem
     * @return
     */
    public int modify(OpenItem openItem){

        return openItemMapper.updateByPrimaryKeySelective(openItem);

    }

}
