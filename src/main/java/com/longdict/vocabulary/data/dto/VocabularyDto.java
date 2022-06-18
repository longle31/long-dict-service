package com.longdict.vocabulary.data.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.longdict.data.dto.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize
@JsonSerialize
@Getter
@Setter
@Builder
public class VocabularyDto implements Dto{
	private static final long serialVersionUID = -4158429955400027496L;
	
	private String word;
	
	private List<String> meanings;
	
	private Map<String, List<String>> pronunciation;
	
	private List<String> examples;
	
	@JsonAlias({"related_vocabulary", "relatedVocabulary"})
	private Map<String, String> relatedVocabulary;
	
	@JsonAlias({"extra_vocabulary", "extraVocabulary"})
	private Map<String, String> extraVocabulary;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public VocabularyDto() {
	}

	private VocabularyDto(String word, List<String> meanings, Map<String, List<String>> pronunciation,
			List<String> examples, Map<String, String> relatedVocabulary, Map<String, String> extraVocabulary) {
		this.word = word;
		this.meanings = meanings;
		this.pronunciation = pronunciation;
		this.examples = examples;
		this.relatedVocabulary = relatedVocabulary;
		this.extraVocabulary = extraVocabulary;
	}
	
	
}
