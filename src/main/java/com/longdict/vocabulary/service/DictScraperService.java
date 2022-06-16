package com.longdict.vocabulary.service;

import java.net.URI;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.longdict.vocabulary.data.converter.VocabularyConverter;
import com.longdict.vocabulary.data.dto.VocabularyDto;
import com.longdict.vocabulary.data.model.Vocabulary;

@Component
public class DictScraperService {

	public Vocabulary get(@NotBlank String word) {
		DictScraperClient client = new DictScraperClient();
		VocabularyDto dto = client.get(word);
		return VocabularyConverter.create().convert(dto);
	}
	
	public static class DictScraperClient {

		public VocabularyDto get(@NotBlank String word) {
			RestTemplate restTemplate =  new RestTemplate();
			URI url = URI.create("http://127.0.0.1:5000/vocabulary/".concat(word));
//			return restTemplate.getForEntity(url, VocabularyDto.class).getBody();
			ResponseEntity<VocabularyDto> response = restTemplate.getForEntity(url, VocabularyDto.class);
			if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
				//
			}
			return response.getBody();
		}
		
	}

}
