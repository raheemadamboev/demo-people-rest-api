package xyz.teamgravity.people.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.teamgravity.people.model.PersonModel;
import xyz.teamgravity.people.service.PersonService;

import java.util.List;

@RequestMapping("api/person")
@RestController
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int insertPerson(@RequestBody PersonModel person) {
        System.out.print("Shit " + person.getName());
        return service.insertPerson(person);
    }

    @GetMapping
    public List<PersonModel> selectAllPeople() {
        return service.selectAllPeople();
    }
}
