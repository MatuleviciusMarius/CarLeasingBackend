package com.marius.leasing.services;

import com.marius.leasing.models.Car;
import com.marius.leasing.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Transactional
    public ResponseEntity<Object> addVehicle(Car car) {
        Car newCar = new Car();
        newCar.setFuelType(car.getFuelType());
        newCar.setMake(car.getMake());
        newCar.setUsed(car.isUsed());

        carRepository.save(car);
        if (carRepository.findById(car.getId()).isPresent()) {
            return ResponseEntity.accepted().body(car);
        } else {
            return ResponseEntity.unprocessableEntity().body("Failed to create a new vehicle");
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteVehicleById(Long id) {
        Optional<Car> vehicle = carRepository.findById(id);
        if (vehicle.isPresent()) {
            carRepository.deleteById(id);
            return ResponseEntity.ok().body("vehicle deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<Object> updateVehicle(Car newCar) {
        Optional<Car> vehicle = carRepository.findById(newCar.getId());
        if (vehicle.isPresent()) {
            carRepository.save(newCar);
            return ResponseEntity.ok().body(newCar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<Car> getVehicleById(Long id) {
        Optional<Car> vehicle = carRepository.findById(id);
        return vehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
