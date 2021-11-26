package com.project.cricket.config;

public enum ClassNumber {
	TESTS(1),
	ODIS(2),
	T20IS(3),
	FIRSTCLASS(4),
	LISTA(5),
	T20S(6);

	private final int value;

	ClassNumber(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
