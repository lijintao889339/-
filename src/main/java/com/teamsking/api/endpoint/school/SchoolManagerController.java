package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.SchoolManagerDto;
import com.teamsking.api.dto.school.SchoolManagerDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.SchoolManager;
import com.teamsking.domain.service.school.SchoolManagerService;
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

/**
*@author linhao
*/
@RestController
@Slf4j
@Api(tags = "学校-管理员操作接口")
public class SchoolManagerController extends BaseController {

    @Autowired
    SchoolManagerService schoolManagerService;

    /**
     * 获取学校-管理员管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校-管理员管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/school_managers")
    public Result schoolManagerList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<SchoolManager> schoolManagerList = schoolManagerService.list();
        List<SchoolManagerDto> schoolManagerDtoList = SchoolManagerDtoMapper.INSTANCE.entityListToDtoList(schoolManagerList);
        return Result.success().addData("pager",warpPage(schoolManagerDtoList));
    }

    /**
     * 添加学校-管理员管理
     * @param schoolManager
     * @return
     */
    @ApiOperation(value = "添加学校-管理员管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "schoolManager", value = "学校-管理员", required = true, dataType = "SchoolManagerDto")
    })
    @PostMapping("/school_manager")
    public Result addSchoolManager(@RequestBody SchoolManagerDto schoolManager){

        SchoolManager schoolManagerEntity = SchoolManagerDtoMapper.INSTANCE.dtoToEntity(schoolManager);
        schoolManagerService.save(schoolManagerEntity);
        return Result.success();
    }

    /**
     * 删除学校-管理员管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校-管理员管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校-管理员的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/school_manager/{id}")
    public Result removeSchoolManager(@PathVariable("id") int id){

        schoolManagerService.remove(id);
        return Result.success();
    }

    /**
     * 删除学校-管理员管理
     * @param schoolManager
     * @param id
     * @return
     */
    @ApiOperation(value = "修改学校-管理员管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schoolManager", value = "学校-管理员", required = true, dataType = "SchoolManagerDto")
    })
    @PutMapping("/school_manager/{id}")
    public Result modifySchoolManager(@RequestBody SchoolManagerDto schoolManager,
                                      @PathVariable("id") int id){

        SchoolManager schoolManagerEntity = SchoolManagerDtoMapper.INSTANCE.dtoToEntity(schoolManager);
        schoolManagerEntity.setId(id);
        schoolManagerService.modify(schoolManagerEntity);
        return Result.success();
    }

}
