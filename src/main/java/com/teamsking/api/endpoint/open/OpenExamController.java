package com.teamsking.api.endpoint.open;

import com.teamsking.api.dto.open.OpenExamDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.service.open.OpenExamService;
import com.teamsking.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "班课考试接口")
public class OpenExamController extends BaseController {


    @Autowired
    OpenExamService openExamService;


    @ApiOperation(value = "添加班课—考试", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openExamDto", value = "班课考试", required = true, dataType = "OpenExamDto")
    })
    @PostMapping("/open_exam")
    public Result saveOpenExam(@RequestBody OpenExamDto openExamDto){

        openExamService.saveOpenExam(openExamDto);

        return Result.success();

    }

}
