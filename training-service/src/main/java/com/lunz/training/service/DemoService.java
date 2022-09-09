package com.lunz.training.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lunz.training.bean.Demo;
import com.lunz.training.dto.DemoDTO;

/**
 * 演示服务
 *
 * @author haha
 */
public interface DemoService extends IService<Demo> {


    String testRpc();

    Demo saveDemo(DemoDTO demoDTO);

    void modifyDemo(DemoDTO demoDTO);
}
