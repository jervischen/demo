package com.example.mock;

/**
 * @author Chen Xiao
 * @since 2021-05-23 13:51
 */
public interface PersonDao {
    Person getPerson(int id);
    boolean update(Person person);
}
