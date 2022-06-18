package com.longdict.vocabulary.data.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.longdict.data.converter.DataConverter;
import com.longdict.vocabulary.data.dto.VocabularyDto;
import com.longdict.vocabulary.data.model.Pronunciation;
import com.longdict.vocabulary.data.model.Vocabulary;

public class VocabularyConverter implements DataConverter<Vocabulary, VocabularyDto> {
	
	private static final String DELIMITER = "\\|";

	public static VocabularyConverter create() {
		return new VocabularyConverter();
	}

	@Override
	public VocabularyDto convert(Vocabulary model) {
		return VocabularyDto.builder().word(model.getWord())
				.meanings(splitString(model.getMeaninngs()))
				.pronunciation(toMap(model.getPronunciations()))
				.examples(splitString(model.getExamples()))
				.relatedVocabulary(toMap(model.getRelatedVocabulary()))
				.extraVocabulary(toMap(model.getExtraVocabulary()))
				.build();
	}

	@Override
	public Vocabulary convert(VocabularyDto dto) {
		return Vocabulary.builder().word(dto.getWord())
				.meaninngs(joinStringList(dto.getMeanings(),DELIMITER))
				.pronunciations(toPronunciations(dto.getPronunciation()))
				.examples(joinStringList(dto.getExamples(), DELIMITER))
				.relatedVocabulary(toJson(dto.getRelatedVocabulary()))
				.extraVocabulary(toJson(dto.getExtraVocabulary()))
				.build();
	}

	private static Set<Pronunciation> toPronunciations(Map<String, List<String>> pronunciations) {
		return pronunciations.entrySet().stream().map(VocabularyConverter::toPronunciation).collect(Collectors.toSet());
	}

	private static Pronunciation toPronunciation(Map.Entry<String, List<String>> entry) {
		Pronunciation pronunciation = new Pronunciation();
		pronunciation.setIpa(entry.getKey());
		pronunciation.setVoices(joinStringList(entry.getValue(), DELIMITER));
		return pronunciation;
	}
	private static Map<String, List<String>> toMap(Set<Pronunciation> pronunciations) {
		return pronunciations.stream().filter(Objects::nonNull).collect(Collectors.toMap(Pronunciation::getIpa, p -> splitString(p.getVoices())));
	}
	
	
	private static String joinStringList(List<String> stringList, String demliter) {
		return stringList.stream().filter(StringUtils::isNotBlank).collect(Collectors.joining(demliter));
	}
	
	private static List<String> splitString(String string) {
		return Arrays.asList(string.split(DELIMITER));
	}
	
	private static String toJson(Map<?, ?> map) {
		try {
			return new ObjectMapper().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			return StringUtils.EMPTY;
		}
	}
	
	private <K, V>  Map<K, V> toMap(String data) {
		if(StringUtils.isEmpty(data)) {
			return Collections.emptyMap(); 
		}
		try {
			TypeReference<HashMap<K, V>> typeRef = new TypeReference<HashMap<K,V>>() {};
			return new ObjectMapper().readValue(data, typeRef);
		} catch (JsonMappingException e) {
			return Collections.emptyMap();
		} catch (JsonProcessingException e) {
			return Collections.emptyMap();
		}
	}

}
