package com.marius.leasing.controllers;

import com.marius.leasing.models.FamilyMember;
import com.marius.leasing.models.Person;
import com.marius.leasing.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("create")
    public ResponseEntity<Object> createPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Object> deletePerson(@RequestParam Long id) {
        return personService.deletePersonById(id);
    }

    @GetMapping("get")
    public ResponseEntity<Person> getPerson(@RequestParam Long id) {
        return personService.getPersonById(id);
    }

    @PutMapping("addFamilyMember")
    public ResponseEntity<Object> addFamilyMember(@RequestParam(name = "pId") Long personId, @RequestBody FamilyMember familyMember){
        return personService.addFamilyMember(personId, familyMember);
    }
}
