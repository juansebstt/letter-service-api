package com.letterserviceapi.repositories;

import com.letterserviceapi.common.entities.LetterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<LetterModel, Long> {
}
