package xyz.teamgravity.people.controller;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.teamgravity.people.model.PersonModel;
import xyz.teamgravity.people.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/person")
@RestController
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int insertPerson(@Valid @NonNull @RequestBody PersonModel person) {
        return service.insertPerson(person);
    }

    @GetMapping
    public List<PersonModel> selectAllPeople() {
        return service.selectAllPeople();
    }

    @GetMapping(path = "{id}")
    public PersonModel selectPerson(@PathVariable("id") UUID id) {
        return service.selectPerson(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int deletePerson(@PathVariable("id") UUID id) {
        return service.deletePerson(id);
    }

    @PutMapping
    public int updatePerson(@Valid @NonNull @RequestBody PersonModel person) {
        return service.updatePerson(person);
    }
}
