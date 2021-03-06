package spittr.domain;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

public class Spitter {
	private Long id;

	@NotNull
	@Size(min = 5, max = 16, message = "{username.size}")
	private String username;

	@NotNull
	@Size(min = 5, max = 25, message = "{password.size}")
	private String password;

	@NotNull
	@Size(min = 2, max = 30, message = "{firstName.size}")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 30, message = "{lastName.size}")
	private String lastName;
	@NotNull
	private String fullName;
	
	@NotNull
	@Email(message = "{email.valid}")
	private String email;
	@NotNull
	private boolean updateByEmail;
	
	public Spitter() {
	}

	public Spitter(String username, String password, String firstName, String lastName,String fullName, String email,boolean updateByEmail) {
		this(null, username, password, firstName, lastName,fullName, email,updateByEmail);
	}

	public Spitter(Long id, String username, String password, String firstName, String lastName,String fullName, String email,boolean updateByEmail) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName=fullName;
		this.email = email;
		this.updateByEmail=updateByEmail;
	}
	
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean getUpdateByEmail() {
		return updateByEmail;
	}

	public void setUpdateByEmail(boolean updateByEmail) {
		this.updateByEmail = updateByEmail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "firstName", "lastName", "username", "password", "email");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "firstName", "lastName", "username", "password", "email");
	}

}
