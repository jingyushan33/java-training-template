package com.lunz.training.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lunz.query.MpAll;
import com.lunz.query.MpPager;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import com.lunz.training.bean.Demo;
import com.lunz.training.dto.DemoDTO;
import com.lunz.training.vo.DemoVO;

import java.util.List;


/**
 * 实体映射工具
 * @author haha
 */
@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DemoConvertMappers {

    DemoConvertMappers MAPPER = Mappers.getMapper(DemoConvertMappers.class);

    Demo convertBean(DemoDTO demoDTO);

    List<DemoVO> convertList(List<Demo> demoList);

    DemoVO convertVO(Demo demo);

    MpAll<Demo> convertAll(MpAll<DemoDTO> mpAll);

    MpPager<Demo> convertPage(MpPager<DemoDTO> mpPager);

    Page<DemoVO> convertPageList(Page<Demo> page);
}
