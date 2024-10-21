package br.com.zaek.loan.core;

import br.com.zaek.loan.dto.Customer;
import br.com.zaek.loan.dto.LoanTypes;
import br.com.zaek.loan.dto.Output;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanCalculatorTest extends CoreTest {

    @Test
    @DisplayName("Given a valid less than 3000 income Customer, When invoke LoanCalculator for this customer, then should return an output with allowed loan options")
    public void shouldEvaluateALess3000IncomeCustomerProfile() {

        // Cenário 1 - Avalia cliente com salário abaixo de 3000 que não mora em SP (deve retornar somente o tipo pessoal)
        String name = "Cliente Com salário abaixo de 3000 que não mora em SP";
        String cpf = "112.358.132-13";
        Integer age = 29;
        String location = "MG";
        Integer income = 2500;

        Customer customer1 = this.getACustomer(name, cpf, age, location, income);

        LoanCalculator calculator = new LoanCalculator(customer1);

        Output output = calculator.calculate();

        assertEquals(name, output.customer());
        assertEquals(1, output.loans().size());
        Assertions.assertEquals(LoanTypes.PERSONAL, output.loans().get(0).type());
        Assertions.assertEquals(4, output.loans().get(0).tax());

        // Cenário 2 - Avalia cliente com salário abaixo de 3000 que mora em SP, mas não tem menos de 30 anos (deve retornar somente o tipo pessoal)
        name = "Cliente Com salário abaixo de 3000 que mora em SP mas não tem menos de 30 anos";
        age = 31;
        location = "SP";

        Customer customer2 = this.getACustomer(name, cpf, age, location, income);

        calculator = new LoanCalculator(customer2);

        output = calculator.calculate();

        assertEquals(name, output.customer());
        assertEquals(1, output.loans().size());
        Assertions.assertEquals(LoanTypes.PERSONAL, output.loans().get(0).type());
        Assertions.assertEquals(4, output.loans().get(0).tax());

        // Cenário 3 - Avalia cliente com salário abaixo de 3000 que mora em SP, e tem menos de 30 anos (deve retornar os tipos pessoal e com garantia)
        name = "Cliente Com salário abaixo de 3000 que mora em SP mas não tem menos de 30 anos";
        age = 29;
        location = "SP";

        Customer customer3 = this.getACustomer(name, cpf, age, location, income);

        calculator = new LoanCalculator(customer3);

        output = calculator.calculate();

        assertEquals(name, output.customer());
        assertEquals(2, output.loans().size());
        Assertions.assertEquals(LoanTypes.PERSONAL, output.loans().get(0).type());
        Assertions.assertEquals(4, output.loans().get(0).tax());
        Assertions.assertEquals(LoanTypes.COLLATERALIZED, output.loans().get(1).type());
        Assertions.assertEquals(3, output.loans().get(1).tax());
    }

    @Test
    @DisplayName("Given a valid Customer with income between 3000 and 5000, When invoke LoanCalculator for this customer, then should return an output with allowed loan options")
    public void shouldEvaluateAValidCustomerWithIncomeBetween3000And5000Profile() {

        // Cenário 1 - Avalia cliente com salário entre 3000 e 5000 que não mora em SP (deve retornar somente o tipo pessoal)
        String name = "Cliente Com salário entre 3000 e 5000 que não mora em SP";
        String cpf = "112.358.132-13";
        Integer age = 29;
        String location = "MG";
        Integer income = 3000;

        Customer customer1 = this.getACustomer(name, cpf, age, location, income);

        LoanCalculator calculator = new LoanCalculator(customer1);

        Output output = calculator.calculate();

        assertEquals(name, output.customer());
        assertEquals(1, output.loans().size());
        Assertions.assertEquals(LoanTypes.PERSONAL, output.loans().get(0).type());
        Assertions.assertEquals(4, output.loans().get(0).tax());

        // Cenário 2 - Avalia cliente com salário entre 3000 e 5000 que mora em SP (deve retornar os tipos pessoal e com garantia)
        name = "Cliente Com salário entre 3000 e 5000 que mora em SP";
        age = 31;
        location = "SP";

        Customer customer2 = this.getACustomer(name, cpf, age, location, income);

        calculator = new LoanCalculator(customer2);

        output = calculator.calculate();

        assertEquals(name, output.customer());
        assertEquals(2, output.loans().size());
        Assertions.assertEquals(LoanTypes.PERSONAL, output.loans().get(0).type());
        Assertions.assertEquals(4, output.loans().get(0).tax());
        Assertions.assertEquals(LoanTypes.COLLATERALIZED, output.loans().get(1).type());
        Assertions.assertEquals(3, output.loans().get(1).tax());
    }

    @Test
    @DisplayName("Given a valid greater than 5000 income Customer, When invoke LoanCalculator for this customer, then should return an output with allowed loan options")
    public void shouldEvaluateAValidGreaterThan5000IncomeCustomerProfile() {

        // Cenário 1 - Avalia cliente com salário acima de 5000 com idade igual ou superior a 30 anos (deve retornar os tipos pessoal e consignado)
        String name = "Cliente Com salário superior a 5000 e idade igual ou superior a 30";
        String cpf = "112.358.132-13";
        Integer age = 30;
        String location = "MG";
        Integer income = 6000;

        Customer customer1 = this.getACustomer(name, cpf, age, location, income);

        LoanCalculator calculator = new LoanCalculator(customer1);

        Output output = calculator.calculate();

        assertEquals(name, output.customer());
        assertEquals(2, output.loans().size());
        Assertions.assertEquals(LoanTypes.PERSONAL, output.loans().get(0).type());
        Assertions.assertEquals(4, output.loans().get(0).tax());
        Assertions.assertEquals(LoanTypes.PAYROLL, output.loans().get(1).type());
        Assertions.assertEquals(2, output.loans().get(1).tax());

        // Cenário 2 - Avalia cliente com salário acima de 5000 com idade inferior a 30 anos (deve retornar todos os 3 tipos de emprestimo)
        name = "Cliente Com salário superior a 5000 e idade inferior a 30";
        age = 29;
        location = "SP";

        Customer customer2 = this.getACustomer(name, cpf, age, location, income);

        calculator = new LoanCalculator(customer2);

        output = calculator.calculate();

        assertEquals(name, output.customer());
        assertEquals(3, output.loans().size());
        Assertions.assertEquals(LoanTypes.PERSONAL, output.loans().get(0).type());
        Assertions.assertEquals(4, output.loans().get(0).tax());
        Assertions.assertEquals(LoanTypes.COLLATERALIZED, output.loans().get(1).type());
        Assertions.assertEquals(3, output.loans().get(1).tax());
        Assertions.assertEquals(LoanTypes.PAYROLL, output.loans().get(2).type());
        Assertions.assertEquals(2, output.loans().get(2).tax());
    }

    @Test
    @DisplayName("Given a Customer with a null income, When invoke LoanCalculator for this customer, then should return an IllegalArgument exception")
    public void shouldReturnAndExceptionIfCustomerIncomeIsNull() {

        // Cenário 1 - Avalia cliente com salário acima de 5000 com idade igual ou superior a 30 anos (deve retornar os tipos pessoal e consignado)
        String name = "Cliente Com salário nulo";
        String cpf = "112.358.132-13";
        Integer age = 30;
        String location = "MG";

        Customer customer = this.getACustomer(name, cpf, age, location, null);

        final LoanCalculator calculator = new LoanCalculator(customer);

        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, calculator::calculate);

        Assertions.assertEquals("O salário do cliente não pode ser nulo.", exception.getMessage());
    }
}