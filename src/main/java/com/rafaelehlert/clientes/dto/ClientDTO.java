package com.rafaelehlert.clientes.dto;

import java.time.LocalDate;

import com.rafaelehlert.clientes.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ClientDTO {

    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter no minimo 3 e no maximo 80 caracteres")
    @NotBlank
    private String name;
    @Size(max = 11, message = "Digite somente os numeros do seu CPF ")
    @NotBlank
    private String cpf;
    @Positive(message = "O salario precisa ser positivo")
    private Double income;
    @PastOrPresent(message = "Nao pode ser data futura")
    @NotNull
    private LocalDate birthDate;
    @PositiveOrZero(message = "Nao pode sere menor que zero")
    @NotNull
    private Integer children;

    
    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity){
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getCpf() {
        return cpf;
    }


    public Double getIncome() {
        return income;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public Integer getChildren() {
        return children;
    }

}
