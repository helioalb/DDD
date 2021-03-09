package me.helioalbano.aggregate.persistence.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    public void testConstructionOfMoney() {
        Money money = new Money("BRL", 1000);

        assertEquals("BRL", money.getCurrency());
        assertEquals(1000, money.getAmount());
    }    
}
