package com.lunz.training.dos;

import lombok.Data;

@Data
public class QuoteListQueryDO {

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 业务类型
     */
    private String orderType;

}
