package com.carregisterapi.cars.controller;
import com.carregisterapi.HTTPResponse.ResponseMessage;
import com.carregisterapi.cars.model.Car;
import com.carregisterapi.cars.model.CarDTO;
import com.carregisterapi.cars.model.CarSummaryDTO;
import com.carregisterapi.cars.repository.CarRepository;
import lombok.AllArgsConstructor;
import com.carregisterapi.config.ModelMapperConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CarsController {

    private final CarRepository carRepository;
    private final ModelMapperConfig mapper;

    @GetMapping("/cars")
    public List<CarDTO> allCars() {
        return this.carRepository.findAll()
                .stream()
                .map(this::parseCarToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/cars/{plate}")
    public CarSummaryDTO findCarPlate(@PathVariable("plate") String plate) {
        Car car = this.carRepository.findByPlate(plate.toUpperCase());
        return parseCarToSummaryDTO(car);
    }

    @PostMapping("/cars")
    public ResponseEntity<Optional<Car>> createCar(@Valid @RequestBody Car car) {
        car.setPlate(car.getPlate().toUpperCase());
        car.setModel(car.getModel().toUpperCase());
        car.setColor(car.getColor().toUpperCase());
        car.setOwner(car.getOwner().toUpperCase());
        Optional<Car> carOptional = Optional.of(
                this.carRepository.save(car));
        return ResponseEntity.status(HttpStatus.CREATED).body(carOptional);
    }

    @DeleteMapping("/cars/{plate}")
    public ResponseEntity<ResponseMessage> deleteCar(@PathVariable("plate") String plate) {
        Car car = this.carRepository.findByPlate(plate.toUpperCase());
        ResponseMessage response = new ResponseMessage();
        response.setMessage("Veículo de placas " + plate + " excluído com sucesso!");
        this.carRepository.delete(car);
        return ResponseEntity.status(200).body(response);
    }

    private CarDTO parseCarToDTO(Car car) {
        return this.mapper.modelMapper().map(car, CarDTO.class);
    }
    private CarSummaryDTO parseCarToSummaryDTO(Car car) {
        return this.mapper.modelMapper().map(car, CarSummaryDTO.class);
    }
}
