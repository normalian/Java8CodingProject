package com.mydomain.lambda;

import org.junit.Assert;
import org.junit.Test;

public class AutoBoxingTest {
	@Test
	public void test01() {
		boolean expected = false;
		IsNegative isNegative = (n) -> n < 0;
		boolean actual = isNegative.check(10);

		Assert.assertEquals(expected, actual);
	}
}

@FunctionalInterface
interface IsNegative {
	boolean check(int n);
}