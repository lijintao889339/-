package com.teamsking.domain.service.open;

import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.*;
import com.teamsking.domain.entity.open.*;
import com.teamsking.domain.entity.study.StudyQuestionnaire;
import com.teamsking.domain.entity.study.StudyVote;
import com.teamsking.domain.repository.OpenActivityMapper;
import com.teamsking.domain.repository.OpenQuestionMapper;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.teamsking.domain.repository.OpenUserMapper;
import com.teamsking.domain.repository.StudyQuestionnaireMapper;
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
    OpenQuestionOptionService openQuestionOptionService;

    /**
     * 获取班次-问卷调查管理列表
     * @param openId
     * @return
     */
    public List<OpenQuestionDto> list(int openId){

        //获取班课下的问卷信息
        OpenQuestion openQuestion = new OpenQuestion();
        openQuestion.setOpenId(openId);
        openQuestion.setDeleteStatus(2);
        List<OpenQuestion> openQuestionList = openQuestionMapper.select(openQuestion);

        if (0 != openQuestionList.size()){

            //查询此班课的学生人数
            OpenUser openUser = new OpenUser();
            openUser.setOpenId(openId);
            openUser.setDeleteStatus(2);
            int count = openUserMapper.selectCount(openUser);

            //获取问卷选项信息
            List<Integer> questionIds = Lists.newArrayList();
            for (OpenQuestion question : openQuestionList){
                questionIds.add(question.getId());
            }
            List<OpenQuestionOption> openQuestionOptions = openQuestionOptionService.getQuestionOptionInfoByQuestionIds(questionIds);

            //数据转换
            List<OpenQuestionOptionDto> questionOptionDtoList = OpenQuestionOptionDtoMapper.INSTANCE.entityListToDtoList(openQuestionOptions);

            //获取问卷选项Ids
            List<Integer> questionOptionIds = Lists.newArrayList();
            for (OpenQuestionOptionDto questionOptionDto : questionOptionDtoList){
                questionOptionIds.add(questionOptionDto.getId());
            }

            //数据转换
            List<OpenQuestionDto> openQuestionDtoList = OpenQuestionDtoMapper.INSTANCE.entityListToDtoList(openQuestionList);

            //遍历集合
            for (OpenQuestionDto openQuestionDto : openQuestionDtoList){

                List<OpenQuestionOptionDto> voteQuestionDtos = Lists.newArrayList();
                for (OpenQuestionOptionDto questionOption: questionOptionDtoList){
                    if (openQuestionDto.getId().intValue() == questionOption.getQuestionId().intValue()){

                        //根据投票选项Id获取学生投票信息
                        StudyQuestionnaire studyQuestionnaire = new StudyQuestionnaire();
                        studyQuestionnaire.setDeleteStatus(2);
                        studyQuestionnaire.setOptionId(questionOption.getId());
                        int questionNums = studyQuestionnaireMapper.selectCount(studyQuestionnaire);

                        //创建一个数值格式化对象
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        //设置精确到小数点后2位
                        numberFormat.setMaximumFractionDigits(2);
                        //转化为百分比
                        String questionRatio = numberFormat.format((float) questionNums / (float) count * 100) + "%";

                        //组装数据
                        questionOption.setQuestionNums(questionNums);
                        questionOption.setQuestionRatio(questionRatio);
                        voteQuestionDtos.add(questionOption);
                    }
                }
                openQuestionDto.setOpenQuestionOptionDtos(voteQuestionDtos);

            }


            return openQuestionDtoList;

        }else {
            List<OpenQuestionDto> openQuestionDtos = null;
            return openQuestionDtos;
        }

    }

    /**
     *添加班次-问卷调查管理
     * @param addOpenQuestionDto
     * @param openId
     * @return
     */
    public int save(AddOpenQuestionDto addOpenQuestionDto, int openId){

        //先添加活动
        OpenActivity openActivity = new OpenActivity();
        openActivity.setTitle(addOpenQuestionDto.getActivityTitle());
        openActivity.setContent(addOpenQuestionDto.getActivityContent());
        openActivity.setDeleteStatus(2);
        openActivity.setType(1);
        openActivity.setOpenId(openId);
        openActivityMapper.insertSelective(openActivity);

        //添加问卷信息
        OpenQuestion openQuestion = new OpenQuestion();
        openQuestion.setDeleteStatus(2);
        openQuestion.setOpenId(openActivity.getOpenId());
        openQuestion.setActivityId(openActivity.getId());
        openQuestion.setTitle(addOpenQuestionDto.getTitle());
        openQuestion.setRemark(addOpenQuestionDto.getRemark());

        //获取开始时间
        Date startTime = new Date();
        openQuestion.setStartTime(startTime);
        //获取结束时间(设置时长)
        if (2 == addOpenQuestionDto.getEndType().intValue()){

            Long durationDay = addOpenQuestionDto.getDurationDay().longValue();
            Long durationHour = addOpenQuestionDto.getDurationHour().longValue();
            Long durationMin = addOpenQuestionDto.getDurationMin().longValue();
            //获取设置的总分钟数
            int totalMin = (int) (durationDay * (24*60) + durationHour * 60 + durationMin);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE,totalMin);

            //结束时间
            Date endTime = calendar.getTime();
            openQuestion.setEndTime(endTime);
        }

        openQuestion.setType(addOpenQuestionDto.getType());
        openQuestion.setIntegralReward(addOpenQuestionDto.getIntegralReward());
        openQuestion.setViewStatistics(addOpenQuestionDto.getIsViewStatistics());
        openQuestion.setPublish(addOpenQuestionDto.getIsPublish());
        openQuestion.setEndType(addOpenQuestionDto.getEndType());
        int count = openQuestionMapper.insertSelective(openQuestion);

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

}
