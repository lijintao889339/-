package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenTeacher;
import com.teamsking.domain.repository.OpenTeacherMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenTeacherService {


    @Autowired
    OpenTeacherMapper openTeacherMapper;

    /**
     * 获取班次-教师管理列表
     * @return
     */
    public List<OpenTeacher> list(){

        return openTeacherMapper.selectAll();
    }

    /**
     * 添加班次-教师管理
     * @param openTeacher
     * @return
     */
    public int save(OpenTeacher openTeacher){

        return openTeacherMapper.insert(openTeacher);

    }

    /**
     * 删除班次-教师管理
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openTeacherMapper.deleteByPrimaryKey(id);
    }


    /**
     * 修改班次-教师管理
     * @param openTeacher
     * @return
     */
    public int modify(OpenTeacher openTeacher){

        return openTeacherMapper.updateByPrimaryKeySelective(openTeacher);

    }

}
