package com.teamsking.domain.service.open;


import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.*;
import com.teamsking.domain.entity.open.OpenActivity;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.open.OpenVote;
import com.teamsking.domain.entity.open.OpenVoteOption;
import com.teamsking.domain.entity.study.StudyVote;
import com.teamsking.domain.entity.sys.UserTeacher;
import com.teamsking.domain.repository.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenVoteService extends BaseService {

    @Autowired
    OpenVoteMapper openVoteMapper;
    @Autowired
    StudyVoteMapper studyVoteMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    OpenActivityMapper openActivityMapper;
    @Autowired
    OpenVoteOptionMapper openVoteOptionMapper;
    @Autowired
    UserTeacherMapper userTeacherMapper;

    @Autowired
    OpenVoteOptionService openVoteOptionService;

    /**
     * 获取班次-投票详情
     * @param openId
     * @param voteId
     * @return
     */
    public OpenVoteDto getVote(int openId, int voteId){

        //获取班课下的投票信息
        OpenVote openVote = new OpenVote();
        openVote.setOpenId(openId);
        openVote.setDeleteStatus(2);
        openVote.setId(voteId);
        OpenVote vote = openVoteMapper.selectOne(openVote);

        //获取已投票人数
        StudyVote studyVote = new StudyVote();
        studyVote.setDeleteStatus(2);
        studyVote.setOpenId(openId);
        studyVote.setVoteId(voteId);
        int commitUserNums = studyVoteMapper.selectCount(studyVote);

        //获取投票选项信息
        List<OpenVoteOption> openVoteOptionList = openVoteOptionService.getVoteOptionInfoByVoteId(voteId);

        //数据转换
        List<OpenVoteOptionDto> voteOptionDtoList = OpenVoteOptionDtoMapper.INSTANCE.entityListToDtoList(openVoteOptionList);

        //获取学生选票Ids
        List<Integer> voteOptionIds = Lists.newArrayList();
        for (OpenVoteOptionDto voteOptionDto : voteOptionDtoList){
            voteOptionIds.add(voteOptionDto.getId());
        }

        //数据转换
        OpenVoteDto openVoteDto = OpenVoteDtoMapper.INSTANCE.entityToDto(vote);

        //获取投票选项信息
        List<OpenVoteOptionDto> voteOptionDtos = Lists.newArrayList();
        for (OpenVoteOptionDto voteOption: voteOptionDtoList){

            if (openVoteDto.getId().intValue() == voteId){
                //根据投票选项Id获取学生投票具体信息
                StudyVote newStudyVote = new StudyVote();
                newStudyVote.setDeleteStatus(2);
                newStudyVote.setOptionId(voteOption.getId());
                int voteNums = studyVoteMapper.selectCount(newStudyVote);

                //创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                //设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(0);
                //转化为百分比
                String voteRatio = numberFormat.format((float) voteNums / (float) commitUserNums * 100) + "%";

                //组装数据
                voteOption.setVoteNums(voteNums);
                voteOption.setVoteRatio(voteRatio);
                voteOptionDtos.add(voteOption);
            }
            openVoteDto.setOpenVoteOptionDtos(voteOptionDtos);

        }
        return openVoteDto;
    }


    /**
     * 添加班次-投票管理
     * @param addOpenVoteDto
     * @param openId
     * @return
     */
    public int save(AddOpenVoteDto addOpenVoteDto, int openId){

        //添加投票信息
        OpenVote openVote = new OpenVote();
        openVote.setDeleteStatus(2);
        openVote.setOpenId(openId);
        openVote.setTitle(addOpenVoteDto.getTitle());
        openVote.setVoteCover(addOpenVoteDto.getVoteCover());
        openVote.setDescription(addOpenVoteDto.getDescription());

        //设置时长
        if (2 == addOpenVoteDto.getEndType().intValue()){

            openVote.setDurationDay(addOpenVoteDto.getDurationDay());
            openVote.setDurationHour(addOpenVoteDto.getDurationHour());
            openVote.setDurationMin(addOpenVoteDto.getDurationMin());
        }

        openVote.setCreateTime(new Date());
        openVote.setType(addOpenVoteDto.getType());
        openVote.setIntegralReward(addOpenVoteDto.getIntegralReward());
        openVote.setViewStatistics(addOpenVoteDto.getIsViewStatistics());
        openVote.setPublish(false);
        openVote.setEndType(addOpenVoteDto.getEndType());
        int count = openVoteMapper.insertSelective(openVote);

        //添加投票选项
        List<OpenVoteOptionDto> openVoteOptionDtos = addOpenVoteDto.getOpenVoteOptionDtos();
        //for (int i = 0; i <= openVoteOptionDtos.size(); i++ ){
        for (OpenVoteOptionDto optionDto : openVoteOptionDtos){
            OpenVoteOption option = new OpenVoteOption();
            option.setDeleteStatus(2);
            option.setOpenId(openId);
            option.setVoteId(openVote.getId());
            option.setVoteOption(optionDto.getVoteOption());
            option.setOptionCover(optionDto.getOptionCover());
            openVoteOptionMapper.insertSelective(option);
        }

        return count;
    }

    /**
     * 创建投票的时候同时发放
     * @param addOpenVoteDto
     * @param openId
     */
    public int saveAndPublishOpenVote(AddOpenVoteDto addOpenVoteDto, int openId) {

        //添加投票信息
        OpenVote openVote = new OpenVote();
        openVote.setDeleteStatus(2);
        openVote.setOpenId(openId);
        openVote.setTitle(addOpenVoteDto.getTitle());
        openVote.setVoteCover(addOpenVoteDto.getVoteCover());
        openVote.setDescription(addOpenVoteDto.getDescription());

        //获取开始时间
        Date startTime = new Date();
        openVote.setStartTime(startTime);
        //获取结束时间(设置时长)
        if (2 == addOpenVoteDto.getEndType().intValue()){

            Long durationDay = addOpenVoteDto.getDurationDay().longValue();
            Long durationHour = addOpenVoteDto.getDurationHour().longValue();
            Long durationMin = addOpenVoteDto.getDurationMin().longValue();
            //获取设置的总分钟数
            int totalMin = (int) (durationDay * (24*60) + durationHour * 60 + durationMin);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE,totalMin);

            //结束时间
            Date endTime = calendar.getTime();
            openVote.setEndTime(endTime);

            openVote.setDurationDay(addOpenVoteDto.getDurationDay());
            openVote.setDurationHour(addOpenVoteDto.getDurationHour());
            openVote.setDurationMin(addOpenVoteDto.getDurationMin());
        }

        openVote.setCreateTime(new Date());
        openVote.setType(addOpenVoteDto.getType());
        openVote.setIntegralReward(addOpenVoteDto.getIntegralReward());
        openVote.setViewStatistics(addOpenVoteDto.getIsViewStatistics());
        openVote.setPublish(true);
        openVote.setEndType(addOpenVoteDto.getEndType());
        int count = openVoteMapper.insertSelective(openVote);

        //添加投票选项
        List<OpenVoteOptionDto> openVoteOptionDtos = addOpenVoteDto.getOpenVoteOptionDtos();
        //for (int i = 0; i <= openVoteOptionDtos.size(); i++ ){
        for (OpenVoteOptionDto optionDto : openVoteOptionDtos){
            OpenVoteOption option = new OpenVoteOption();
            option.setDeleteStatus(2);
            option.setOpenId(openId);
            option.setVoteId(openVote.getId());
            option.setVoteOption(optionDto.getVoteOption());
            option.setOptionCover(optionDto.getOptionCover());
            openVoteOptionMapper.insertSelective(option);
        }

        return count;
    }

    /**
     * 删除班次-投票管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openVoteMapper.deleteByPrimaryKey(id);

    }

    /**
     * 修改班次-投票管理
     * @param openVote
     * @return
     */
    public int modify(OpenVote openVote){

        return openVoteMapper.updateByPrimaryKeySelective(openVote);

    }


    /**
     * 获取班课下的投票列表
     * @param openId
     * @return
     */
    public List<OpenVoteQueryDto> getVoteList(int openId) {

        //获取班课已发布下投票信息
        OpenVote openVote = new OpenVote();
        openVote.setDeleteStatus(2);
        openVote.setOpenId(openId);
        openVote.setPublish(true);
        List<OpenVote> openVoteList = openVoteMapper.select(openVote);

        if (0 != openVoteList.size()){

            //查询此班课的学生人数
            OpenUser openUser = new OpenUser();
            openUser.setOpenId(openId);
            openUser.setDeleteStatus(2);
            int allUserNums = openUserMapper.selectCount(openUser);

            //数据转换
            List<OpenVoteQueryDto> openVoteQueryDtoList = OpenVoteDtoMapper.INSTANCE.entityListToQueryDotList(openVoteList);

            //遍历集合，组装数据
            for (OpenVoteQueryDto openVoteQueryDto : openVoteQueryDtoList){

                //获取发布人信息
                UserTeacher userTeacher = new UserTeacher();
                userTeacher.setDeleteStatus(2);
                userTeacher.setId(openVoteQueryDto.getPublishUserId());
                UserTeacher newUserTeacher = userTeacherMapper.selectOne(userTeacher);
                openVoteQueryDto.setUserName(newUserTeacher.getUserName());

                //获取已投票人数
                StudyVote studyVote = new StudyVote();
                studyVote.setDeleteStatus(2);
                studyVote.setOpenId(openId);
                studyVote.setVoteId(openVoteQueryDto.getId());
                int commitUserNums = studyVoteMapper.selectCount(studyVote);

                openVoteQueryDto.setCommitUserNums(commitUserNums);
                openVoteQueryDto.setAllUserNums(allUserNums);
            }

            return openVoteQueryDtoList;
        }else {
            List<OpenVoteQueryDto> openVoteQueryDtoList = null;
            return openVoteQueryDtoList;
        }
    }
}
