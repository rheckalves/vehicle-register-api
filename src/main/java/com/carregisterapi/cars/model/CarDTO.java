package com.carregisterapi.cars.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {
    private Long id;
    private String plate;
    private String model;
    private String color;
}
