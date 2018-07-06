package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.SchoolAboutDto;
import com.teamsking.api.dto.school.SchoolAboutDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.SchoolAbout;
import com.teamsking.domain.service.school.SchoolAboutService;
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
@Api(tags = "学校-'关于我们'操作接口")
public class SchoolAboutController extends BaseController {

    @Autowired
    SchoolAboutService schoolAboutService;

    /**
     * 获取学校-"关于我们"管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校-关于我们管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/school_abouts")
    public Result schoolAboutList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<SchoolAbout> schoolAboutList = schoolAboutService.list();
        List<SchoolAboutDto> schoolAboutDtoList = SchoolAboutDtoMapper.INSTANCE.entityListToDtoList(schoolAboutList);
        return Result.success().addData("pager",warpPage(schoolAboutDtoList));
    }

    /**
     * 添加学校-"关于我们"管理
     * @param schoolAbout
     * @return
     */
    @ApiOperation(value = "添加学校-关于我们", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "schoolAbout", value = "学校-关于我们", required = true, dataType = "SchoolAboutDto")
    })
    @PostMapping("/school_about")
    public Result addSchoolAbout(@RequestBody SchoolAboutDto schoolAbout){

        SchoolAbout schoolAboutEntity = SchoolAboutDtoMapper.INSTANCE.dtoToEntity(schoolAbout);
        schoolAboutService.save(schoolAboutEntity);
        return Result.success();
    }

    /**
     * 删除学校-"关于我们"管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校-关于我们", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校-关于我们的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/school_about/{id}")
    public Result removeSchoolAbout(@PathVariable("id") int id){

        schoolAboutService.remove(id);
        return Result.success();
    }

    /**
     * 修改学校-"关于我们"管理
     * @param schoolAbout
     * @param id
     * @return
     */
    @ApiOperation(value = "修改学校-关于我们", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schoolAbout", value = "学校-关于我们", required = true, dataType = "SchoolAboutDto")
    })
    @PutMapping("/school_about/{id}")
    public Result modifySchoolAbout(@RequestBody SchoolAboutDto schoolAbout,
                                    @PathVariable("id") int id){

        SchoolAbout schoolAboutEntity = SchoolAboutDtoMapper.INSTANCE.dtoToEntity(schoolAbout);
        schoolAboutEntity.setId(id);
        schoolAboutService.modify(schoolAboutEntity);
        return Result.success();
    }

}
