package com.lunz.training.convert;

import com.lunz.training.dos.QuoteListQueryDO;
import com.lunz.training.dto.QuoteListDTO;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface QuoteConvertMappers {

    QuoteConvertMappers MAPPER = Mappers.getMapper(QuoteConvertMappers.class);

    /**
     * convert QuoteListDTO to QuoteListQueryDO
     * @param quoteListDTO
     * @return
     */
    @Mapping(target = "orderType", source = "businessType")
    QuoteListQueryDO convert2QuoteListQueryDO(QuoteListDTO quoteListDTO);

}
