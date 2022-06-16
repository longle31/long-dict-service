package com.longdict.vocabulary.resource;

import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longdict.vocabulary.data.converter.VocabularyConverter;
import com.longdict.vocabulary.data.dto.VocabularyDto;
import com.longdict.vocabulary.service.VocabularyService;

@RestController
@RequestMapping("vocabulary")
public final class VocabularyResource {
	
	private final VocabularyService vocabularyService;
	
	public VocabularyResource(VocabularyService vocabularyService) {
		this.vocabularyService = vocabularyService;
	}
	
	@GetMapping("{word}")
	public VocabularyDto get(@PathVariable("word") @NotBlank String word) {
		return VocabularyConverter.create().convert(vocabularyService.get(word));
	}
}
