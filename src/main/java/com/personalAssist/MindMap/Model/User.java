package com.personalAssist.MindMap.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", // join table name
			joinColumns = @JoinColumn(name = "user_id"), // FK from users
			inverseJoinColumns = @JoinColumn(name = "role_id") // FK from roles

	)
	private Set<Role> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "user_services", // The join table
			joinColumns = @JoinColumn(name = "user_id"), // Foreign key in the join table
			inverseJoinColumns = @JoinColumn(name = "service_id") // Foreign key for services
	)
	private List<UserServiceModal> userServices = new ArrayList<>();

	public List<UserServiceModal> getUserServices() {
		return userServices;
	}

	public void setUserServices(List<UserServiceModal> userServices) {
		this.userServices = userServices;
	}

	public User() {
		super();
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(String firstName, String lastName, String email, String phone, Set<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
	}

	@CreationTimestamp
	private LocalDateTime createdAt;;

	@CreationTimestamp
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	 // Helper method to add a service
    public void addService(UserServiceModal service) {
        this.userServices.add(service);
        service.getUsers().add(this);
    }

}
