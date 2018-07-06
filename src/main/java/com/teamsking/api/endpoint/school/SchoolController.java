package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.SchoolDto;
import com.teamsking.api.dto.school.SchoolDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.School;
import com.teamsking.domain.service.school.SchoolService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*@author linhao
*/
@RestController
@Slf4j
@Api(tags = "学校操作接口")
public class SchoolController extends BaseController {

    @Autowired
    SchoolService schoolService;

    /**
     * 获取学校列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/schools")
    public Result schoolList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<School> schoolList = schoolService.list();
        List<SchoolDto> schoolDtoList = SchoolDtoMapper.INSTANCE.entityListToDtoList(schoolList);
        return Result.success().addData("pager",warpPage(schoolDtoList));
    }

    /**
     * 添加学校
     * @param school
     * @return
     */
    @ApiOperation(value = "添加学校", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "school", value = "学校", required = true, dataType = "SchoolDto")
    })
    @PostMapping("/school")
    public Result addSchool(@RequestBody SchoolDto school){

        School schoolEntity = SchoolDtoMapper.INSTANCE.dtoToEntity(school);
        schoolService.save(schoolEntity);
        return Result.success();
    }

    /**
     * 删除学校
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "学校",required = true,dataType = "int")
    })
    @DeleteMapping("/school/{id}")
    public Result removeSchool(@PathVariable("id") int id){

        schoolService.remove(id);
        return Result.success();
    }

    /**
     * 修改学校
     * @param id
     * @param school
     * @return
     */
    @ApiOperation(value = "修改学校",consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "school",value = "学校",required = true,dataType = "SchoolDto")
    })
    @PutMapping("/school/{id}")
    public Result modifySchool(@PathVariable("id") int id,
                               @RequestBody SchoolDto school){

        School schoolEntity = SchoolDtoMapper.INSTANCE.dtoToEntity(school);
        schoolEntity.setId(id);
        schoolService.modify(schoolEntity);
        return Result.success();
    }

}
