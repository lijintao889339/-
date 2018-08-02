package com.teamsking.domain.service.node;

import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.repository.NodeMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class NodeService {

    @Autowired
    NodeMapper nodeMapper;

    /**
     * 获取资源管理列表
     *
     * @return
     */
    public List<Node> list(){
        return nodeMapper.selectAll();
    }

    /**
     * 添加资源管理
     * @param node
     * @return
     */
    public int save(Node node){
        return nodeMapper.insert(node);
    }


    /**
     * 根据班课id查询视频信息
     * @param openId
     * @return
     */
    public Node query(Integer openId){

//        Example nodeExample = new Example(Node.class);
//        Example.Criteria cri = nodeExample.createCriteria();
//        cri.andIn("openId",);
//        return nodeMapper.selectByExample(nodeExample);
        return nodeMapper.selectByPrimaryKey(openId);

    }

    /**
     * 删除资源管理
     * @param id
     * @return
     */
    public int remove(Integer id){
        return nodeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改资源管理
     * @param node
     * @return
     */
    public int modify(Node node){
        return nodeMapper.updateByPrimaryKeySelective(node);
    }

    /**
     * 根据班次Ids获取资源信息
     * @param openIds
     * @return
     */
    public List<Node> getNodeByOpenIdList(List<Integer> openIds) {

        Example nodeExample = new Example(Node.class);
        return nodeMapper.selectByExample(nodeExample);

    }
}
