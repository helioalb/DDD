package me.helioalbano.aggregate.persistence.domain.model;

public class Money {
    private Currency currency;
    private Integer amount;

    public Money(Currency currency, Integer amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
