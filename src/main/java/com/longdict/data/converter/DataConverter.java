package com.longdict.data.converter;

import com.longdict.data.dto.Dto;
import com.longdict.data.model.Model;

public interface DataConverter<M extends Model, D extends Dto> {
	D convert(M model);
	M convert(D dto);
}
