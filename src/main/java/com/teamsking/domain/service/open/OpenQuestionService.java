package com.teamsking.domain.service.open;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.*;
import com.teamsking.domain.entity.open.*;
import com.teamsking.domain.entity.study.StudyQuestionnaire;
import com.teamsking.domain.entity.sys.UserTeacher;
import com.teamsking.domain.repository.*;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.teamsking.domain.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author linhao
*/
@Service
@Slf4j
public class OpenQuestionService extends BaseService {

    @Autowired
    OpenQuestionMapper openQuestionMapper;
    @Autowired
    OpenUserMapper openUserMapper;
    @Autowired
    StudyQuestionnaireMapper studyQuestionnaireMapper;
    @Autowired
    OpenActivityMapper openActivityMapper;
    @Autowired
    OpenQuestionOptionMapper openQuestionOptionMapper;
    @Autowired
    UserTeacherMapper userTeacherMapper;

    @Autowired
    OpenQuestionOptionService openQuestionOptionService;

    /**
     * 获取班次-问卷调查管理详情
     * @param openId
     * @param questionId
     * @return
     */
    public OpenQuestionDto getQuestion(int openId, int questionId){

        //获取班课下的问卷信息
        OpenQuestion openQuestion = new OpenQuestion();
        openQuestion.setOpenId(openId);
        openQuestion.setDeleteStatus(2);
        openQuestion.setId(questionId);
        OpenQuestion question = openQuestionMapper.selectOne(openQuestion);

        //获取已做问卷人数
        StudyQuestionnaire studyQuestionnaire = new StudyQuestionnaire();
        studyQuestionnaire.setDeleteStatus(2);
        studyQuestionnaire.setOpenId(openId);
        studyQuestionnaire.setQuestionnaireId(questionId);
        int commitUserNums = studyQuestionnaireMapper.selectCount(studyQuestionnaire);

        //数据转换
        OpenQuestionDto openQuestionDto = OpenQuestionDtoMapper.INSTANCE.entityToDto(question);

        //获取问卷选项信息
        List<OpenQuestionOption> openQuestionOptionList = openQuestionOptionService.getQuestionOptionInfoByQuestionId(questionId);

        //数据转换
        List<OpenQuestionOptionDto> questionOptionDtoList = OpenQuestionOptionDtoMapper.INSTANCE.entityListToDtoList(openQuestionOptionList);

        //List<OpenVoteOptionDto> voteOptionDtos = Lists.newArrayList();
        for (OpenQuestionOptionDto questionOption: questionOptionDtoList){

            if (openQuestionDto.getId().intValue() == questionId){
                //根据投票选项Id获取学生投票具体信息
                StudyQuestionnaire studyQuestion = new StudyQuestionnaire();
                studyQuestion.setDeleteStatus(2);
                studyQuestion.setOptionId(questionOption.getId());
                int questionNums = studyQuestionnaireMapper.selectCount(studyQuestion);

                //创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                //设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(0);
                //转化为百分比
                String questionRatio = numberFormat.format((float) questionNums / (float) commitUserNums * 100) + "%";

                //组装数据
                questionOption.setQuestionNums(questionNums);
                questionOption.setQuestionRatio(questionRatio);
                //voteOptionDtos.add(voteOption);
            }
            openQuestionDto.setOpenQuestionOptionDtos(questionOptionDtoList);

        }
        return openQuestionDto;

    }

    /**
     *添加班次-问卷调查管理
     * @param addOpenQuestionDto
     * @param openId
     * @return
     */
    public int save(AddOpenQuestionDto addOpenQuestionDto, int openId){

        //添加问卷信息
        OpenQuestion openQuestion = new OpenQuestion();
        openQuestion.setDeleteStatus(2);
        openQuestion.setOpenId(openId);
        openQuestion.setTitle(addOpenQuestionDto.getTitle());
        openQuestion.setQuestionCover(addOpenQuestionDto.getQuestionCover());
        openQuestion.setRemark(addOpenQuestionDto.getRemark());

        //设置时长
        if (2 == addOpenQuestionDto.getEndType().intValue()){

            openQuestion.setDurationDay(addOpenQuestionDto.getDurationDay());
            openQuestion.setDurationHour(addOpenQuestionDto.getDurationHour());
            openQuestion.setDurationMin(addOpenQuestionDto.getDurationMin());
        }

        openQuestion.setCreateTime(new Date());
        openQuestion.setType(addOpenQuestionDto.getType());
        openQuestion.setPublish(false);
        openQuestion.setIntegralReward(addOpenQuestionDto.getIntegralReward());
        openQuestion.setViewStatistics(addOpenQuestionDto.getIsViewStatistics());
        openQuestion.setEndType(addOpenQuestionDto.getEndType());
        int count = openQuestionMapper.insertSelective(openQuestion);

        //添加问卷选项
        List<OpenQuestionOptionDto> openQuestionOptionDtos = addOpenQuestionDto.getOpenQuestionOptionDtos();

        for (OpenQuestionOptionDto openQuestionOptionDto : openQuestionOptionDtos){
            OpenQuestionOption option = new OpenQuestionOption();
            option.setQuestionId(openQuestion.getId());
            option.setDeleteStatus(2);
            option.setOpenId(openId);
            option.setOption(openQuestionOptionDto.getOption());
            option.setOptionCover(openQuestionOptionDto.getOptionCover());
            openQuestionOptionMapper.insertSelective(option);
        }

        return count;



    }

    /**
     * 创建问卷的同时发放
     * @param addOpenQuestionDto
     * @param openId
     */
    public int savePublishOpenQuestion(AddOpenQuestionDto addOpenQuestionDto, int openId) {

        //添加问卷信息
        OpenQuestion openQuestion = new OpenQuestion();
        openQuestion.setDeleteStatus(2);
        openQuestion.setOpenId(openId);
        openQuestion.setTitle(addOpenQuestionDto.getTitle());
        openQuestion.setQuestionCover(addOpenQuestionDto.getQuestionCover());
        openQuestion.setRemark(addOpenQuestionDto.getRemark());

        //获取开始时间
        Date startTime = new Date();
        openQuestion.setStartTime(startTime);
        //获取结束时间(设置时长)
        if (2 == addOpenQuestionDto.getEndType().intValue()){

            Long durationDay = addOpenQuestionDto.getDurationDay().longValue();
            Long durationHour = addOpenQuestionDto.getDurationHour().longValue();
            Long durationMin = addOpenQuestionDto.getDurationMin().longValue();

            openQuestion.setDurationDay(addOpenQuestionDto.getDurationDay());
            openQuestion.setDurationHour(addOpenQuestionDto.getDurationHour());
            openQuestion.setDurationMin(addOpenQuestionDto.getDurationMin());
            //获取设置的总分钟数
            int totalMin = (int) (durationDay * (24*60) + durationHour * 60 + durationMin);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE,totalMin);

            //结束时间
            Date endTime = calendar.getTime();
            openQuestion.setEndTime(endTime);

        }

        openQuestion.setCreateTime(new Date());
        openQuestion.setType(addOpenQuestionDto.getType());
        openQuestion.setIntegralReward(addOpenQuestionDto.getIntegralReward());
        openQuestion.setViewStatistics(addOpenQuestionDto.getIsViewStatistics());
        openQuestion.setPublish(true);
        openQuestion.setEndType(addOpenQuestionDto.getEndType());
        int count = openQuestionMapper.insertSelective(openQuestion);

        //添加问卷选项
        List<OpenQuestionOptionDto> openQuestionOptionDtos = addOpenQuestionDto.getOpenQuestionOptionDtos();

        for (OpenQuestionOptionDto openQuestionOptionDto : openQuestionOptionDtos){
            OpenQuestionOption option = new OpenQuestionOption();
            option.setQuestionId(openQuestion.getId());
            option.setDeleteStatus(2);
            option.setOpenId(openId);
            option.setOption(openQuestionOptionDto.getOption());
            option.setOptionCover(openQuestionOptionDto.getOptionCover());
            openQuestionOptionMapper.insertSelective(option);
        }

        return count;

    }

    /**
     *删除班次-问卷调查管理
     * @param id
     * @return
     */
    public int remove(int id){

        return openQuestionMapper.deleteByPrimaryKey(id);
    }

    /**
     *添加班次-问卷调查管理
     * @param openQuestion
     * @return
     */
    public int modify(OpenQuestion openQuestion){

        return openQuestionMapper.updateByPrimaryKeySelective(openQuestion);
    }

    /**
     * 获取班课下的问卷列表
     * @param openId
     * @return
     */
    public List<OpenQuestionQueryDto> getQuestionList(int openId) {

        //获取班课已发布下投票信息
        OpenQuestion openQuestion = new OpenQuestion();
        openQuestion.setDeleteStatus(2);
        openQuestion.setOpenId(openId);
        openQuestion.setPublish(true);
        List<OpenQuestion> openQuestionList = openQuestionMapper.select(openQuestion);

        if (0 != openQuestionList.size()){

            //查询此班课的学生人数
            OpenUser openUser = new OpenUser();
            openUser.setOpenId(openId);
            openUser.setDeleteStatus(2);
            int allUserNums = openUserMapper.selectCount(openUser);

            //数据转换
            List<OpenQuestionQueryDto> openQUestionQueryDtoList = OpenQuestionDtoMapper.INSTANCE.entityListToQueryDotList(openQuestionList);

            //遍历集合，组装数据
            for (OpenQuestionQueryDto openQuestionQueryDto : openQUestionQueryDtoList){

                //获取发布人信息
                UserTeacher userTeacher = new UserTeacher();
                userTeacher.setDeleteStatus(2);
                userTeacher.setId(openQuestionQueryDto.getPublishUserId());
                UserTeacher newUserTeacher = userTeacherMapper.selectOne(userTeacher);
                openQuestionQueryDto.setUserName(newUserTeacher.getUserName());

                //获取已投票人数
                StudyQuestionnaire studyQuestionnaire = new StudyQuestionnaire();
                studyQuestionnaire.setDeleteStatus(2);
                studyQuestionnaire.setOpenId(openId);
                studyQuestionnaire.setQuestionnaireId(openQuestionQueryDto.getId());
                int commitUserNums = studyQuestionnaireMapper.selectCount(studyQuestionnaire);

                openQuestionQueryDto.setCommitUserNums(commitUserNums);
                openQuestionQueryDto.setAllUserNums(allUserNums);
            }

            return openQUestionQueryDtoList;
        }else {
            List<OpenQuestionQueryDto> openQUestionQueryDtoList = null;
            return openQUestionQueryDtoList;
        }

    }
}
