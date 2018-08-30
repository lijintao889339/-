package com.teamsking.domain.service.node;

import com.teamsking.api.dto.node.NodeDocDto;
import com.teamsking.api.dto.node.NodeDtoMapper;
import com.teamsking.api.dto.node.NodeNameDto;
import com.teamsking.api.dto.node.NodeVideoDto;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.repository.NodeMapper;
import java.util.List;

import com.teamsking.domain.repository.OpenItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class NodeService {

    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    OpenItemMapper openItemMapper;

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
    public List<Node> query(Integer openId){

        Node node = new Node();
        node.setId(openId);
        node.setDeleteStatus(2);

        return nodeMapper.select(node);

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


    /**
     * 根据班课id添加视频
     * @param nodeVideoDto
     * @return
     */
    public int saveVideoByOpenId(NodeVideoDto nodeVideoDto,Integer openId){

        Node nodeEntity = NodeDtoMapper.INSTANCE.dotToEntityVideo(nodeVideoDto);
        nodeEntity.setDeleteStatus(2);
        nodeEntity.setOpenId(openId);
        nodeEntity.setNodeType(20);

        nodeMapper.insertSelective(nodeEntity);

        OpenItem openItem = new OpenItem();
        openItem.setOpenId(nodeEntity.getOpenId());
        openItem.setContentId(nodeEntity.getId());
        openItem.setChapterId(nodeEntity.getChapterId());
        openItem.setSectionId(nodeEntity.getSectionId());
        openItem.setDeleteStatus(2);
        openItem.setItemType(10);

        return openItemMapper.insertSelective(openItem);


    }

    /**
     * 根据节id添加视频
     * @param nodeVideoDto
     * @param sectionId
     * @return
     */
    public int saveVideoBySectionId(NodeVideoDto nodeVideoDto,Integer sectionId){

        Node nodeEntity = NodeDtoMapper.INSTANCE.dotToEntityVideo(nodeVideoDto);
        nodeEntity.setSectionId(sectionId);
        nodeEntity.setNodeType(20);

        nodeMapper.insertSelective(nodeEntity);

        OpenItem openItem = new OpenItem();
        openItem.setOpenId(nodeEntity.getOpenId());
        openItem.setContentId(nodeEntity.getId());
        openItem.setChapterId(nodeEntity.getChapterId());
        openItem.setSectionId(nodeEntity.getSectionId());
        openItem.setDeleteStatus(2);
        openItem.setItemType(10);

        return openItemMapper.insertSelective(openItem);

    }


    /**
     * 根据班课id查询视频信息(教学管理内容)
     * @param openId
     * @return
     */
//    public List<NodeVideoDto> getNodeVideoListByOpenId(Integer openId){
//
//        Node node = new Node();
//        node.setNodeType(20);
//        node.setOpenId(openId);
//        node.setDeleteStatus(2);
//
//        List<Node> nodeList = nodeMapper.select(node);
//
//        List<NodeVideoDto> nodeVideoDtoList = NodeDtoMapper.INSTANCE.entityListVideoDto(nodeList);
//
//        return nodeVideoDtoList;
//
//    }


    /**
     * 根据班课id添加课件(章节内容)
     * @param nodeDocDto
     * @param openId
     * @return
     */
    public int saveNodeDocByOpenId(NodeDocDto nodeDocDto,Integer openId){

        //数据转换
        Node nodeEntity = NodeDtoMapper.INSTANCE.dotToEntityDoc(nodeDocDto);
        nodeEntity.setDeleteStatus(2);
        nodeEntity.setOpenId(openId);
        nodeEntity.setNodeType(10);
        nodeMapper.insertSelective(nodeEntity);

        OpenItem openItem = new OpenItem();
        openItem.setOpenId(nodeEntity.getOpenId());
        openItem.setContentId(nodeEntity.getId());
        openItem.setChapterId(nodeEntity.getChapterId());
        openItem.setSectionId(nodeEntity.getSectionId());
        openItem.setDeleteStatus(2);
        openItem.setItemType(20);

        return openItemMapper.insertSelective(openItem);
    }

    /**
     * 根据节id添加课件(章节内容)
     * @param nodeDocDto
     * @param sectionId
     * @return
     */
    public int saveNodeDocBySectionId(NodeDocDto nodeDocDto,Integer sectionId){

        //数据转换
        Node nodeEntity = NodeDtoMapper.INSTANCE.dotToEntityDoc(nodeDocDto);
        nodeEntity.setDeleteStatus(2);
        nodeEntity.setNodeType(10);
        nodeEntity.setSectionId(sectionId);
        nodeMapper.insertSelective(nodeEntity);

        OpenItem openItem = new OpenItem();
        openItem.setOpenId(nodeEntity.getOpenId());
        openItem.setContentId(nodeEntity.getId());
        openItem.setChapterId(nodeEntity.getChapterId());
        openItem.setSectionId(nodeEntity.getSectionId());
        openItem.setDeleteStatus(2);
        openItem.setItemType(20);

        return openItemMapper.insertSelective(openItem);

    }


    public NodeNameDto getNodeNameById(Integer id,Integer openId){

        //Node nodeEntity = NodeDtoMapper.INSTANCE.dotToEntityName(nodeNameDto);

        Node node = new Node();
        node.setId(id);
        node.setOpenId(openId);
        node.setDeleteStatus(2);
        node.setNodeType(20);

        Node nodeEntity = nodeMapper.selectOne(node);

        NodeNameDto nodeNameDtoEntity = NodeDtoMapper.INSTANCE.EntityNameDot(nodeEntity);



        //return nodeMapper.selectOne(nodeEntity);
        return nodeNameDtoEntity;

    }


}
