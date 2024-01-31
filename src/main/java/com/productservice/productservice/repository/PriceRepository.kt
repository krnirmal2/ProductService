package com.productservice.productservice.repository;

import com.productservice.productservice.models.Price
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PriceRepository : JpaRepository<Price, UUID> {
}