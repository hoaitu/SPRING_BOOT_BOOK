package com.ht.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_role")
public class UsersRoles implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Role role;
	private User users;

	public UsersRoles() {
	}

	public UsersRoles(Role role, User users) {
		this.role = role;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	public User getUsers() {
		return this.users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

}
