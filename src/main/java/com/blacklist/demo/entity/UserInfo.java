package com.blacklist.demo.entity;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;

    private Long userId;

    private String userName;

    private Integer blackStatus;

    private Long createTime;

    private Long updateTime;

    //生效时间
    private Long effectTime;

    //失效时间
    private Long expiredTime;


}