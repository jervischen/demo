package com.example.modelmapper;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Chen Xiao
 * @since 2020-10-09 14:35
 */
@Data
@Accessors(chain = true)
public class OrderDTO {
    String firstName;
    String customerLastName;
    String billingStreet;
    String billingCity;

    int score;
    int count;
}
