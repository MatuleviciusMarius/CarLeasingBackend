package com.marius.leasing.services;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class LeasingServiceTest {

/*    @Mock
    CreditApplicationRepository creditApplicationRepository;
    @Mock
    PersonRepository personRepository;
    @Mock
    CarRepository carRepository;

    @InjectMocks
    LeasingService carLeasingService;

    @Test
    void shouldSuccessfullyAddAPersonToTheDatabase() {
        ResponseEntity<Object> expectedResponse = ResponseEntity.accepted().body("Successfully created a new person");
        Person testPerson = new Person();
        testPerson.setId(1L);
        testPerson.setName("Marius");
        testPerson.setLastName("Matulevicius");
        testPerson.setIncome(BigDecimal.valueOf(1000));
        testPerson.setEmail("Email");
        when(personRepository.save(any(Person.class))).thenReturn(testPerson);
        when(personRepository.findById(1L)).thenReturn(Optional.of(testPerson));

        ResponseEntity<Object> actualResponse = carLeasingService.addPerson(testPerson);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldSuccessfullyAddAVehicleToTheDatabase() {
        ResponseEntity<Object> expectedResponse = ResponseEntity.accepted().body("Successfully Created a new vehicle");
        Vehicle testVehicle = new Vehicle();
        testVehicle.setId(1L);

        when(carRepository.save(any(Vehicle.class))).thenReturn(testVehicle);
        when(carRepository.findById(1L)).thenReturn(Optional.of(testVehicle));

        ResponseEntity<Object> actualResponse = carLeasingService.addVehicle(testVehicle);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldSuccessfullyCreateANewLeasingApplication() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        Person person = new Person();
        person.setId(1L);
        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setId(1L);

        when(carRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(creditApplicationRepository.save(any(CreditApplication.class))).thenReturn(null);
        when(creditApplicationRepository.findById(any())).thenReturn(Optional.of(creditApplication));

        ResponseEntity<Object> expectedResult = ResponseEntity.accepted().body(Optional.of(creditApplication));

        ResponseEntity<Object> actualResult = carLeasingService.createNewLeasingApplication(creditApplication, 1L, 1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldSuccessfullyUpdateAPerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Marius");
        person.setLastName("Matulevicius");
        person.setIncome(BigDecimal.valueOf(1000));
        person.setEmail("Email");
        Person updatedPerson = new Person();
        updatedPerson.setId(1L);
        updatedPerson.setName("Marius");
        ResponseEntity<Object> expectedResponse = ResponseEntity.ok().body(updatedPerson);

        when(personRepository.save(person)).thenReturn(null);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        carLeasingService.addPerson(person);

        ResponseEntity<Object> actualResponse = carLeasingService.updatePerson(updatedPerson);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingAPersonWhichDoesNotExist() {
        ResponseEntity<Object> expectedResponse = ResponseEntity.notFound().build();
        Person testPerson = new Person();
        testPerson.setId(1L);

        when(personRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> actualResponse = carLeasingService.updatePerson(testPerson);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldSuccessfullyDeleteAPersonFromDatabase() {
        ResponseEntity<Object> expectedResponse = ResponseEntity.ok().body("Person deleted successfully");
        Person testPerson = new Person();
        testPerson.setId(1L);
        testPerson.setName("Marius");
        testPerson.setLastName("Matulevicius");
        testPerson.setIncome(BigDecimal.valueOf(1000));
        testPerson.setEmail("Email");

        when(personRepository.findById(1L)).thenReturn(Optional.of(testPerson));

        ResponseEntity<Object> actualResponse = carLeasingService.deletePersonById(testPerson.getId());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldReturnNotFoundWhenDeletingAPersonFromDatabaseWhichDoesNotExist() {
        ResponseEntity<Object> expectedResponse = ResponseEntity.notFound().build();

        when(personRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> actualResponse = carLeasingService.deletePersonById(1L);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldSuccessfullyAddAVehicle() {
        ResponseEntity<Object> expectedResult = ResponseEntity.accepted().body("Successfully Created a new vehicle");
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);

        when(carRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        ResponseEntity<Object> actualResult = carLeasingService.addVehicle(vehicle);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldSuccessfullyDeleteVehicleById() {
        ResponseEntity<Object> expectedResult = ResponseEntity.ok().body("vehicle deleted successfully");
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        carLeasingService.addVehicle(vehicle);
        ResponseEntity<Object> actualResult = carLeasingService.deleteVehicleById(vehicle.getId());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldFailToDeleteVehicleWhenItDoesNotExist() {
        ResponseEntity<Object> expectedResult = ResponseEntity.notFound().build();
        ResponseEntity<Object> actualResult = carLeasingService.deleteVehicleById(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldSuccessfullyGetVehicleById() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setUsed(true);
        vehicle.setMake("make");
        vehicle.setFuelType("fuel Type");
        ResponseEntity<Vehicle> expectedResult = ResponseEntity.ok().body(vehicle);
        carLeasingService.addVehicle(vehicle);
        ResponseEntity<Vehicle> actualResult = carLeasingService.getVehicleById(vehicle.getId());

        assertEquals(expectedResult.getBody().getId(), actualResult.getBody().getId());
    }

    @Test
    void shouldReturnNotFoundWhenCantFindVehicleById() {
        ResponseEntity<Vehicle> expectedResult = ResponseEntity.notFound().build();
        ResponseEntity<Vehicle> actualResult = carLeasingService.getVehicleById(1L);

        assertEquals(expectedResult, actualResult);

    }*/
}