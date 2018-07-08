package com.teamsking.api.endpoint.open;

import com.github.pagehelper.PageHelper;
import com.teamsking.api.dto.open.OpenWareDto;
import com.teamsking.api.dto.open.OpenWareDtoMapper;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.entity.open.OpenWare;
import com.teamsking.domain.service.open.OpenWareService;
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
@Api(tags = "班次-课件管理操作接口")
public class OpenWareController extends BaseController {

    @Autowired
    OpenWareService openWareService;

    /**
     * 获取班次-课件管理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "班次-课件管理列表", notes = "可分页", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, example = "10")
    })
    @GetMapping("/open_wares")
    public Result openWareList(int pageNo, int pageSize){

        PageHelper.startPage(fixPage(pageNo),fixPage(pageSize));
        List<OpenWare> openWareList = openWareService.list();
        List<OpenWareDto> openWareDtoList = OpenWareDtoMapper.INSTANCE.entityListToDtoList(openWareList);
        return Result.success().addData("pager",warpPage(openWareDtoList));
    }

    /**
     * 添加班次-课件管理
     * @param openWare
     * @return
     */
    @ApiOperation(value = "添加班次-课件", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openWare", value = "班次-课件", required = true, dataType = "OpenWareDto")
    })
    @PostMapping("/open_ware")
    public Result addOpenWare(@RequestBody OpenWareDto openWare){

        OpenWare openWareEntity = OpenWareDtoMapper.INSTANCE.dtoToEntity(openWare);
        openWareService.save(openWareEntity);
        return Result.success();
    }

    /**
     * 删除班次-课件管理
     * @param id
     * @return
     */
    @ApiOperation(value = "删除班次-课件", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "班次-课件的主键", required = true, dataType = "int")
    })
    @DeleteMapping("/open_ware/{id}")
    public Result removeOpenWare(@PathVariable("id") int id){

        openWareService.remove(id);
        return Result.success();
    }

    @ApiOperation(value = "修改班次-课件", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openWare", value = "班次-课件", required = true, dataType = "OpenWareDto")
    })
    @PutMapping("/open_ware/{id}")
    public Result modifyOpenWare(@PathVariable("id") int id,
                                 @RequestBody OpenWareDto openWare){

        OpenWare openWareEntity = OpenWareDtoMapper.INSTANCE.dtoToEntity(openWare);
        openWareEntity.setId(id);
        openWareService.modify(openWareEntity);
        return Result.success();
    }


}
