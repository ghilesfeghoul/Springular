package com.mediatheque.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Ghiles FEGHOUL on 27/12/2017
 */

@Entity
@Table(name = "USER")
public class User implements UserDetails, Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Email
  @Column(name="email", unique = true)
  private String email;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Nullable
  @Column(name="inscription_date")
  private String inscription;

  @JsonIgnore
  @Column(name="max_loans")
  private int max_loans;

  @Column(name="loans")
  private int loans;

  @Column(name="current_loans")
  private int current;

  public User() {
    this.current = 0;
    this.inscription = new DateTime().toString();
    this.max_loans = 24;
    this.loans = 0;
  }

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_authority",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
  private List<Authority> authorities;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getInscription() {
    return inscription;
  }

  public void setInscription(String inscription) {
    this.inscription = inscription;
  }

  public int getMax_loans() {
    return max_loans;
  }

  public void setMax_loans(int max_loans) {
    this.max_loans = max_loans;
  }

  public int getLoans() {
    return loans;
  }

  public void setLoans(int loans) {
    this.loans = loans;
  }

  public int getCurrent() {
    return current;
  }

  public void setCurrent(int current) {
    this.current = current;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  // We can add the below fields in the users table.
  // For now, they are hardcoded.
  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }
}
