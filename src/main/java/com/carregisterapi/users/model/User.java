package com.carregisterapi.users.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EntityListeners(EntityListeners.class)
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @NotNull(message = "O campo name não pode ser nulo!")
    @NotBlank(message = "O campo name não pode estar em branco!")
    @NotEmpty(message = "O campo name não pode ser vazio!")
    @Column
    @Getter
    @Setter
    private String name;

    @NotNull(message = "O campo email não pode ser nulo!")
    @NotBlank(message = "O campo email não pode estar em branco!")
    @NotEmpty(message = "O campo email não pode ser vazio!")
    @Email
    @Column
    @Getter
    @Setter
    private String email;

    @NotNull(message = "O campo password não pode ser nulo!")
    @NotBlank(message = "O campo password  não pode estar em branco!")
    @NotEmpty(message = "O campo password  não pode ser vazio!")
    @Column
    @Getter
    @Setter
    private String password;

    @Column
    @Getter
    @Setter
    private Boolean isAdmin;
}
