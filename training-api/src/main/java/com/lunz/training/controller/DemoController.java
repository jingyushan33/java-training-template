package com.lunz.training.controller;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.segments.GroupBySegmentList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lunz.query.MpAll;
import com.lunz.query.MpPager;
import com.lunz.util.MpUtil;
import com.lunz.util.MyQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lunz.training.BaseController;
import com.lunz.training.bean.Demo;
import com.lunz.training.convert.DemoConvertMappers;
import com.lunz.training.dto.DemoDTO;
import com.lunz.training.group.AddGroup;
import com.lunz.training.group.UpdateGroup;
import com.lunz.training.service.DemoService;
import com.lunz.training.vo.DemoVO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 演示
 *
 * @author haha
 */
@Api(value = "测试API", tags = {"测试API"})
@Validated
@RestController
@RequestMapping("/api/v1/training")
public class DemoController extends BaseController {


    @Autowired
    private DemoService demoService;

    @ApiOperation("创建用户（使用分组验证器）")
    @PostMapping
    public DemoVO createUser(@RequestBody @Validated({AddGroup.class}) @Valid DemoDTO demoDTO) {
        Demo demo = demoService.saveDemo(demoDTO);
        return DemoConvertMappers.MAPPER.convertVO(demo);
    }

    @ApiOperation("修改用户")
    @PutMapping
    public void updateUser(@RequestBody @Validated({UpdateGroup.class}) @Valid DemoDTO demoDTO) {
        demoService.modifyDemo(demoDTO);
    }

    @ApiOperation("新增用户（不使用分组验证器）")
    @PutMapping("/base")
    public void addUser(@RequestBody @Valid DemoDTO demoDTO) {
        Demo demo = DemoConvertMappers.MAPPER.convertBean(demoDTO);
        demoService.save(demo);
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    public void deleteUser(String id) {
        demoService.removeById(id);
    }

    @ApiOperation("获取用户详情")
    @GetMapping("/{id}")
    public DemoVO getUserDetail(@PathVariable("id") @NotBlank(message = "id不能为空") String id) {
        Demo demo = demoService.getById(id);
        return DemoConvertMappers.MAPPER.convertVO(demo);
    }

    @ApiOperation("获取分页数据")
    @PostMapping("/page")
    public IPage<DemoVO> page(@RequestBody MpPager<DemoDTO> page) {
        MpPager<Demo> newPage = DemoConvertMappers.MAPPER.convertPage(page);
        MyQueryWrapper<Demo> queryWrapper = MpUtil.convertObjectToMP(newPage);
        IPage<Demo> demoPage = demoService.page(queryWrapper.getPage(), queryWrapper.getQueryWrapper());
        return DemoConvertMappers.MAPPER.convertPageList((Page<Demo>) demoPage);
    }

    @ApiOperation("查看所有")
    @PostMapping("/list")
    public List<DemoVO> list(@RequestBody MpAll<DemoDTO> page) {
        MpAll<Demo> newAll = DemoConvertMappers.MAPPER.convertAll(page);
        MyQueryWrapper<Demo> queryWrapper = MpUtil.convertObjectToMP(newAll);
        List<Demo> demoList = demoService.list(queryWrapper.getQueryWrapper());
        return DemoConvertMappers.MAPPER.convertList(demoList);
    }


    @ApiOperation(value = "查看所有以及自定义分组聚合")
    @PostMapping("/list/group")
    public List<DemoVO> listGroupBy(@RequestBody MpAll<DemoDTO> page) {
        MpAll<Demo> newAll = DemoConvertMappers.MAPPER.convertAll(page);
        MyQueryWrapper<Demo> queryWrapper = MpUtil.convertObjectToMP(newAll);
        GroupBySegmentList groupBys = queryWrapper.getQueryWrapper().getExpression().getGroupBy();
        // 默认查看所有
        if (!CollectionUtils.isEmpty(groupBys)) {
            // 在这里自定义需要附加查询聚合列
            StringBuilder select = new StringBuilder("sum(money) as money");
            // 将需要分组的列，加入查询列
            for (ISqlSegment sqlSegment : groupBys) {
                select.append(",").append(sqlSegment.getSqlSegment());
            }
            queryWrapper.getQueryWrapper().select(select.toString());
        }
        List<Demo> demoList = demoService.list(queryWrapper.getQueryWrapper());
        return DemoConvertMappers.MAPPER.convertList(demoList);
    }

    @ApiOperation("测试远程调用")
    @GetMapping("/rpc")
    public String test() {
        return demoService.testRpc();
    }


}
