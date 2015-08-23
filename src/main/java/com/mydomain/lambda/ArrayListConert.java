package com.mydomain.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import junit.framework.Assert;

public class ArrayListConert {

	@Test
	public void test01() {

		int expected = 2;
		int actual;

		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("aaa11");
		list.add("aaa2");
		list.add("aaa33");
		list.add("aaa4");

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

		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("aaa11");
		list.add("aaa2");
		list.add("aaa33");
		list.add("aaa4");

		actual = Arrays.asList(
				list.stream().filter(s -> s.length() > 4)
						.toArray(size -> new String[size])).size();

		Assert.assertEquals(expected, actual);
	}
}
