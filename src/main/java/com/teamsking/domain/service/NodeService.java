package com.teamsking.domain.service;

import com.teamsking.domain.entity.Node;
import com.teamsking.domain.repository.NodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NodeService {

    @Autowired
    NodeMapper nodeMapper;

    /**
     * 获取课程资源列表
     * @param node
     * @return
     */
    public List<Node> list(Node node){
        return nodeMapper.select(node);
    }

    /**
     * 添加课程资源
     * @param node
     * @return
     */
    public int save(Node node){
        return nodeMapper.insert(node);
    }

    /**
     * 删除课程资源
     * @param node
     * @return
     */
    public int remove(Node node){
        return nodeMapper.delete(node);
    }

    /**
     * 修改课程资源
     * @param node
     * @return
     */
    public int modify(Node node){
        return nodeMapper.updateByPrimaryKeySelective(node);
    }

}
