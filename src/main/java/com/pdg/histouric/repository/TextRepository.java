package com.pdg.histouric.repository;

import com.pdg.histouric.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface TextRepository extends JpaRepository<Text, UUID> {
}
