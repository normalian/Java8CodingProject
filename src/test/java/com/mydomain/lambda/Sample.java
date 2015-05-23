package com.mydomain.lambda;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mydomain.model.Person;

public class Sample {
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

		// 30çŒñ¢ñûÇÃé·é“Ç 50çŒà»è„Ç…
		List<Person> list = getSampleList();

		list.replaceAll((Person p) -> {
			if (p.getAge() < 30)
				p.setAge(50);
			return p;
		});

		// 30çŒà»è„ÇµÇ©Ç¢Ç»Ç¢èÛë‘Ç÷
		actual = list.stream().filter(p -> p.getAge() <= 30).count() == 0;
		Assert.assertEquals(expected, actual);
	}

	List<Person> getSampleList() {
		List<Person> list = new ArrayList<Person>();

		{
			Person p = new Person();
			p.setAge(23);
			p.setName("é·é“1");
			list.add(p);
		}
		{
			Person p = new Person();
			p.setAge(43);
			p.setName("Ç®Ç¡Ç≥ÇÒ1");
			list.add(p);
		}
		{
			Person p = new Person();
			p.setAge(17);
			p.setName("é·é“2");
			list.add(p);
		}
		{
			Person p = new Person();
			p.setAge(31);
			p.setName("Ç®Ç¡Ç≥ÇÒ2");
			list.add(p);
		}

		return list;
	}
}
