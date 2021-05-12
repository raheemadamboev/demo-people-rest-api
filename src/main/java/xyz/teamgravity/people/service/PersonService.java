package xyz.teamgravity.people.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xyz.teamgravity.people.dao.PersonDao;
import xyz.teamgravity.people.model.PersonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao dao;

    public PersonService(@Qualifier("mock") PersonDao dao) {
        this.dao = dao;
    }

    public int insertPerson(PersonModel person) {
        return dao.insertPerson(person);
    }

    public List<PersonModel> selectAllPeople() {
        return dao.selectAllPeople();
    }

    public Optional<PersonModel> selectPerson(UUID id) {
        return dao.selectPerson(id);
    }
}
