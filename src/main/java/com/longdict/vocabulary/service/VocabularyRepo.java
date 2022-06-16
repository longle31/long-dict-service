package com.longdict.vocabulary.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longdict.vocabulary.data.model.Vocabulary;

@Repository
public interface VocabularyRepo extends JpaRepository<Vocabulary, String> {
}
