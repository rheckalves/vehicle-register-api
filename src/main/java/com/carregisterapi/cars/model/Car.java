package com.carregisterapi.cars.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EntityListeners(EntityListeners.class)
@EqualsAndHashCode(of = "id")
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @NotNull(message = "O campo plate não pode ser nulo!")
    @NotBlank(message = "O campo plate não pode estar em branco!")
    @NotEmpty(message = "O campo plate não pode ser vazio!")
    @Column
    @Getter
    @Setter
    private String plate;

    @NotNull(message = "O campo model não pode ser nulo!")
    @NotBlank(message = "O campo model não pode estar em branco!")
    @NotEmpty(message = "O campo model não pode ser vazio!")
    @Column
    @Getter
    @Setter
    private String model;

    @NotNull(message = "O color plate não pode ser nulo!")
    @NotBlank(message = "O color plate não pode estar em branco!")
    @NotEmpty(message = "O color plate não pode ser vazio!")
    @Column
    @Getter
    @Setter
    private String color;

    @NotNull(message = "O owner plate não pode ser nulo!")
    @NotBlank(message = "O owner plate não pode estar em branco!")
    @NotEmpty(message = "O owner plate não pode ser vazio!")
    @Column
    @Getter
    @Setter
    private String owner;
}
