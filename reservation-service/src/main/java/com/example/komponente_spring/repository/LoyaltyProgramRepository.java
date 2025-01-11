package com.example.komponente_spring.repository;

import com.example.komponente_spring.domain.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {
}
