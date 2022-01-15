package com.example.modelmapper.model;

import lombok.Data;

/**
 * @author Chen Xiao
 * @since 2020-10-09 14:39
 */
@Data
public class Order {
    Customer customer = new Customer();
    Address billingAddress = new Address();
}
