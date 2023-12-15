package com.productservice.productservice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class Category extends BaseModel{
    private String name;
}
