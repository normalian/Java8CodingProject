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

		// 30歳未満の若者を 50歳以上に
		List<Person> list = getSampleList();

		list.replaceAll((Person p) -> {
			if (p.getAge() < 30)
				p.setAge(50);
			return p;
		});

		// 30歳以上しかいない状態へ
		actual = list.stream().filter(p -> p.getAge() <= 30).count() == 0;
		Assert.assertEquals(expected, actual);
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
