package xyz.teamgravity.people.dao.mock;

import org.springframework.stereotype.Repository;
import xyz.teamgravity.people.dao.PersonDao;
import xyz.teamgravity.people.model.PersonModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mock")
public class MockPersonDao implements PersonDao {

    private static List<PersonModel> MOCK_PERSON = new ArrayList<>();

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
        return MOCK_PERSON
                .stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }
}
