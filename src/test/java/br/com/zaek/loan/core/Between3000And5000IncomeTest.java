package br.com.zaek.loan.core;

import br.com.zaek.loan.dto.Customer;
import br.com.zaek.loan.dto.Loan;
import br.com.zaek.loan.dto.LoanTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Between3000And5000IncomeTest extends CoreTest {
    @Test
    @DisplayName("Given an income between 3000 and 5000 customer, when evaluate this customer, then returns me only a personal loan option")
    public void shouldReturnOnlyAPersonalLoanOption() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "BH";
        final Integer income = 4000;
        Customer customer  = this.getACustomer(name, cpf, age, location, income);

        Between3000And5000Income loan = new Between3000And5000Income();

        List<Loan> available = loan.calculate(customer);

        Assertions.assertEquals(1, available.size());
        Assertions.assertEquals(LoanTypes.PERSONAL, available.get(0).type());
        Assertions.assertEquals(4, available.get(0).tax());
    }

    @Test
    @DisplayName("Given an income between 3000 and 5000, 'SP' location customer, when evaluate this customer, then returns me both personal and collateralized loan options")
    public void shouldReturnBothPersonalAndCollateralizedLoanOptions() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "SP";
        final Integer income = 4000;
        Customer customer  = this.getACustomer(name, cpf, age, location, income);

        Between3000And5000Income loan = new Between3000And5000Income();

        List<Loan> available = loan.calculate(customer);

        Assertions.assertEquals(2, available.size());
        Assertions.assertEquals(LoanTypes.PERSONAL, available.get(0).type());
        Assertions.assertEquals(4, available.get(0).tax());
        Assertions.assertEquals(LoanTypes.COLLATERALIZED, available.get(1).type());
        Assertions.assertEquals(3, available.get(1).tax());
    }

    @Test
    @DisplayName("Given an income less than 3000 or greater than 5000 customer, when evaluate this customer, then should returns an InvalidArgument exception")
    public void shouldReturnExceptionIfIncomeWasGreaterThan3000() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "SP";
        final Customer customer1 = this.getACustomer(name, cpf, age, location, 2000);
        Between3000And5000Income loan = new Between3000And5000Income();

        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> loan.calculate(customer1));

        Assertions.assertEquals("O salário informado é inválido neste contexto.", exception.getMessage());

        final Customer customer2 = this.getACustomer(name, cpf, age, location, 6000);

        final IllegalArgumentException exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> loan.calculate(customer2));

        Assertions.assertEquals("O salário informado é inválido neste contexto.", exception.getMessage());
    }

}