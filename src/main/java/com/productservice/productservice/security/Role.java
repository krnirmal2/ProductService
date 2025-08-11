package com.productservice.productservice.security;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// @JsonDeserialize(as = Role.class)
public class Role extends BaseModel { // this  model copy from userservice but we should put this in some common places (common repo)
  private String role;
}
