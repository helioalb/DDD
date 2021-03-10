package me.helioalbano.aggregate.persistence.domain.model;

import me.helioalbano.aggregate.persistence.domain.model.exceptions.DifferentCurrencyException;

public class Money {
    private Currency currency;
    private Integer amount;

    public Money(Currency currency, Integer amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Money() {
    	this.currency = Currency.BRL;
    	this.amount = 0;
    }

    public Integer getAmount() {
        return amount;
    }

    public Currency getCurrency() {
    	return currency;
    }

	public Money multiply(Integer quantity) {
		return new Money(currency, this.amount * quantity);
	}

	public Boolean isCurrency(Currency currency) {
		return this.currency == currency;
	}

	public Money plus(Money money) {
		if (!money.isCurrency(currency)) throw new DifferentCurrencyException(
				"Não é possível somar dois tipos de moedas diferentes");

		return new Money(this.currency, this.amount + money.getAmount());
	}

	public Money minus(Money money) {
		if (!money.isCurrency(currency)) throw new DifferentCurrencyException(
				"Não é possível subtrair dois tipos de moedas diferentes");

		return new Money(this.currency, this.amount - money.getAmount());
	}
}
