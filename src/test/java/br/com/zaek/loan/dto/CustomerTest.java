package br.com.zaek.loan.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    @DisplayName("Given Valid data for customer, when build a customer, then returns me a valid customer")
    public void shouldBuildACustomer() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "BH";
        final Integer income = 3000;

        Customer.CustomerBuilder builder = new Customer.CustomerBuilder();
        Customer customer = builder.withName(name)
                .withCPF(cpf)
                .withAge(age)
                .withLocation(location)
                .withIncome(income).build();

        Assertions.assertEquals(name, customer.getName());
        Assertions.assertEquals(cpf, customer.getCpf());
        Assertions.assertEquals(age, customer.getAge());
        Assertions.assertEquals(location, customer.getLocation());
        Assertions.assertEquals(income, customer.getIncome());
    }
}