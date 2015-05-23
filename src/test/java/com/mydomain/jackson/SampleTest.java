package com.mydomain.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import com.mydomain.model.Person;

public class SampleTest {
	@Test
	public void readTest01() throws JsonParseException, IOException {
		Person expected = new Person();
		Person actual;

		expected.setAge(34);
		expected.setName("お名前です");

		String data = "{ \"age\" : \"34\", \"name\" : \"お名前です\" } ";
		ObjectMapper mapper = new ObjectMapper();
		actual = mapper.readValue(data, Person.class);

		Assert.assertEquals(expected, actual);
	}
}
