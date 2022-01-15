package com.example.spi;

import java.util.List;

/**
 * @author Chen Xiao
 * @since 2021-04-25 15:45
 */
public class FileSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索 "+keyword);
        return null;
    }
}