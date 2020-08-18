package com.marius.leasing.controllers;

import com.marius.leasing.models.Car;
import com.marius.leasing.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/car")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("create")
    public ResponseEntity<Object> createVehicle(@RequestBody Car car) {
        return carService.addVehicle(car);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateVehicle(@RequestBody Car car){
        return carService.updateVehicle(car);
    }

    @GetMapping("get")
    public ResponseEntity<Car> getVehicle(@RequestParam Long id){
        return carService.getVehicleById(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Object> deleteVehicle(@RequestParam Long id){
        return carService.deleteVehicleById(id);
    }
}
