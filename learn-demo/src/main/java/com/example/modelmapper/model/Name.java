package com.example.modelmapper.model;

import lombok.Data;

/**
 * @author Chen Xiao
 * @since 2020-10-09 14:36
 */
@Data
public class Name {
    String firstName = "chen";
    String lastName = "xiao";

    private String bankName="444";
    private String branchBankName="1233";


    public String getA(){
        return "AA";
    }
    public String getB(){
        return "BB";
    }

}
