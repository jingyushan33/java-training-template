package com.lunz.training.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunz.training.mapper.DemoMapper;
import com.lunz.util.GlobalFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunz.training.IRpcDemoService;
import com.lunz.training.bean.Demo;
import com.lunz.training.convert.DemoConvertMappers;
import com.lunz.training.dto.DemoDTO;
import com.lunz.training.service.DemoService;

/**
 * 演示接口
 *
 * @author haha
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

    @Autowired
    private IRpcDemoService rpcDemoService;

    @Override
    public String testRpc() {
        return rpcDemoService.test();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Demo saveDemo(DemoDTO demoDTO) {
        Demo demo = DemoConvertMappers.MAPPER.convertBean(demoDTO);
        GlobalFieldValidator.checkNotExist("名称", demo, "name");
        save(demo);
        return demo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyDemo(DemoDTO demoDTO) {
        Demo demo = DemoConvertMappers.MAPPER.convertBean(demoDTO);
        GlobalFieldValidator.checkNotExist("名称", demo, "id", "name");
        updateById(demo);
    }
}
