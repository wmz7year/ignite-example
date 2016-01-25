package com.github.wmz7year.example;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.apache.ignite.resources.SpringResource;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity implements Externalizable {

	@SpringResource(resourceName = "exampleService")
	@Autowired
	private transient ExampleService service;

	private String field;

	public Entity() {

	}

	public Entity(String field) {
		this.field = field;
	}

	public void doServiceMethod() {
		service.doSomething();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(field);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		field = in.readUTF();
	}

}
