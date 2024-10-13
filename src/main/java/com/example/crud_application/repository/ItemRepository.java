package com.example.crud_application.repository;

import com.example.crud_application.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByIdAndUserId(Long id, Long userId);
}
