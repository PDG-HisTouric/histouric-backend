package com.pdg.histouric.repository;

import com.pdg.histouric.model.HistouricUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<HistouricUser, UUID> {
    Optional<HistouricUser> findByUsername(String username);
    Optional<HistouricUser> findByEmail(String email);
}
