package com.teamsking.domain.service.open;


import com.google.common.collect.Lists;
import com.teamsking.api.dto.open.OpenVoteDto;
import com.teamsking.api.dto.open.OpenVoteDtoMapper;
import com.teamsking.api.dto.open.OpenVoteOptionDto;
import com.teamsking.api.dto.open.OpenVoteOptionDtoMapper;
import com.teamsking.domain.entity.open.OpenUser;
import com.teamsking.domain.entity.open.OpenVote;
import com.teamsking.domain.entity.open.OpenVoteOption;
import com.teamsking.domain.entity.study.StudyVote;
import com.teamsking.domain.repository.OpenUserMapper;
import com.teamsking.domain.repository.OpenVoteMapper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import com.teamsking.domain.repository.StudyVoteMapper;
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
    OpenVoteOptionService openVoteOptionService;

    /**
     * 获取班次-投票管理列表
     * @param openId
     * @return
     */
    public List<OpenVoteDto> list(int openId){

        //获取班课下的投票信息
        OpenVote openVote = new OpenVote();
        openVote.setOpenId(openId);
        openVote.setDeleteStatus(2);
        List<OpenVote> openVoteList = openVoteMapper.select(openVote);

        if (0 != openVoteList.size()){

            //查询此班课的学生人数
            OpenUser openUser = new OpenUser();
            openUser.setOpenId(openId);
            openUser.setDeleteStatus(2);
            int count = openUserMapper.selectCount(openUser);

            //获取投票选项信息
            List<Integer> voteIds = Lists.newArrayList();
            for (OpenVote vote : openVoteList){
                voteIds.add(vote.getId());
            }
            List<OpenVoteOption> openVoteOptionList = openVoteOptionService.getVoteOPtionInfoByVoteIds(voteIds);

            //数据转换
            List<OpenVoteOptionDto> voteOptionDtoList = OpenVoteOptionDtoMapper.INSTANCE.entityListToDtoList(openVoteOptionList);

            //获取学生选票Ids
            List<Integer> voteOptionIds = Lists.newArrayList();
            for (OpenVoteOptionDto voteOptionDto : voteOptionDtoList){
                voteOptionIds.add(voteOptionDto.getId());
            }

            //数据转换
            List<OpenVoteDto> openVoteDtoList = OpenVoteDtoMapper.INSTANCE.entityListToDtoList(openVoteList);

            //遍历集合
            for (OpenVoteDto openVoteDto : openVoteDtoList){

                List<OpenVoteOptionDto> voteOptionDtos = Lists.newArrayList();
                for (OpenVoteOptionDto voteOption: voteOptionDtoList){
                    if (openVoteDto.getId().intValue() == voteOption.getVoteId().intValue()){

                        //根据投票选项Id获取学生投票信息
                        StudyVote studyVote = new StudyVote();
                        studyVote.setDeleteStatus(2);
                        studyVote.setOptionId(voteOption.getId());
                        int voteNums = studyVoteMapper.selectCount(studyVote);

                        //创建一个数值格式化对象
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        //设置精确到小数点后2位
                        numberFormat.setMaximumFractionDigits(2);
                        //转化为百分比
                        String voteRatio = numberFormat.format((float) voteNums / (float) count * 100) + "%";

                        //组装数据
                        voteOption.setVoteNums(voteNums);
                        voteOption.setVoteRatio(voteRatio);
                        voteOptionDtos.add(voteOption);
                    }
                }
                openVoteDto.setOpenVoteOptionDtos(voteOptionDtos);

            }


            return openVoteDtoList;

        }else {
            List<OpenVoteDto> openVoteDtos = null;
            return openVoteDtos;
        }
    }


    /**
     * 添加班次-投票管理
     * @param openVote
     * @return
     */
    public int save(OpenVote openVote){

        return openVoteMapper.insert(openVote);

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

}
