package com.teamsking.domain.service.sys;

import com.teamsking.domain.entity.sys.SysConfig;
import com.teamsking.domain.repository.SysConfigMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class SysConfigService {

    @Autowired
    SysConfigMapper sysConfigMapper;

    /**
     * 获取系统配置列表
     *
     * @return 配置列表
     */
    public List<SysConfig> list() {

        return sysConfigMapper.selectAll();
    }

    /**
     * 添加系统配置
     * @param sysConfig
     * @return
     */
    public int save(SysConfig sysConfig){

        return sysConfigMapper.insert(sysConfig);
    }

    /**
     *删除系统配置
     * @param id
     * @return
     */
    public int remove(Integer id){
        return sysConfigMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改系统配置
     * @param sysConfig
     * @return
     */
    public int modify(SysConfig sysConfig){

        return sysConfigMapper.updateByPrimaryKeySelective(sysConfig);
    }

}
