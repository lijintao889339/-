package com.teamsking.domain.service.open;

import com.teamsking.api.dto.open.OpenExamDto;
import com.teamsking.api.dto.open.OpenExamDtoMapper;
import com.teamsking.domain.entity.open.OpenExam;
import com.teamsking.domain.repository.OpenExamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenExamService {

    @Autowired
    OpenExamMapper openExamMapper;


    /**
     * 根据班课id创建考试
     * @param openExamDto
     * @return
     */
    public int saveOpenExam(OpenExamDto openExamDto){

        OpenExam openExamEntity = OpenExamDtoMapper.INSTANCE.dtoToEntity(openExamDto);
        openExamEntity.setDeleteStatus(2);

        return openExamMapper.insert(openExamEntity);
    }

}
