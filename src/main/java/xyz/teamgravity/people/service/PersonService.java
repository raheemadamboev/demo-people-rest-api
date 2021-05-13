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

    public PersonService(@Qualifier("postgres") PersonDao dao) {
        this.dao = dao;
    }

    public int insertPerson(PersonModel person) {
        if (person.getId() == null) {
            return dao.insertPerson(person);
        } else {
            return dao.insertPerson(person.getId(), person);
        }
    }

    public List<PersonModel> selectAllPeople() {
        return dao.selectAllPeople();
    }

    public Optional<PersonModel> selectPerson(UUID id) {
        return dao.selectPerson(id);
    }

    public int deletePerson(UUID id) {
        return dao.deletePerson(id);
    }

    public int updatePerson(PersonModel person) {
        return dao.updatePerson(person);
    }
}
