package com.marius.leasing.repositories;

import com.marius.leasing.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
