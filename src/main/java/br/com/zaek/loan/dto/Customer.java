package br.com.zaek.loan.dto;

public class Customer {
    private final String name;
    private final String cpf;
    private final Integer age;
    private final String location;
    private final Integer income;

    private Customer(String name, String cpf, Integer age, String location, Integer income) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.location = location;
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public Integer getIncome() {
        return income;
    }

    public static class CustomerBuilder {
        private String name;
        private String cpf;
        private Integer age;
        private String location;
        private Integer income;

        public CustomerBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder withCPF(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public CustomerBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public CustomerBuilder withLocation(String location) {
            this.location = location;
            return this;
        }

        public CustomerBuilder withIncome(Integer income) {
            this.income = income;
            return this;
        }

        public Customer build() {
            return new Customer(this.name, this.cpf, this.age, this.location, this.income);
        }


    }
}
