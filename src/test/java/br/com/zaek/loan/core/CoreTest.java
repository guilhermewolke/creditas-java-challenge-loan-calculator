package br.com.zaek.loan.core;

import br.com.zaek.loan.dto.Customer;

public class CoreTest {

    protected Customer getACustomer(String name, String cpf, Integer age, String location, Integer income) {
        return new Customer.CustomerBuilder()
                .withName(name)
                .withCPF(cpf)
                .withAge(age)
                .withLocation(location)
                .withIncome(income).build();
    }
}
