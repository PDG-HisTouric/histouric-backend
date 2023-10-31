package com.pdg.histouric.repository;

import com.pdg.histouric.model.BIC;
import com.pdg.histouric.model.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NicknameRepository extends JpaRepository<Nickname, UUID> {
    @Query(value = "SELECT nickname.bic FROM Nickname nickname WHERE LOWER(nickname.nickname) LIKE LOWER(CONCAT('%', :nickname, '%'))")
    Optional<List<BIC>> findByNicknameContainsIgnoreCase(String nickname);
}
