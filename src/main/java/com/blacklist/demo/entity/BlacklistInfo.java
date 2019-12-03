package com.blacklist.demo.entity;

import lombok.Data;

@Data
public class BlacklistInfo {

    private Long id;

    private Long projectId;

    private String userId;

    private String userName;

    private Integer blackStatus;

    private Long effectTime;

    private Long expiredTime;

    private Long createTime;

    private Long updateTime;

}