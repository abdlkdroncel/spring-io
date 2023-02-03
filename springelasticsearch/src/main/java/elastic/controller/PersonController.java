package elastic.controller;

import elastic.entity.Person;
import elastic.repository.PersonRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepo personRepo;

    @PostConstruct
    public void init(){
        Person p=new Person();
        p.setAddress("tr");
        p.setBirthDate(LocalDate.now());
        p.setName("kadir");
        p.setLastname("oncel");
        personRepo.save(p);

        Person p1=new Person();
        p1.setAddress("tr");
        p1.setBirthDate(LocalDate.now());
        p1.setName("tugba");
        p1.setLastname("harman");
        personRepo.save(p1);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<Person>> getPerson(@PathVariable String search){
        List<Person> persons=personRepo.getByQuery(search);
        return ResponseEntity.ok(persons);
    }
}
