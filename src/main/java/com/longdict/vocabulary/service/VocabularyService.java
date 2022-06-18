package com.longdict.vocabulary.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longdict.vocabulary.data.model.Pronunciation;
import com.longdict.vocabulary.data.model.Vocabulary;

@Component
public class VocabularyService {

	@Autowired
	private VocabularyRepo repo;
	
	@Autowired
	private PronunciationRepo pronunciationRepo;
	
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
		persistPronunciation(vocabulary.getPronunciations());
		repo.save(vocabulary);
		return vocabulary;
	}

	@Transactional
	private void persistPronunciation(Set<Pronunciation> pronunciations) {
		if(CollectionUtils.isEmpty(pronunciations)) {
			return;
		}
		
		List<String> ipas = pronunciations.stream().map(Pronunciation::getIpa).collect(Collectors.toList());
		List<Pronunciation> existingPronunciationsInDB = pronunciationRepo.findAllById(ipas);
		
		if(CollectionUtils.isEmpty(existingPronunciationsInDB)) {
			pronunciationRepo.saveAll(pronunciations);
			return;
		}
		
		List<String> existingIpas = existingPronunciationsInDB.stream().map(Pronunciation::getIpa).collect(Collectors.toList());
		List<Pronunciation> persistedPronunciations = pronunciations.stream().filter(p -> !existingIpas.contains(p.getIpa())).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(persistedPronunciations)) {			
			pronunciationRepo.saveAll(persistedPronunciations);
		}

	}

}
