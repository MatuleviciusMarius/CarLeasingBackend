package com.marius.leasing.services;

import com.marius.leasing.models.Car;
import com.marius.leasing.models.FamilyMember;
import com.marius.leasing.models.Person;
import com.marius.leasing.repositories.FamilyMemberRepository;
import com.marius.leasing.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;
    @Mock
    FamilyMemberRepository familyMemberRepository;
    @InjectMocks
    PersonService personService;

    @Test
    void shouldSuccessfullyAddANewPerson() {
        Person person = new Person();
        person.setId(1L);
        ResponseEntity<Object> expectedResult = ResponseEntity.accepted().body(person);
        when(personRepository.findById(any())).thenReturn(Optional.of(person));

        ResponseEntity<Object> actualResult = personService.addPerson(person);
        assertEquals(expectedResult.getStatusCode(), actualResult.getStatusCode());
    }

    @Test
    void shouldSuccessfullyUpdatePerson() {
        Person person = new Person();
        person.setId(1L);
        Person updatedPerson = new Person();
        updatedPerson.setId(1L);
        updatedPerson.setName("Updated name");
        ResponseEntity<Object> expectedResult = ResponseEntity.ok().body(updatedPerson);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        ResponseEntity<Object> actualResult = personService.updatePerson(updatedPerson);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingPersonThatDoesNotExist() {
        Person person = new Person();
        person.setId(1L);
        ResponseEntity<Object> expectedResult = ResponseEntity.notFound().build();
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Object> actualResult = personService.updatePerson(person);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldDeleteAPersonByGivenId() {
        Person person = new Person();
        person.setId(1L);
        ResponseEntity<Object> expectedResult = ResponseEntity.ok().body("Person deleted successfully");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        ResponseEntity<Object> actualResult = personService.deletePersonById(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnNotFoundWhenDeletingPersonThatDoesNotExist() {
        ResponseEntity<Object> expectedResult = ResponseEntity.notFound().build();
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Object> actualResult = personService.deletePersonById(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGetPersonById() {
        Person person = new Person();
        person.setId(1L);
        ResponseEntity<Person> expectedResult = ResponseEntity.ok(person);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        ResponseEntity<Person> actualResult = personService.getPersonById(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addFamilyMember() {
        Person person = new Person();
        person.setId(1L);
        FamilyMember familyMember = new FamilyMember();
        ResponseEntity<Object> expectedResult = ResponseEntity.ok().body("Family member added successfully");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        ResponseEntity<Object> actualResult = personService.addFamilyMember(1L, familyMember);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnNotFoundWhenPersonDoesNotExistWhenAddingFamilyMember() {
        FamilyMember familyMember = new FamilyMember();
        ResponseEntity<Object> expectedResult = ResponseEntity.status(404).body("Person does not exist");
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Object> actualResult = personService.addFamilyMember(1L, familyMember);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGetAllFamilyMembersByPersonId() {
        HashSet<FamilyMember> familyMembers = new HashSet<>();
        familyMembers.add(new FamilyMember());
        ResponseEntity<Set<FamilyMember>> expectedResult = ResponseEntity.accepted().body(familyMembers);

        when(familyMemberRepository.findByPersonId(1L)).thenReturn(familyMembers);

        ResponseEntity<Set<FamilyMember>> actualResult = personService.getFamilyMembersByPersonID(1L);

        assertEquals(expectedResult, actualResult);
    }
}