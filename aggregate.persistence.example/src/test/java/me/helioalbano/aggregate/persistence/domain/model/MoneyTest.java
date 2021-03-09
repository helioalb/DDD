package me.helioalbano.aggregate.persistence.domain.model;

import static me.helioalbano.aggregate.persistence.domain.model.Currency.BRL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    public void testConstructionOfMoney() {
        Money money = new Money(BRL, 1000);

        assertEquals(1000, money.getAmount());
    }    
}
