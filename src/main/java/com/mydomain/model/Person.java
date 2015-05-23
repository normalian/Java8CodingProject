package com.mydomain.model;

public class Person {
	int age;
	String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person == false)
			return false;

		Person p = (Person) obj;
		boolean isAgeSame = age == p.getAge();
		boolean isNameSame = false;
		if (name != null)
			isNameSame = name.equals(p.getName());
		else
			isNameSame = p.getName() == null;
		return isAgeSame && isNameSame;
	}
}
