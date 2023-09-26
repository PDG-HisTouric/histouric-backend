package com.pdg.histouric.repository;

import com.pdg.histouric.model.History;
import com.pdg.histouric.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface TextRepository extends JpaRepository<Text, UUID> {
}
