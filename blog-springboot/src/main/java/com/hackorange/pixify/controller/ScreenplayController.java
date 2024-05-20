package com.hackorange.pixify.controller;

import cn.hutool.core.date.DateTime;
import com.hackorange.pixify.entity.Screenplay;
import com.hackorange.pixify.service.ScreenplayService;
import com.hackorange.pixify.vo.Result;
import com.hackorange.pixify.vo.ScreenplayVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "剧本模块")
@RestController()
public class ScreenplayController {
    @Autowired
    private ScreenplayService screenplayService;
    @ApiOperation(value = "保存剧本")
    @PostMapping("/screenplay/save")
    public Result<Boolean> saveScreenplay(@RequestBody  ScreenplayVO screenplayVO) {

        Screenplay screenplay=Screenplay.builder()
                .name(screenplayVO.getName())
                .storyBackground(screenplayVO.getStoryBackground())
                .playType(screenplayVO.getPlayType())
                .type(screenplayVO.getStoreType())
                .state(screenplayVO.getState())
                .createdBy(screenplayVO.getCreatedBy())
                .createdTime(DateTime.now()).build();
        return Result.ok(screenplayService.save(screenplay));
    }
    @ApiOperation(value = "剧本列表")
    @GetMapping("/screenplay/list")
    public Result<List<Screenplay>> queryScreenplayList() {
        return Result.ok(screenplayService.list());
    }
}
