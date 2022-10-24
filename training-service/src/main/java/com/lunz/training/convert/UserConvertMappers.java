package com.lunz.training.convert;

import com.lunz.training.dos.FindUserDO;
import com.lunz.training.dos.FindUserOutputDO;
import com.lunz.training.dos.UserDO;
import com.lunz.training.dto.FindUserDTO;
import com.lunz.training.dto.UserDTO;
import com.lunz.training.vo.FindUserVO;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author liangbing
 * @description 用户实体映射工具
 * @date 2022/8/29
 */
@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserConvertMappers {

    UserConvertMappers MAPPER = Mappers.getMapper(UserConvertMappers.class);

    /**
     * convert UserDTO to UserDO
     * @param userDTO
     * @return
     */
    UserDO convert2UserDO(UserDTO userDTO);

    FindUserDO convert2FindUserDO(FindUserDTO findUserDTO);

    FindUserVO convert2FindUserVO(FindUserOutputDO findUserOutputDO);
}

