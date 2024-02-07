package com.productservice.productservice.security;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTObject {
  // NOTE 24 UP:
  // this will return in the DTO
  // After validation of the validate method it should return
  // some kind of message or that validate successfully
  private String email;
  private Long userId;
  private Date expiryAt;
  private Date createdAt;
  private List<Role> roles;
}
