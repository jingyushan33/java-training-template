package com.lunz.training;

import com.lunz.training.vo.DemoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName: IDemoFeignService
 * @Description: 测试统一调用接口
 * @Author: puritykid
 * @Date: 2021/6/28 10:31
 * @Version: v1.0
 */
@FeignClient(name = "training-service")
@RequestMapping("/rpc/training")
public interface ITrainingApplicationFeignClient {

    @GetMapping("/{id}")
    DemoVO getUserInfoById(@PathVariable("id") @NotBlank(message = "id不能为空") String id);

    @GetMapping("/list")
    List<DemoVO> getAll();
}
