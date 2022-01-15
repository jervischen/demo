package com.example.mock;

/**
 * @author Chen Xiao
 * @since 2021-05-23 13:51
 */
public class PersonService {
    private final PersonDao personDao;
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }
    public boolean update(int id, String name) {
        Person person = personDao.getPerson(id);
        if (person == null)
        { return false; }
        Person personUpdate = new Person(person.getId(), name);
        return personDao.update(personUpdate);
    }
}
