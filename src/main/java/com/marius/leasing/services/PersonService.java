package com.marius.leasing.services;

import com.marius.leasing.models.FamilyMember;
import com.marius.leasing.models.Person;
import com.marius.leasing.repositories.FamilyMemberRepository;
import com.marius.leasing.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Transactional
    public ResponseEntity<Object> addPerson(Person person) {
        Person newPerson = new Person();
        newPerson.setIncome(person.getIncome());
        newPerson.setEmail(person.getEmail());
        newPerson.setLastName(person.getLastName());
        newPerson.setName(person.getName());
        personRepository.save(person);

        if (personRepository.findById(newPerson.getId()).isPresent()) {
            return ResponseEntity.accepted().body(newPerson);
        } else {
            return ResponseEntity.unprocessableEntity().body("Failed to create a new person");
        }
    }

    @Transactional
    public ResponseEntity<Object> updatePerson(Person newPerson) {
        Optional<Person> person = personRepository.findById(newPerson.getId());
        if (person.isPresent()) {
            personRepository.save(newPerson);
            return ResponseEntity.ok().body(newPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<Object> deletePersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.ok().body("Person deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<Person> getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Transactional
    public ResponseEntity<Object> addFamilyMember(Long personId, FamilyMember familyMember) {
        Optional<Person> person = personRepository.findById(personId);

        if (person.isPresent()) {
            familyMember.setPerson(person.get());
            familyMemberRepository.save(familyMember);

            return ResponseEntity.ok().body("Family member added successfully");
        } else {
            return ResponseEntity.status(404).body("Person does not exist");
        }
    }

    @Transactional
    public ResponseEntity<Set<FamilyMember>> getFamilyMembersByPersonID(Long id) {
        return ResponseEntity.accepted().body(familyMemberRepository.findByPersonId(id));
    }
}
