package me.helioalbano.aggregate.persistence.domain.model;

import static me.helioalbano.aggregate.persistence.domain.model.Currency.BRL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import me.helioalbano.aggregate.persistence.domain.model.exceptions.DifferentCurrencyException;

public class MoneyTest {
    @Test
    public void testConstructionOfMoney() {
        Money money = new Money(BRL, 1000);

        assertEquals(1000, money.getAmount());
    }

    @Test
    public void testPlusMethodWithSameCurrency() {
    	Money one = new Money(Currency.BRL, 1);
    	Money two = new Money(Currency.BRL, 2);

    	assertEquals(3, one.plus(two).getAmount());
    }

    @Test
    public void testPlusMethodWithDifferentCurrency() {
    	Money one = new Money(Currency.BRL, 1);
    	Money two = new Money(Currency.USD, 2);

    	Exception exception = assertThrows(
    			DifferentCurrencyException.class, () -> {
    				one.plus(two).getAmount();
    	});

    	String message = "Não é possível somar dois tipos de moedas diferentes";

    	assertEquals(message, exception.getMessage());
    }

    @Test
    public void testMinusMethodWithSameCurrency() {
    	Money one = new Money(Currency.BRL, 1);
    	Money two = new Money(Currency.BRL, 2);

    	assertEquals(1, two.minus(one).getAmount());
    }

    @Test
    public void testMinusMethodWithDifferentCurrency() {
    	Money one = new Money(Currency.BRL, 1);
    	Money two = new Money(Currency.USD, 2);

    	Exception exception = assertThrows(
    			DifferentCurrencyException.class, () -> {
    				two.minus(one).getAmount();
    	});

    	String msg = "Não é possível subtrair dois tipos de moedas diferentes";

    	assertEquals(msg, exception.getMessage());
    }

    @Test
    public void testMultiplyMethod() {
    	Money one = new Money(Currency.BRL, 1);

    	Money two = one.multiply(2);

    	assertEquals(2, two.getAmount());
    }
}
