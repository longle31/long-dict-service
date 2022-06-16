package com.longdict.vocabulary.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pronunciation")
public class Pronunciation {
	@Id
	private String ipa;
	
	@Column(columnDefinition = "TEXT")
	private String voices;
	
	public String getIpa() {
		return ipa;
	}

	public void setIpa(String ipa) {
		this.ipa = ipa;
	}

	public String getVoices() {
		return voices;
	}

	public void setVoices(String voices) {
		this.voices = voices;
	}

	public Pronunciation() {
	}
}
