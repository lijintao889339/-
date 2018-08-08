package com.teamsking.domain.service.open;

import com.teamsking.domain.entity.open.OpenTeacherConnection;
import com.teamsking.domain.repository.OpenTeacherConnectionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OpenTeacherConnectionService {

    @Autowired
    OpenTeacherConnectionMapper openTeacherConnectionMapper;

    /**
     * 根据班课id查询老师班课关系信息
     * @param openId
     * @return
     */
    public List<OpenTeacherConnection> getTeacherByOpenId(Integer openId){

        OpenTeacherConnection openTeacherConnection = new OpenTeacherConnection();
        openTeacherConnection.setOpenId(openId);

        return openTeacherConnectionMapper.select(openTeacherConnection);

    }

}
