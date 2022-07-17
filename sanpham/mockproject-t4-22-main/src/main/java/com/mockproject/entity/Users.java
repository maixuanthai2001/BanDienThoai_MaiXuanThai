package com.mockproject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

	private static final long serialVersionUID = -8005275174150207433L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "hashPassword")
	private String hashPassword;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "createdDate")
	@CreationTimestamp
	private Timestamp createdDate;
	
	@Column(name = "imgUrl")
	private String imgUrl;
	
	@Column(name = "isDeleted")
	private Boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"application", "hibernateLazyInitializer"})
	private Roles role;
}
