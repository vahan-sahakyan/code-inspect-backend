package io.github.vahansahakyan.CodeInspect.domain;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  private static final long serialVersionUID = 5742050515818119031L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate cohortStartDate;
  private String username;
  private String password;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  @JsonIgnore
  private Set<Authority> authorities = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getCohortStartDate() {
    return cohortStartDate;
  }

  public void setCohortStartDate(LocalDate cohortStartDate) {
    this.cohortStartDate = cohortStartDate;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
//    List<GrantedAuthority> roles = new ArrayList<>();
//    roles.add(new Authority("ROLE_STUDENT"));
//    return roles;
    return authorities;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("[ ");
    for (Authority auth: authorities) {
      sb.append("\"" + auth.getAuthority() + "\", ");
    }
    sb.append("]");

    return "User {" +
            " id=" + id +
            ", username=\"" + username + '\"' +
            ", name=\"" + username + '\"' +
            ", cohortStartDate=" + cohortStartDate +
            ", authorities=" + sb.toString() +
            '}';
  }

}
