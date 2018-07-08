package com.teamsking.api.endpoint.study;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.study.StudyNoteDto;
import com.teamsking.api.dto.study.StudyNoteDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.study.StudyNote;
import com.teamsking.domain.service.study.StudyNoteService;
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
@Api(tags = "学习-笔记管理操作接口")
public class StudyNoteController extends BaseController {

    @Autowired
    StudyNoteService studyNoteService;


    @ApiOperation(value = "学习-笔记管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/study_notes")
    public Result studyNoteList(int pageNo,int pageSize){
        PageHelper.startPage(fixPage(pageNo), fixPage(pageSize));
        List<StudyNote> studyNoteList = studyNoteService.list();
        List<StudyNoteDto> studyNoteDtoList = StudyNoteDtoMapper.INSTANCE.entityListToDtoList(studyNoteList);
        return Result.success().addData("pager", warpPage(studyNoteDtoList));
    }



    @ApiOperation(value = "添加学习-笔记管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyNote", value = "学习笔记管理", required = true, dataType = "StudyNoteDto")
    })
    @PostMapping("/study_note")
    public Result addStudyNote(@RequestBody StudyNoteDto studyNote){

        StudyNote studyNoteEntity = StudyNoteDtoMapper.INSTANCE.dtoToEntity(studyNote);
        studyNoteService.save(studyNoteEntity);
        return Result.success();

    }


    @ApiOperation(value = "删除学习-笔记管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "学习笔记管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/study_note/{id}")
    public Result removeStudyNote(@PathVariable int id){

        studyNoteService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "修改学习-笔记管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "studyNote", value = "学习笔记管理", required = true, dataType = "StudyNoteDto")
    })
    @PutMapping("/study_note/{id}")
    public Result modifyStudyNote(@PathVariable int id,
                                  @RequestBody StudyNoteDto studyNote){

        StudyNote studyNoteEntity = StudyNoteDtoMapper.INSTANCE.dtoToEntity(studyNote);
        studyNoteEntity.setId(id);
        studyNoteService.modify(studyNoteEntity);
        return Result.success();


    }


}
