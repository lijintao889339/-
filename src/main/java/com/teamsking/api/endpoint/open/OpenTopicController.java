package com.teamsking.api.endpoint.open;

import com.teamsking.api.dto.open.OpenTopicDto;
import com.teamsking.api.endpoint.BaseController;
import com.teamsking.domain.service.open.OpenTopicService;
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
@Api(tags = "班课讨论操作接口")
public class OpenTopicController extends BaseController {

    @Autowired
    OpenTopicService openTopicService;


    @ApiOperation(value = "根据班课id添加讨论", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openTopicDto", value = "班次讨论", required = true, dataType = "OpenTopicDto")
    })
    @PostMapping("/open_topic")
    public Result saveOpenTopic(@RequestBody OpenTopicDto openTopicDto){

        openTopicService.saveOpenTopic(openTopicDto);
        return Result.success();

    }

}
