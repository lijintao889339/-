package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.AddOpenTeacherDto;
import com.teamsking.api.dto.open.OpenTeacherDto;
import com.teamsking.api.dto.open.OpenTeacherDtoMapper;
import com.teamsking.api.dto.open.OpenTeacherNameDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenTeacher;
import com.teamsking.domain.service.open.OpenTeacherService;
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
@Api(tags = "班次-教师管理操作接口")
public class OpenTeacherController extends BaseController {

    @Autowired
    OpenTeacherService openTeacherService;



    @ApiOperation(value = "班次-教师管理列表", notes = "可分页", produces = "application/json")
    @GetMapping("/open_teachers")
    public Result openTeacherList(){

        List<OpenTeacher> openTeacherList = openTeacherService.list();

        List<OpenTeacherNameDto> openTeacherNameDtoList = OpenTeacherDtoMapper.INSTANCE.entityListToNameDtoList(openTeacherList);

        return Result.success().addData("pager", warpPage(openTeacherNameDtoList));

    }


    @ApiOperation(value = "添加班次-教师管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "addOpenTeacher", value = "班次教师管理", required = true, dataType = "AddOpenTeacherDto")
    })
    @PostMapping("/open_teacher")
    public Result addOpenTeacher(@RequestBody AddOpenTeacherDto addOpenTeacherDto){

        openTeacherService.save(addOpenTeacherDto);
        return Result.success();

    }


    @ApiOperation(value = "添加班次-教师管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次教师管理", required = true, dataType = "Integer")
    })
    @DeleteMapping("/open_teacher/{id}")
    public Result removeOpenTeacher(@PathVariable int id){

        openTeacherService.remove(id);
        return Result.success();

    }



    @ApiOperation(value = "添加班次-教师管理", consumes= "application/json")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "id", value = "班次教师管理", required = true, dataType = "Integer")
    })
    @PutMapping("/open_teacher/{id}")
    public Result modifyOpenTeacher(@PathVariable int id,
                                    @RequestBody OpenTeacherDto openTeacher){

        OpenTeacher openTeacherEntity = OpenTeacherDtoMapper.INSTANCE.dtoToEntity(openTeacher);
        openTeacherEntity.setId(id);
        openTeacherService.modify(openTeacherEntity);

        return Result.success();

    }

}
