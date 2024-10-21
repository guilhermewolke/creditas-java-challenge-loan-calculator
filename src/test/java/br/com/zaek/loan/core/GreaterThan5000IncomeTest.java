package br.com.zaek.loan.core;

import br.com.zaek.loan.dto.Customer;
import br.com.zaek.loan.dto.Loan;
import br.com.zaek.loan.dto.LoanTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GreaterThan5000IncomeTest extends CoreTest {

    @Test
    @DisplayName("Given an greater than 5000 income customer, when evaluate this customer, then returns me both personal and payroll loan options")
    public void shouldReturnBothPersonalAndPayrollLoanOptions() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 31;
        final String location = "SP";
        final Integer income = 5500;

        final Customer customer = this.getACustomer(name, cpf, age, location, income);
        GreaterThan5000Income loan = new GreaterThan5000Income();

        List<Loan> available = loan.calculate(customer);

        Assertions.assertEquals(2, available.size());
        Assertions.assertEquals(LoanTypes.PERSONAL, available.get(0).type());
        Assertions.assertEquals(4, available.get(0).tax());
        Assertions.assertEquals(LoanTypes.PAYROLL, available.get(1).type());
        Assertions.assertEquals(2, available.get(1).tax());
    }

    @Test
    @DisplayName("Given an greater than 5000 income, less than 30 years old customer, when evaluate this customer, then returns me all of available loan options")
    public void shouldReturnAllOfAvailableLoanOptions() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "SP";
        final Integer income = 5500;

        final Customer customer = this.getACustomer(name, cpf, age, location, income);
        GreaterThan5000Income loan = new GreaterThan5000Income();

        List<Loan> available = loan.calculate(customer);

        Assertions.assertEquals(3, available.size());
        Assertions.assertEquals(LoanTypes.PERSONAL, available.get(0).type());
        Assertions.assertEquals(4, available.get(0).tax());
        Assertions.assertEquals(LoanTypes.COLLATERALIZED, available.get(1).type());
        Assertions.assertEquals(3, available.get(1).tax());
        Assertions.assertEquals(LoanTypes.PAYROLL, available.get(2).type());
        Assertions.assertEquals(2, available.get(2).tax());
    }

    @Test
    @DisplayName("Given an income less than or equal 5000 customer, when evaluate this customer, then should returns an InvalidArgument exception")
    public void shouldReturnExceptionIfIncomeWasGreaterThan3000() {
        final String name = "Cliente 1";
        final String cpf = "112.358.132-13";
        final Integer age = 29;
        final String location = "SP";
        final Integer income = 5000;
        Customer customer  = this.getACustomer(name, cpf, age, location, income);
        GreaterThan5000Income loan = new GreaterThan5000Income();

        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> loan.calculate(customer));

        Assertions.assertEquals("O salário informado é inválido neste contexto.", exception.getMessage());
    }

}