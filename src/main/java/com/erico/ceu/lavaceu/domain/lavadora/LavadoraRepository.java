package com.erico.ceu.lavaceu.domain.lavadora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LavadoraRepository extends JpaRepository<Lavadora, UUID> {
}
