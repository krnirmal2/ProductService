package com.productservice.productservice.repository;

import com.productservice.productservice.models.Price;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, UUID> {}
