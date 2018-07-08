package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.RecommendTeacherDto;
import com.teamsking.api.dto.school.RecommendTeacherDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.RecommendTeacher;
import com.teamsking.domain.service.school.RecommendTeacherService;
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
@Api(tags = "学校-推荐老师管理操作接口")
public class RecommendTeacherController extends BaseController {

    @Autowired
    RecommendTeacherService recommendTeacherService;

    /**
     * 获取学校-推荐老师管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校-推荐老师管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/recommend_teachers")
    public Result recommendTeacherList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<RecommendTeacher> recommendTeacherList = recommendTeacherService.list();
        List<RecommendTeacherDto> recommendTeacherDtoList = RecommendTeacherDtoMapper.INSTANCE.entityListToDtoList(recommendTeacherList);
        return Result.success().addData("pager",warpPage(recommendTeacherDtoList));
    }

    /**
     * 添加学校-推荐老师管理
     * @param recommendTeacher
     * @return
     */
    @ApiOperation(value = "添加学校-推荐老师", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recommendTeacher", value = "学校-推荐老师", required = true, dataType = "RecommendTeacherDto")
    })
    @PostMapping("/recommend_teacher")
    public Result addRecommendTeacher(@RequestBody RecommendTeacherDto recommendTeacher){

        RecommendTeacher recommendTeacherEntity = RecommendTeacherDtoMapper.INSTANCE.dtoToEntity(recommendTeacher);
        recommendTeacherService.save(recommendTeacherEntity);
        return Result.success();
    }

    /**
     * 删除学校-推荐老师管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校-推荐老师", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校-推荐老师的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/recommend_teacher/{id}")
    public Result removeRecommendTeacher(@PathVariable("id") int id){

        recommendTeacherService.remove(id);
        return Result.success();
    }

    /**
     * 修改学校-推荐老师管理
     * @param recommendTeacher
     * @param id
     * @return
     */
    @ApiOperation(value = "修改学校-推荐老师", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recommendTeacher", value = "学校-推荐老师", required = true, dataType = "RecommendTeacherDto")
    })
    @PutMapping("/recommend_teacher/{id}")
    public Result modifyRecommendTeacher(@RequestBody RecommendTeacherDto recommendTeacher,
                                         @PathVariable("id") int id){

        RecommendTeacher recommendTeacherEntity = RecommendTeacherDtoMapper.INSTANCE.dtoToEntity(recommendTeacher);
        recommendTeacherEntity.setId(id);
        recommendTeacherService.modify(recommendTeacherEntity);
        return Result.success();
    }

}
