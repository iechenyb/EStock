package com.cyb.mbean;

public class MyMBean {
	private String name, password;

	public void statckOOM() {
		new StackOutOfMemoryTask().newThread();
	}

	public void methodAreaLeak() {
		new MethodAreaLeak().methodAreaLeak();
	}

	public void localOOM() {
		try {
			new DirectMemoryOOM().directMemoryOOM();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contantPoolLeak() {
		new ConstantPoolLeak().constantPoolLeak();
	}

	public void heapLeak() {
		new HeapLeak().heapLeak();
	}

	@SuppressWarnings("static-access")
	public void stackLeak() {
		new StackLeak().method();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
