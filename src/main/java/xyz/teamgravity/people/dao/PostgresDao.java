package xyz.teamgravity.people.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.teamgravity.people.exception.PersonNotExistException;
import xyz.teamgravity.people.model.PersonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Repository("postgres")
public class PostgresDao implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public PostgresDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, PersonModel person) {
        return jdbcTemplate.update(
                "INSERT INTO person (id, name) VALUES (?, ?)",
                id,
                person.getName()
        );
    }

    @Override
    public List<PersonModel> selectAllPeople() {
        return jdbcTemplate.query("SELECT id, name FROM person", (resultSet, i) -> new PersonModel(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name")));
    }

    @Override
    public Optional<PersonModel> selectPerson(UUID id) {
        try {
            Optional<PersonModel> person = Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT id, name FROM person WHERE id = ?",
                    (resultSet, i) -> new PersonModel(
                            UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("name")),
                    id
            ));

            if (person.isEmpty()) {
                throw new PersonNotExistException("Person not found with the id: " + id.toString());
            } else {
                return person;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new PersonNotExistException("Person not found with the id: " + id.toString());
        }
    }

    @Override
    public int deletePerson(UUID id) {
        // check if with this id there is row if not throw exception
        return jdbcTemplate.update(
                "DELETE FROM person WHERE id = ?",
                selectPerson(id).get().getId()
        );
    }

    @Override
    public int updatePerson(PersonModel person) {
        // check if with this id there is row if not throw exception
        return jdbcTemplate.update(
                "UPDATE person SET name = ? WHERE id = ?",
                person.getName(),
                selectPerson(person.getId()).get().getId()
        );
    }
}
