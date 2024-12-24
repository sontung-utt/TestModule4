package com.codegym.testmodule4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Trường tên không được để trống")
    private String name;
    @NotBlank(message = "Trường số điện thoại không được để trống")
    private String phone;
    @NotBlank(message = "Trường email không được để trống")
    private String email;

}
