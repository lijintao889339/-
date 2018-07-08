package com.teamsking.api.endpoint.school;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.school.SchoolFriendshipDto;
import com.teamsking.api.dto.school.SchoolFriendshipDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.school.SchoolFriendship;
import com.teamsking.domain.service.school.SchoolFriendshipService;
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
@Api(tags = "学校-友情链接管理操作接口")
public class SchoolFriendshipController extends BaseController {

    @Autowired
    SchoolFriendshipService schoolFriendshipService;

    /**
     * 获取学校-友情链接管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "学校-友情链接管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value ="页码", required = true, example = "1") ,
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/school_friendships")
    public Result schoolFriendshipList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<SchoolFriendship> schoolFriendshipList = schoolFriendshipService.list();
        List<SchoolFriendshipDto> schoolFriendshipDtoList = SchoolFriendshipDtoMapper.INSTANCE.entityListToDtoList(schoolFriendshipList);
        return Result.success().addData("pager",warpPage(schoolFriendshipDtoList));
    }

    /**
     * 添加学校-友情链接管理
     * @param schoolFriendship
     * @return
     */
    @ApiOperation(value = "添加学校-友情链接管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "schoolFriendship", value = "学校-友情链接", required = true, dataType = "SchoolFriendshipDto")
    })
    @PostMapping("/school_friendship")
    public Result addSchoolFriendship(@RequestBody SchoolFriendshipDto schoolFriendship){

        SchoolFriendship schoolFriendshipEntity = SchoolFriendshipDtoMapper.INSTANCE.dtoToEntity(schoolFriendship);
        schoolFriendshipService.save(schoolFriendshipEntity);
        return Result.success();
    }

    /**
     * 删除学校-友情链接管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除学校-友情链接管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校-友情链接的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/school_friendship/{id}")
    public Result removeSchoolFriendship(@PathVariable("id") int id){

        schoolFriendshipService.remove(id);
        return Result.success();
    }

    /**
     * 修改学校-友情链接管理
     * @param id
     * @param schoolFriendship
     * @return
     */
    @ApiOperation(value = "修改学校-友情链接管理", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schoolFriendship", value = "学校-友情链接", required = true, dataType = "SchoolFriendshipDto")
    })
    @PutMapping("/school_friendship/{id}")
    public Result modifySchoolFriendship(@PathVariable("id") int id,
                         @RequestBody SchoolFriendshipDto schoolFriendship){

        SchoolFriendship schoolFriendshipEntity = SchoolFriendshipDtoMapper.INSTANCE.dtoToEntity(schoolFriendship);
        schoolFriendshipEntity.setId(id);
        schoolFriendshipService.modify(schoolFriendshipEntity);
        return Result.success();
    }

}
