package com.egen.passport.demo.repository;

import com.egen.passport.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item , Long> {
}
