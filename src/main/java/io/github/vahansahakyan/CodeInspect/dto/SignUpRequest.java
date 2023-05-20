package io.github.vahansahakyan.CodeInspect.dto;

public class SignUpRequest {
  private String username;
  private String password;
  private String name;
  private String authority;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  @Override
  public String toString() {
    return "SignUpRequest{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", authority='" + authority + '\'' +
            '}';
  }
}
