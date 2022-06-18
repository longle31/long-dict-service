package com.longdict.vocabulary.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longdict.vocabulary.data.model.Pronunciation;

@Repository
public interface PronunciationRepo extends JpaRepository<Pronunciation, String> {
}
