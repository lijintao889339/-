package com.teamsking.api.endpoint.study;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.study.StudyVisitDto;
import com.teamsking.api.dto.study.StudyVisitDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.study.StudyVisit;
import com.teamsking.domain.service.study.StudyVisitService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api("学习-进度操作接口")
public class StudyVisitController extends BaseController {

    @Autowired
    StudyVisitService studyVisitService;



    @ApiOperation(value = "学习-进度管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/study_visits")
    public Result studyVisitList(int pageNo,int pageSize){

        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<StudyVisit> studyVisitList = studyVisitService.list();
        List<StudyVisitDto> studyVisitDtoList = StudyVisitDtoMapper.INSTANCE.entityListToDtoList(studyVisitList);
        return Result.success().addData("pager", warpPage(studyVisitDtoList));

    }



    @ApiOperation(value = "添加学习-进度管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyVisit", value = "学习进度管理", required = true, dataType = "StudyVisitDto")
    })
    @PostMapping("/study_visit")
    public Result addStudyVisit(@RequestBody StudyVisitDto studyVisit){

        StudyVisit studyVisitEntity = StudyVisitDtoMapper.INSTANCE.dtoToEntity(studyVisit);
        studyVisitService.save(studyVisitEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除学习-进度管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "学习进度管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/study_visit/{id}")
    public Result removeStudyVisit(@PathVariable int id){

        studyVisitService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改学习-进度管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyVisit", value = "学习进度管理", required = true, dataType = "StudyVisitDto")
    })
    @PutMapping("/study_visit/{id}")
    public Result modifyStudyVisit(@PathVariable int id,
                                   @RequestBody StudyVisitDto studyVisit){

        StudyVisit studyVisitEntity = StudyVisitDtoMapper.INSTANCE.dtoToEntity(studyVisit);
        studyVisitEntity.setId(id);
        studyVisitService.modify(studyVisitEntity);
        return Result.success();

    }


}
