package com.ruoyi.customer.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class EmailPacketListVO {

    private Long id;

    private String name;

    private Integer customerCount;

    private Integer emailCount;

    private List<EmailCustomerVO> customerList;
}
