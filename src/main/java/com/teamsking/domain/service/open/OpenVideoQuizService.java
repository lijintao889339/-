package com.teamsking.domain.service.open;


import com.teamsking.domain.entity.open.OpenVideoQuiz;
import com.teamsking.domain.repository.OpenVideoQuizMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenVideoQuizService {

    @Autowired
    OpenVideoQuizMapper openVideoQuizMapper;


    /**
     *获取视频内试题列表
     * @return
     */
    public List<OpenVideoQuiz> list(){

        return openVideoQuizMapper.selectAll();

    }


    /**
     *添加视频内试题
     * @param openVideoQuiz
     * @return
     */
    public int save(OpenVideoQuiz openVideoQuiz){

        return openVideoQuizMapper.insert(openVideoQuiz);

    }


    /**
     *删除视频内试题
     * @param id
     * @return
     */
    public int remove(Integer id){

        return openVideoQuizMapper.deleteByPrimaryKey(id);

    }


    /**
     * 修改视频内试题
     * @param openVideoQuiz
     * @return
     */
    public int modify(OpenVideoQuiz openVideoQuiz){

        return openVideoQuizMapper.updateByPrimaryKeySelective(openVideoQuiz);

    }

}
