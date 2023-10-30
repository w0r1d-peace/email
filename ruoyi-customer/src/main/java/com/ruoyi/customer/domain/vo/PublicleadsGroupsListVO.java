package com.ruoyi.customer.domain.vo;

import com.ruoyi.system.domain.vo.UserInfoVO;
import lombok.Data;

import java.util.List;

@Data
public class PublicleadsGroupsListVO {

    private Long id;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组成员信息
     */
    private List<UserInfoVO> userInfoList;

    /**
     * 是否为默认分组
     */
    private Boolean defaultGroupFlag;
}
