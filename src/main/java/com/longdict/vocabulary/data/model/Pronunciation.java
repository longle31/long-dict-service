package com.longdict.vocabulary.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "pronunciation")
@Data
public class Pronunciation {
	@Id
	private String ipa;
	
	@Column(columnDefinition = "TEXT")
	private String voices;
	
}
