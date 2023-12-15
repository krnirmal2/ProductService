package com.productservice.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDtos {
    private Long id;
    private int price;

    private String title;
    private String category;
    private String description;
    private String image;

}
