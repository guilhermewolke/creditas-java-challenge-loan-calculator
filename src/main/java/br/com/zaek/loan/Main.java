package br.com.zaek.loan;

import br.com.zaek.loan.core.LoanCalculator;
import br.com.zaek.loan.dto.Customer;
import br.com.zaek.loan.dto.Output;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do cliente:");
        String nome = scanner.next();

        System.out.println("Informe o CPF do cliente:");
        String cpf = scanner.next();

        System.out.println("Informe a Idade do cliente:");
        Integer idade = scanner.nextInt();

        System.out.println("Informe a Localização do cliente:");
        String localizacao = scanner.next();

        System.out.println("Informe o Salário do cliente:");
        Integer salario = scanner.nextInt();

        Customer cliente = new Customer.CustomerBuilder()
                .withName(nome)
                .withCPF(cpf)
                .withAge(idade)
                .withLocation(localizacao)
                .withIncome(salario).build();

        Output output = new LoanCalculator(cliente).calculate();

        System.out.println("Retorno: " + output);
    }
}