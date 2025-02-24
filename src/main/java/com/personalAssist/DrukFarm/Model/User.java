package com.personalAssist.DrukFarm.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String phone;
	
	@Column(nullable = false)
	private String password;
	
	private String location;
	
	@CreationTimestamp
	private LocalDateTime createdAt;;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", // join table name
			joinColumns = @JoinColumn(name = "user_id"), // FK from users
			inverseJoinColumns = @JoinColumn(name = "role_id") // FK from roles

	)
	private Set<Role> roles = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "user_services", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "service_id") 
	)
	private List<UserServiceModal> userServices = new ArrayList<>();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private BuyerDetail buyerDetail;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private FarmerDetail farmerDetail;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private TransporterDetail transporterDetail;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DP profileImage;

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

	public User( String userName, String email, String phone, Set<Role> roles) {
		super();
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BuyerDetail getBuyerDetail() {
		return buyerDetail;
	}

	public void setBuyerDetail(BuyerDetail buyerDetail) {
		this.buyerDetail = buyerDetail;
	}

	public TransporterDetail getTransporterDetail() {
		return transporterDetail;
	}

	public void setTransporterDetail(TransporterDetail transporterDetail) {
		this.transporterDetail = transporterDetail;
	}

}
