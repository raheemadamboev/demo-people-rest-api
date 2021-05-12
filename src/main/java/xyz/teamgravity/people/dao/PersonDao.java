package xyz.teamgravity.people.dao;

import xyz.teamgravity.people.model.PersonModel;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, PersonModel person);

    default int insertPerson(PersonModel person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<PersonModel> selectAllPeople();

    Optional<PersonModel> selectPerson(UUID id);

    int deletePerson(UUID id);

    int updatePerson(UUID id, PersonModel person);
}
