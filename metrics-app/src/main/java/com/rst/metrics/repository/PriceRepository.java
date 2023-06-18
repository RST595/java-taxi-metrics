package com.rst.metrics.repository;

import com.rst.metrics.entity.MomentPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<MomentPrice, Long> {
}
