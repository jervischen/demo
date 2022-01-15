package com.example.modelmapper.model;

import lombok.Data;

/**
 * @author Chen Xiao
 * @since 2020-10-09 14:36
 */
@Data
public class Customer {
//    Name name = new Name();
    String firstName = "chenxiao";

    private String bankName;

    private String branchBankName;

}
