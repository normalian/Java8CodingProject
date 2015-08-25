package com.mydomain.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import junit.framework.Assert;

import org.junit.Test;

public class ArrayListConertTest {

	@Test
	public void test01() {

		int expected = 2;
		int actual;

		List<String> list = Arrays.asList("aaa", "aaa11", "aaa2", "aaa33",
				"aaa4");

		Stream<String> ss = list.stream().filter(s -> s.length() > 4);
		String[] sAry = ss.toArray(size -> new String[size]);
		List<String> actuallist = Arrays.asList(sAry);

		actual = actuallist.size();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void test02() {

		int expected = 2;
		int actual;

		List<String> list = Arrays.asList("aaa", "aaa11", "aaa2", "aaa33",
				"aaa4");

		actual = Arrays.asList(
				list.stream().filter(s -> s.length() > 4)
						.toArray(size -> new String[size])).size();

		Assert.assertEquals(expected, actual);
	}
}
