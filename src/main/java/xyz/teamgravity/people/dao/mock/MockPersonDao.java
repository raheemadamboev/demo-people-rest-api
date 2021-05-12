package xyz.teamgravity.people.dao.mock;

import org.springframework.stereotype.Repository;
import xyz.teamgravity.people.dao.PersonDao;
import xyz.teamgravity.people.exception.PersonNotExistException;
import xyz.teamgravity.people.model.PersonModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mock")
public class MockPersonDao implements PersonDao {

    private static final List<PersonModel> MOCK_PERSON = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, PersonModel person) {
        MOCK_PERSON.add(new PersonModel(id, person.getName()));
        return 1;
    }

    @Override
    public List<PersonModel> selectAllPeople() {
        return MOCK_PERSON;
    }

    @Override
    public Optional<PersonModel> selectPerson(UUID id) {
        Optional<PersonModel> person = MOCK_PERSON
                .stream()
                .filter(filteredPerson -> filteredPerson.getId().equals(id))
                .findFirst();

        if (person.isEmpty()) throw new PersonNotExistException("Person not found with the id: " + id.toString());

        return person;
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<PersonModel> person = selectPerson(id);
        if (person.isEmpty()) {
            return 0;
        } else {
            MOCK_PERSON.remove(person.get());
            return 1;
        }
    }

    @Override
    public int updatePerson(PersonModel person) {
        Optional<PersonModel> dbPerson = selectPerson(person.getId());

        if (dbPerson.isEmpty()) {
            return 0;
        } else {
            int index = MOCK_PERSON.indexOf(dbPerson.get());

            if (index == -1) {
                return 0;
            } else {
                MOCK_PERSON.set(index, person);
            }
            return 1;
        }
    }
}
