package com.example.komponente_spring.repository;

import com.example.komponente_spring.domain.ReservationSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationSlotRepository extends JpaRepository<ReservationSlot, Long> {
}
