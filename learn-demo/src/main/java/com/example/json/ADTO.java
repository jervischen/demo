package com.example.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Chen Xiao
 * @since 2020-11-18 14:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ADTO {

    private String targetType = "TYPE";
    private List<Long> targetIds = new LinkedList<>();//抽象的目标类型的Id，比如道具ID，礼物ID
    private List<String> targetNames = new LinkedList<>();//抽象的目标类型的名字(方便运营后台)
}
