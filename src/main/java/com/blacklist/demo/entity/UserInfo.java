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


}