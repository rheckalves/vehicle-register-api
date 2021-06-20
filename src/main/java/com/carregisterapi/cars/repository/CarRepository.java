package com.carregisterapi.cars.repository;
import com.carregisterapi.cars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CarRepository extends JpaRepository<Car, Long> {

    public Car findByPlate(String plate);
}