package com.teamsking.domain.service.node;

import com.teamsking.api.dto.node.NodeFolderDtoMapper;
import com.teamsking.api.dto.node.NodeFolderSelDto;
import com.teamsking.domain.entity.node.NodeFolder;
import com.teamsking.domain.repository.NodeFolderMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * 根据openId查询课件资源视频库一级目录
     * @param openId
     * @return
     */
    public List<NodeFolderSelDto> getNodeFolderListByOpenId(Integer openId){
        //根据openid获取视频目录
        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setOpenId(openId);
        nodeFolderEntity.setParentId(0);
        nodeFolderEntity.setDeleteStatus(2);
        nodeFolderEntity.setFolderType(10);

        List<NodeFolder> nodeFolderList = nodeFolderMapper.select(nodeFolderEntity);
        //数据转换
        List<NodeFolderSelDto> nodeFolderSelDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoSelList(nodeFolderList);


        return nodeFolderSelDtoList;
    }

    /**
     * 根据一级目录查询课件资源视频库子目录
     * @param id
     * @param openId
     * @return
     */
    public List<NodeFolderSelDto> getTwoVideoListById(Integer id,Integer openId){

        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setParentId(id);
        nodeFolderEntity.setFolderType(10);
        nodeFolderEntity.setDeleteStatus(2);
        nodeFolderEntity.setOpenId(openId);

        List<NodeFolder> nodeFolderList = nodeFolderMapper.select(nodeFolderEntity);

        List<NodeFolderSelDto> nodeFolderSelDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoSelList(nodeFolderList);

        return nodeFolderSelDtoList;
    }


    /**
     * 根据openId查询课件资源文档库一级目录
     * @param openId
     * @return
     */
    public List<NodeFolderSelDto> getOneDocListByOpenId(Integer openId){

        //根据openid获取视频目录
        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setOpenId(openId);
        nodeFolderEntity.setParentId(0);
        nodeFolderEntity.setDeleteStatus(2);
        nodeFolderEntity.setFolderType(20);

        List<NodeFolder> nodeFolderList = nodeFolderMapper.select(nodeFolderEntity);
        //数据转换
        List<NodeFolderSelDto> nodeFolderSelDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoSelList(nodeFolderList);

        return nodeFolderSelDtoList;

    }

    /**
     * 根据一级目录查询课件资源文档库子目录
     * @param openId
     * @param id
     * @return
     */
    public List<NodeFolderSelDto> getTwoDocListById(Integer openId,Integer id){

        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setParentId(id);
        nodeFolderEntity.setFolderType(20);
        nodeFolderEntity.setDeleteStatus(2);
        nodeFolderEntity.setOpenId(openId);

        List<NodeFolder> nodeFolderList = nodeFolderMapper.select(nodeFolderEntity);

        List<NodeFolderSelDto> nodeFolderSelDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoSelList(nodeFolderList);

        return nodeFolderSelDtoList;

    }


    /**
     * 根据openId查询课件资源习题库一级目录
     * @param openId
     * @return
     */
    public List<NodeFolderSelDto> getOneQuizListByOpenId(Integer openId){

        //根据openid获取视频目录
        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setOpenId(openId);
        nodeFolderEntity.setParentId(0);
        nodeFolderEntity.setDeleteStatus(2);
        nodeFolderEntity.setFolderType(30);

        List<NodeFolder> nodeFolderList = nodeFolderMapper.select(nodeFolderEntity);
        //数据转换
        List<NodeFolderSelDto> nodeFolderSelDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoSelList(nodeFolderList);

        return nodeFolderSelDtoList;

    }

    /**
     * 根据一级id查询课件资源习题库子目录
     * @param id
     * @param openId
     * @return
     */
    public List<NodeFolderSelDto> getTwoQuizListById(Integer id,Integer openId){

        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setParentId(id);
        nodeFolderEntity.setFolderType(30);
        nodeFolderEntity.setDeleteStatus(2);
        nodeFolderEntity.setOpenId(openId);

        List<NodeFolder> nodeFolderList = nodeFolderMapper.select(nodeFolderEntity);

        List<NodeFolderSelDto> nodeFolderSelDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoSelList(nodeFolderList);

        return nodeFolderSelDtoList;

    }

}
