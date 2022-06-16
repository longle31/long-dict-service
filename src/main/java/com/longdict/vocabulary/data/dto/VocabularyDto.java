package com.longdict.vocabulary.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.longdict.data.dto.Dto;

@JsonDeserialize
public class VocabularyDto implements Dto{
	private static final long serialVersionUID = -4158429955400027496L;
	
	private String word;
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public VocabularyDto() {
		// TODO Auto-generated constructor stub
	}

}
