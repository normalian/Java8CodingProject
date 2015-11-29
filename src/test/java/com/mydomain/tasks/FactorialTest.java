package com.mydomain.tasks;

import java.math.BigInteger;

import org.junit.Test;

//「nの階乗の最下位の連続する0の数を求めるアルゴリズムを書け」
//「三目並べの勝敗を判定するプログラムを書け」

public class FactorialTest {
	@Test
	public void test01() {
		System.out.println(factorial(new BigInteger("2")));
		System.out.println(factorial(new BigInteger("5")));
		System.out.println(factorial(new BigInteger("10")));
	}

	static public BigInteger factorial(BigInteger i) {
		if (i.equals(BigInteger.ONE))
			return BigInteger.ONE;
		else {
			return i.multiply(factorial(i.subtract(BigInteger.ONE)));
		}
	}
}
