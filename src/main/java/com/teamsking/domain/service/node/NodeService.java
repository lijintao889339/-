package com.teamsking.domain.service.node;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.node.NodeDocDto;
import com.teamsking.api.dto.node.NodeDtoMapper;
import com.teamsking.api.dto.node.NodeNameDto;
import com.teamsking.api.dto.node.NodeVideoDto;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.entity.open.OpenItem;
import com.teamsking.domain.repository.NodeMapper;

import java.text.NumberFormat;
import java.util.List;

import com.teamsking.domain.repository.OpenItemMapper;
import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class NodeService extends BaseService {

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
    public List<Node> getNodeVideoListByOpenId(Integer openId){

        Node node = new Node();
        node.setNodeType(20);
        node.setOpenId(openId);
        node.setDeleteStatus(2);

        List<Node> nodeList = nodeMapper.select(node);

        //List<NodeVideoDto> nodeVideoDtoList = NodeDtoMapper.INSTANCE.entityListVideoDto(nodeList);

        return nodeList;

    }


    /**
     * 根据班课id查询视频(分页)信息(教学管理内容)
     * @param openId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page getNodeVideoByOpenIdList(Integer openId,int pageNo,int pageSize){

        //进行分页
        PageHelper.startPage(pageNo, pageSize);

        Node node = new Node();
        node.setNodeType(20);
        node.setOpenId(openId);
        node.setDeleteStatus(2);

        //查询视频列表
        List<Node> nodeList = nodeMapper.select(node);

        //判断
        if (0 != nodeList.size()){

            //数据转换
            List<NodeNameDto> nodeVideoDtoList = NodeDtoMapper.INSTANCE.entityListToDtoList1(nodeList);

            //遍历
            for (NodeNameDto nodeNameDto:nodeVideoDtoList) {

                //视频时长
                Integer seconds = nodeNameDto.getSeconds();
                //获取观看进度
                //获取观看时长
                Integer watchProgress = nodeNameDto.getWatchProgress();
                //创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                //设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(0);
                //转化为百分比
                String watchRate = numberFormat.format( (float)watchProgress / (float)seconds * 100) + "%";

                nodeNameDto.setWatchRate(watchRate);

            }

            return convertPage((Page)nodeList,nodeVideoDtoList);

        }else {
            Page page =null;
            return page;
        }

    }


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


    /**
     * 根据id查询视频信息(名称和视频长度)
     * @param id
     * @param openId
     * @return
     */
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
