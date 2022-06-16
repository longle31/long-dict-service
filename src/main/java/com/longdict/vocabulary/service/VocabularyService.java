package com.longdict.vocabulary.service;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longdict.vocabulary.data.model.Vocabulary;

@Component
public class VocabularyService {

	@Autowired
	private VocabularyRepo repo;
	
	@Autowired
	private DictScraperService scraperService;
	
	public Vocabulary get(@NotBlank String word) {
		Optional<Vocabulary> vocabulary = repo.findById(word);
		if(vocabulary.isPresent()) {
			return vocabulary.get();
		}
		return getFromDictScraperService(word);
	}

	private Vocabulary getFromDictScraperService(@NotBlank String word) {
		Vocabulary vocabulary = scraperService.get(word);
		repo.save(vocabulary);
		return vocabulary;
	}

}
