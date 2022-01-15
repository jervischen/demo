package com.example.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Chen Xiao
 * @since 2020-10-16 15:43
 */
@Getter
@AllArgsConstructor
public enum EnumDemo {

    RANK("普通排行榜"),

    GROUP_PK("分组pk"),

    ;

    private String msg;
}
