package demo.servlet.context;

import java.time.LocalDate;

public class Policy {
	private int policyId;
	private String name;
	private int age;
	private LocalDate expiresOn;

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setExpiresOn(LocalDate expiresOn) {
		this.expiresOn = expiresOn;
	}

	public LocalDate getExppiresOn() {
		return expiresOn;
	}
}
