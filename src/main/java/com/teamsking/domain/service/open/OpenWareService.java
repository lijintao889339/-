package com.teamsking.domain.service.open;

import com.teamsking.api.dto.open.OpenWareDto;
import com.teamsking.api.dto.open.OpenWareDtoMapper;
import com.teamsking.domain.entity.open.OpenWare;
import com.teamsking.domain.repository.OpenWareMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenWareService {

    @Autowired
    OpenWareMapper openWareMapper;

    /**
     * 获取班次-课件管理列表
     * @return
     */
    public List<OpenWare> list(){

        return openWareMapper.selectAll();
    }

    /**
     *添加班次-课件管理
     * @param openWare
     * @return
     */
    public int save(OpenWare openWare){

        return openWareMapper.insert(openWare);
    }

    /**
     *删除班次-课件管理
     * @param id
     * @return
     */
    public int remove(int id){

        return openWareMapper.deleteByPrimaryKey(id);
    }

    /**
     *修改班次-课件管理
     * @param openWare
     * @return
     */
    public int modify(OpenWare openWare){

        return openWareMapper.updateByPrimaryKeySelective(openWare);
    }
}
