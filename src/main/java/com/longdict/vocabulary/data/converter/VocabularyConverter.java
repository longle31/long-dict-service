package com.longdict.vocabulary.data.converter;

import com.longdict.data.converter.DataConverter;
import com.longdict.vocabulary.data.dto.VocabularyDto;
import com.longdict.vocabulary.data.model.Vocabulary;

public class VocabularyConverter implements DataConverter<Vocabulary, VocabularyDto> {
	
	public static VocabularyConverter create() {
		return new VocabularyConverter();
	}

	@Override
	public VocabularyDto convert(Vocabulary model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vocabulary convert(VocabularyDto dto) {
		// TODO Auto-generated method stub
		return null;
	}


}
