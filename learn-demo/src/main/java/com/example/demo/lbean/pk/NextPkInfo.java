package com.example.demo.lbean.pk;

import lombok.Data;

/**
 * Created in 2018-04-12 16:44.
 *
 * @author chenxiao
 */
@Data
public class NextPkInfo {

    private long userId;
    private String band;
    private String userName;

    private long radioId;
    private String headPortrait;

    private long beginTime;

    private long groupId;
}
