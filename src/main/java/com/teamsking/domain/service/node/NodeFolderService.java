package com.teamsking.domain.service.node;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.node.NodeFolderDtoMapper;
import com.teamsking.api.dto.node.NodeFolderSelDto;
import com.teamsking.domain.entity.node.NodeFolder;
import com.teamsking.domain.repository.NodeFolderMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
     * @param
     * @return
     */
    public List<NodeFolderSelDto> getTwoVideoListById(Integer id){

        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setParentId(id);
        nodeFolderEntity.setFolderType(10);
        nodeFolderEntity.setDeleteStatus(2);
        //nodeFolderEntity.setOpenId(openId);

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
     * @param
     * @param id
     * @return
     */
    public List<NodeFolderSelDto> getTwoDocListById(Integer id){

        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setParentId(id);
        nodeFolderEntity.setFolderType(20);
        nodeFolderEntity.setDeleteStatus(2);
        //nodeFolderEntity.setOpenId(openId);

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
     * @param
     * @return
     */
    public List<NodeFolderSelDto> getTwoQuizListById(Integer id){

        NodeFolder nodeFolderEntity = new NodeFolder();
        nodeFolderEntity.setParentId(id);
        nodeFolderEntity.setFolderType(30);
        nodeFolderEntity.setDeleteStatus(2);
        //nodeFolderEntity.setOpenId(openId);

        List<NodeFolder> nodeFolderList = nodeFolderMapper.select(nodeFolderEntity);

        List<NodeFolderSelDto> nodeFolderSelDtoList = NodeFolderDtoMapper.INSTANCE.entityListToDtoSelList(nodeFolderList);

        return nodeFolderSelDtoList;

    }


    /**
     * 根据id批量删除课件资源目录
     * @param ids
     * @return
     */
    public int removeByIds(Integer[] ids) {

        List<Integer> idList = Lists.newArrayList();
        for (Integer id : ids){
            idList.add(id);
        }

        NodeFolder nodeFolder = new NodeFolder();
        nodeFolder.setDeleteStatus(1);

        Example nodeFolderExample = new Example(NodeFolder.class);
        Example.Criteria cri = nodeFolderExample.createCriteria();
        cri.orIn("id",idList);
        cri.orIn("parentId",idList);
        return nodeFolderMapper.updateByExampleSelective(nodeFolder,nodeFolderExample);

    }


    /**
     * 创建一级视频目录
     * @param nodeFolder
     * @return
     */
    public int saveFirstVideo(NodeFolder nodeFolder) {

        nodeFolder.setDeleteStatus(2);
        nodeFolder.setFolderType(10);
        nodeFolder.setParentId(0);

        return nodeFolderMapper.insertSelective(nodeFolder);
    }

    /**
     * 创建子视频库目录
     * @param nodeFolder
     * @param id
     * @return
     */
    public int saveTwoVideo(NodeFolder nodeFolder,Integer id){

        nodeFolder.setDeleteStatus(2);
        nodeFolder.setFolderType(10);
        nodeFolder.setParentId(id);

        return nodeFolderMapper.insertSelective(nodeFolder);

    }


    /**
     * 创建一级文档库目录
     * @param nodeFolder
     * @return
     */
    public int saveFirstDoc(NodeFolder nodeFolder) {

        nodeFolder.setDeleteStatus(2);
        nodeFolder.setFolderType(20);
        nodeFolder.setParentId(0);

        return nodeFolderMapper.insertSelective(nodeFolder);
    }

    /**
     * 创建文档库子目录
     * @param nodeFolder
     * @param id
     * @return
     */
    public int saveTwoDoc(NodeFolder nodeFolder,Integer id){

        nodeFolder.setDeleteStatus(2);
        nodeFolder.setFolderType(20);
        nodeFolder.setParentId(id);

        return nodeFolderMapper.insertSelective(nodeFolder);

    }


    /**
     * 创建习题库目录
     * @param nodeFolder
     * @return
     */
    public int saveFirstQuiz(NodeFolder nodeFolder) {

        nodeFolder.setDeleteStatus(2);
        nodeFolder.setFolderType(30);
        nodeFolder.setParentId(0);

        return nodeFolderMapper.insertSelective(nodeFolder);
    }

    /**
     * 创建习题库子目录
     * @param nodeFolder
     * @param id
     * @return
     */
    public int saveTwoQuiz(NodeFolder nodeFolder,Integer id){

        nodeFolder.setDeleteStatus(2);
        nodeFolder.setFolderType(30);
        nodeFolder.setParentId(id);

        return nodeFolderMapper.insertSelective(nodeFolder);

    }


    /**
     * 课件资源重命名(命名前根据id查询信息)
     * @param id
     * @return
     */
    public NodeFolderSelDto getNodeFolderById(Integer id){
        //根据id查询信息
        NodeFolder nodeFolder = nodeFolderMapper.selectByPrimaryKey(id);
        //数据转换
        NodeFolderSelDto nodeFolderSelDto = NodeFolderDtoMapper.INSTANCE.entityToSelDto(nodeFolder);

        return nodeFolderSelDto;

    }

}
