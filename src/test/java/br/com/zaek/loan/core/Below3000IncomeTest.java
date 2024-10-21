package br.com.zaek.loan.core;

import br.com.zaek.loan.dto.Customer;
import br.com.zaek.loan.dto.Loan;
import br.com.zaek.loan.dto.LoanTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class Below3000IncomeTest extends CoreTest {

    @Test
    @DisplayName("Given A 3000 or less income customer, when evaluate this customer, then returns me only a personal loan option")
    public void shouldReturnOnlyAPersonalLoanOption() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "BH";
        final Integer income = 3000;
        Customer customer  = this.getACustomer(name, cpf, age, location, income);

        Below3000Income loan = new Below3000Income();

        List<Loan> available = loan.calculate(customer);

        Assertions.assertEquals(1, available.size());
        Assertions.assertEquals(LoanTypes.PERSONAL, available.get(0).type());
        Assertions.assertEquals(4, available.get(0).tax());
    }

    @Test
    @DisplayName("Given A 3000 or less income, 30 or less years old, 'SP' location customer, when evaluate this customer, then returns me both personal and collateralized loan options")
    public void shouldReturnBothPersonalAndCollateralizedLoanOptions() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "SP";
        final Integer income = 3000;
        Customer customer  = this.getACustomer(name, cpf, age, location, income);

        Below3000Income loan = new Below3000Income();

        List<Loan> available = loan.calculate(customer);

        Assertions.assertEquals(2, available.size());
        Assertions.assertEquals(LoanTypes.PERSONAL, available.get(0).type());
        Assertions.assertEquals(4, available.get(0).tax());
        Assertions.assertEquals(LoanTypes.COLLATERALIZED, available.get(1).type());
        Assertions.assertEquals(3, available.get(1).tax());
    }

    @Test
    @DisplayName("Given an income greater than 3000 customer, when evaluate this customer, then should returns an InvalidArgument exception")
    public void shouldReturnExceptionIfIncomeWasGreaterThan3000() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "SP";
        final Integer income = 3001;
        Customer customer  = this.getACustomer(name, cpf, age, location, income);
        Below3000Income loan = new Below3000Income();

        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> loan.calculate(customer));

        Assertions.assertEquals("O salário informado é inválido neste contexto.", exception.getMessage());
    }

}