package com.teamsking.domain.service.node;

import com.teamsking.domain.entity.node.NodeFolder;
import com.teamsking.domain.repository.NodeFolderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NodeFolderService {

    @Autowired
    NodeFolderMapper nodeFolderMapper;

    /**
     * 获取资源目录管理列表
     *
     * @return
     */
    public List<NodeFolder> list(){
        return nodeFolderMapper.selectAll();
    }

    /**
     * 添加资源目录管理
     * @param nodeFolder
     * @return
     */
    public int save(NodeFolder nodeFolder){
        return nodeFolderMapper.insert(nodeFolder);
    }

    /**
     * 删除资源目录管理
     * @param id
     * @return
     */
    public int remove(Integer id){
        return nodeFolderMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改资源目录管理
     * @param nodeFolder
     * @return
     */
    public int modify(NodeFolder nodeFolder){
        return nodeFolderMapper.updateByPrimaryKeySelective(nodeFolder);

    }

}
