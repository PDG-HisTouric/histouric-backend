package com.pdg.histouric.repository;

import com.pdg.histouric.model.BICImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BICImageRepository extends JpaRepository<BICImage, UUID> {
}
