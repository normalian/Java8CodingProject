package com.mydomain.lambda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mydomain.model.Person;

public class SampleTest {
	// http://qiita.com/kamatama_41/items/c35e29d930b074a3cd2f

	@Test
	public void removeIfTest01() {
		int expected = 2;
		int actual;

		List<Person> list = getSampleList();
		list.removeIf(p -> p.getAge() < 30);
		actual = list.size();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void replaceAllTest01() {
		boolean expected = true;
		boolean actual;

		List<Person> list = getSampleList();

		list.replaceAll((Person p) -> {
			if (p.getAge() < 30)
				p.setAge(50);
			return p;
		});

		actual = list.stream().filter(p -> p.getAge() <= 30).count() == 0;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void filterTest01() {
		long expected = 1;
		long actual;

		List<Person> list = getSampleList();
		actual = list.stream().filter(p -> p.getAge() < 20).count();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void functionalInterfaceTest01() {
		IsNumberInterface isNumberInterface1 = (String value) -> {
			try {
				new BigDecimal(value);
				return true;
			} catch (Exception e) {
				return false;
			}
		};

		boolean expected = true;
		boolean actual = isNumberInterface1.check("01");

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void functionalInterfaceTest02() {
		IsNumberInterface isNumberInterface1 = _val -> {
			try {
				new BigDecimal(_val);
				return true;
			} catch (Exception e) {
				return false;
			}
		};

		boolean expected = true;
		boolean actual = isNumberInterface1.check("01");

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void innerClassTest01() {
		class A {

		}
	}

	List<Person> getSampleList() {
		List<Person> list = new ArrayList<Person>();

		{
			Person p = new Person();
			p.setAge(23);
			p.setName("若者1");
			list.add(p);
		}
		{
			Person p = new Person();
			p.setAge(43);
			p.setName("おっさん1");
			list.add(p);
		}
		{
			Person p = new Person();
			p.setAge(17);
			p.setName("若者2");
			list.add(p);
		}
		{
			Person p = new Person();
			p.setAge(31);
			p.setName("おっさん2");
			list.add(p);
		}

		return list;
	}
}