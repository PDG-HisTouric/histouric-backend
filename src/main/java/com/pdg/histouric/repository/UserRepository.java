package com.pdg.histouric.repository;

import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<HistouricUser, UUID> {
    Optional<HistouricUser> findByNickname(String nickname);
    Optional<HistouricUser> findByEmail(String email);

    @Query(value = "SELECT histouric_user.roles FROM HistouricUser histouric_user WHERE histouric_user.id = :userId")
    Optional<List<Role>> getRolesByUserId(@Param("userId") UUID userId);

    Optional<List<HistouricUser>> getHistouricUserByRolesContains(Role role);
}
