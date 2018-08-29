package com.teamsking.domain.service.sys;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.teamsking.api.dto.sys.UserStudentNodeDto;
import com.teamsking.api.dto.sys.UserStudentNodeDtoMapper;
import com.teamsking.domain.entity.node.Node;
import com.teamsking.domain.entity.open.Open;
import com.teamsking.domain.entity.open.OpenGroup;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.sys.UserStudent;
import com.teamsking.domain.entity.sys.UserStudentNode;
import com.teamsking.domain.repository.*;
import com.teamsking.domain.service.BaseService;
import com.teamsking.domain.service.open.OpenUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.NumberFormat;
import java.util.List;

@Service
@Slf4j
public class UserStudentNodeService extends BaseService {

    @Autowired
    UserStudentNodeMapper userStudentNodeMapper;
    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    OpenGroupMapper openGroupMapper;
    @Autowired
    UserStudentMapper userStudentMapper;

    @Autowired
    UserStudentService userStudentService;
    @Autowired
    OpenUserService openUserService;

    /**
     * 获取学生观看视频时长列表
     * @param pageNo
     * @param pageSize
     * @param nodeId
     * @param openId
     * @return
     */
    public Page list(int pageNo, int pageSize, int nodeId, int openId) {

        //获取该视频时长
        Node node = nodeMapper.selectByPrimaryKey(nodeId);
        Integer seconds = node.getSeconds();

        //分页
        PageHelper.startPage(pageNo,pageSize);

        //获取该视频的学生观看时长信息
        UserStudentNode userStudentNode = new UserStudentNode();
        userStudentNode.setNodeId(nodeId);
        userStudentNode.setDeleteStatus(2);
        List<UserStudentNode> userStudentNodeList = userStudentNodeMapper.select(userStudentNode);

        if (0 != userStudentNodeList.size()){

            //获取学生Ids
            List<Integer> userStudentIds = Lists.newArrayList();
            for (UserStudentNode studentNode : userStudentNodeList){
                userStudentIds.add(studentNode.getUserStudentId());
            }

            //获取学生信息
            List<UserStudent> userStudentList = userStudentService.getUserStudentListByIds(userStudentIds);

            //获取学生选课信息(包含班组信息)
            List<OpenUser> openUserList = openUserService.getOpenUserLIstByStudentIds(userStudentIds,openId);

            //数据转换
            List<UserStudentNodeDto> userStudentNodeDtoList = UserStudentNodeDtoMapper.INSTANCE.entityListToDtoList(userStudentNodeList);

            //遍历集合，组装数据
            for (UserStudentNodeDto studentNodeDto :  userStudentNodeDtoList){

                //获取用户名
                for (UserStudent userStudent : userStudentList){
                    if (studentNodeDto.getUserStudentId().intValue() == userStudent.getId().intValue()){
                        studentNodeDto.setUserName(userStudent.getUserName());
                    }
                }

                //获取组名
                for (OpenUser openUser : openUserList){
                    if (studentNodeDto.getUserStudentId().intValue() == openUser.getUserStudentId().intValue()){
                        studentNodeDto.setGroupName(openUser.getGroupName());
                    }
                }

                //获取观看进度
                //获取观看时长
                Integer watchSeconds = studentNodeDto.getWatchSeconds();
                //创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                //设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(0);
                //转化为百分比
                String watchRate = numberFormat.format( (float)watchSeconds / (float)seconds * 100) + "%";

                studentNodeDto.setWatchRate(watchRate);
            }

            return convertPage((Page)userStudentNodeList, userStudentNodeDtoList);

        }else {
            Page page = null;
            return page;
        }
    }


    /**
     * 根据班组学生观看视频时长列表
     * @param pageNo
     * @param pageSize
     * @param nodeId
     * @param openId
     * @param groupId
     * @return
     */
    public Page listByGroupId(int pageNo, int pageSize, int nodeId, int openId, Integer groupId) {

        //该班组信息
        OpenGroup openGroup = openGroupMapper.selectByPrimaryKey(groupId);

        //获取该视频时长
        Node node = nodeMapper.selectByPrimaryKey(nodeId);
        Integer seconds = node.getSeconds();

        //获取班组下面的学生
        OpenUser openUser = new OpenUser();
        openUser.setOpenId(openId);
        openUser.setGroupId(groupId);
        openUser.setDeleteStatus(2);
        List<OpenUser> openUserList = openUserMapper.select(openUser);

        if (0 != openUserList.size()){

            //获取学生Ids
            List<Integer> userStudentIds = Lists.newArrayList();
            for (OpenUser newOpenUser : openUserList){
                userStudentIds.add(newOpenUser.getUserStudentId());
            }

            //分页
            PageHelper.startPage(pageNo,pageSize);

            //获取该视频的学生观看时长信息
            Example studentNodeExample = new Example(UserStudentNode.class);
            Example.Criteria cir = studentNodeExample.createCriteria();
            cir.andEqualTo("nodeId",nodeId);
            cir.andEqualTo("deleteStatus",2);
            cir.andIn("userStudentId",userStudentIds);
            List<UserStudentNode> userStudentNodeList = userStudentNodeMapper.selectByExample(studentNodeExample);

            if (0 != userStudentNodeList.size()){

                //获取学生信息
                List<UserStudent> userStudentList = userStudentService.getUserStudentListByIds(userStudentIds);

                //获取学生选课信息(包含班组信息)
                //List<OpenUser> openUserList = openUserService.getOpenUserLIstByStudentIds(userStudentIds,openId);

                //数据转换
                List<UserStudentNodeDto> userStudentNodeDtoList = UserStudentNodeDtoMapper.INSTANCE.entityListToDtoList(userStudentNodeList);

                //遍历集合，组装数据
                for (UserStudentNodeDto studentNodeDto :  userStudentNodeDtoList){

                    //获取用户名
                    for (UserStudent userStudent : userStudentList){
                        if (studentNodeDto.getUserStudentId().intValue() == userStudent.getId().intValue()){
                            studentNodeDto.setUserName(userStudent.getUserName());
                        }
                    }

                    //获取组名
                    studentNodeDto.setGroupName(openGroup.getGroupName());

                    //获取观看时长
                    Integer watchSeconds = studentNodeDto.getWatchSeconds();
                    //创建一个数值格式化对象
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    //设置精确到小数点后2位
                    numberFormat.setMaximumFractionDigits(0);
                    //转化为百分比
                    String watchRate = numberFormat.format( (float)watchSeconds / (float)seconds * 100) + "%";

                    //获取观看进度
                    studentNodeDto.setWatchRate(watchRate);
                }

                return convertPage((Page)userStudentNodeList, userStudentNodeDtoList);

            }else {
                Page page = null;
                return page;
            }

        }else {
            Page page = null;
            return page;
        }
    }


    /**
     * 根据学号或姓名模糊查询学生观看视频时长列表
     * @param pageNo
     * @param pageSize
     * @param nodeId
     * @param openId
     * @param studentNo
     * @param userName
     * @return
     */
    public Page listByStudentNoOrUserName(int pageNo, int pageSize, int nodeId, int openId, String studentNo, String userName) {

        //获取该视频时长
        Node node = nodeMapper.selectByPrimaryKey(nodeId);
        Integer seconds = node.getSeconds();

        //根据模糊查询获取学生信息
        Example userstudentExample = new Example(UserStudent.class);
        Example.Criteria cir = userstudentExample.createCriteria();
        cir.andEqualTo("deleteStatus",2);
        if ("" != studentNo){
            cir.andLike("studentNo","%" + studentNo + "%");
        }
        if ("" != userName){
            cir.andLike("userName","%" + userName + "%");
        }
        //List<OpenUser> openUserList = openUserMapper.selectByExample(openUserExample);
        List<UserStudent> userStudentList = userStudentMapper.selectByExample(userstudentExample);

        if (0 != userStudentList.size()){

            //获取查询到的学生Ids
            List<Integer> sudentIds = Lists.newArrayList();
            for (UserStudent userStudent : userStudentList){
                sudentIds.add(userStudent.getId());
            }

            //再进一步获取sudentIds中该班课下的学生ids
            List<Integer> userStudentIds = Lists.newArrayList();
            List<OpenUser> openUserList = openUserService.getOpenUserLIstByStudentIds(sudentIds,openId);
            if (0 != openUserList.size()){

                for (OpenUser openUser : openUserList){
                    userStudentIds.add(openUser.getUserStudentId());
                }


                //分页
                PageHelper.startPage(pageNo,pageSize);

                //获取该视频的学生观看时长信息
                Example studentNodeExample = new Example(UserStudentNode.class);
                Example.Criteria criteria = studentNodeExample.createCriteria();
                criteria.andEqualTo("nodeId",nodeId);
                criteria.andEqualTo("deleteStatus",2);
                criteria.andIn("userStudentId",userStudentIds);
                List<UserStudentNode> userStudentNodeList = userStudentNodeMapper.selectByExample(studentNodeExample);

                if (0 != userStudentNodeList.size()){

                    //获取学生信息
                    //List<UserStudent> userStudentList = userStudentService.getUserStudentListByIds(userStudentIds);

                    //获取学生选课信息(包含班组信息)
                    //List<OpenUser> newOpenUserList = openUserService.getOpenUserLIstByStudentIds(userStudentIds,openId);

                    //数据转换
                    List<UserStudentNodeDto> userStudentNodeDtoList = UserStudentNodeDtoMapper.INSTANCE.entityListToDtoList(userStudentNodeList);

                    //遍历集合，组装数据
                    for (UserStudentNodeDto studentNodeDto :  userStudentNodeDtoList){

                        //获取用户名
                        for (UserStudent userStudent : userStudentList){
                            if (studentNodeDto.getUserStudentId().intValue() == userStudent.getId().intValue()){
                                studentNodeDto.setUserName(userStudent.getUserName());
                            }
                        }

                        //获取组名
                        for (OpenUser openUser : openUserList){
                            if (studentNodeDto.getUserStudentId().intValue() == openUser.getUserStudentId().intValue()){
                                studentNodeDto.setGroupName(openUser.getGroupName());
                            }
                        }

                        //获取观看进度
                        //获取观看时长
                        Integer watchSeconds = studentNodeDto.getWatchSeconds();
                        //创建一个数值格式化对象
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        //设置精确到小数点后2位
                        numberFormat.setMaximumFractionDigits(0);
                        //转化为百分比
                        String watchRate = numberFormat.format( (float)watchSeconds / (float)seconds * 100) + "%";

                        studentNodeDto.setWatchRate(watchRate);
                    }

                    return convertPage((Page)userStudentNodeList, userStudentNodeDtoList);

                }else {
                    Page page = null;
                    return page;
                }

            }else {
                Page page = null;
                return page;
            }

        }else {
            Page page = null;
            return page;
        }
    }
}
