package com.marius.leasing.services;

import com.marius.leasing.models.Car;
import com.marius.leasing.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @Test
    void shouldSuccessfullyAddACar() {
        Car car = new Car();
        ResponseEntity<Object> expectedResult = ResponseEntity.accepted().body(car);
        when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(car));

        ResponseEntity<Object> actualResult = carService.addVehicle(car);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldDeleteVehicleByGivenId() {
        Car car = new Car();
        ResponseEntity<Object> expectedResult = ResponseEntity.ok().body("vehicle deleted successfully");
        when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(car));

        ResponseEntity<Object> actualResult = carService.deleteVehicleById(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnNotFoundWhenDeletingACarThatDoesNotExist(){
        ResponseEntity<Object> expectedResult = ResponseEntity.notFound().build();
        when(carRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        ResponseEntity<Object> actualResult = carService.deleteVehicleById(1L);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldUpdateVehicle() {
        Car car = new Car();
        car.setId(1L);
        Car updatedCar = new Car();
        updatedCar.setId(1L);
        updatedCar.setMake("Updated make");
        ResponseEntity<Object> expectedResult = ResponseEntity.ok().body(updatedCar);
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        ResponseEntity<Object> actualResult = carService.updateVehicle(updatedCar);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingAVehicleThatDoesNotExist(){
        Car updatedCar = new Car();
        updatedCar.setId(1L);
        updatedCar.setMake("Updated make");
        ResponseEntity<Object> expectedResult = ResponseEntity.notFound().build();
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Object> actualResult = carService.updateVehicle(updatedCar);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldGetVehicleByGivenID() {
        Car car = new Car();
        car.setId(1L);
        ResponseEntity<Car> expectedResult = ResponseEntity.ok().body(car);
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        ResponseEntity<Car> actualResult = carService.getVehicleById(1L);

        assertEquals(expectedResult, actualResult);
    }
}