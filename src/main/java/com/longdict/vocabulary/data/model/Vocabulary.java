package com.longdict.vocabulary.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.longdict.data.model.Model;

@Entity
@Table(name = "vocabulary")
public class Vocabulary implements Model{	
	private static final long serialVersionUID = 1581838348490214976L;

	@Id
	private String word;

	@OneToMany
	private Set<Pronunciation> pronunciations = new HashSet<>(); 
	
	@Column(columnDefinition = "TEXT")
	private String meaninngs;
	
	@Column(columnDefinition = "TEXT")
	private String  relatedVocabulary;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Set<Pronunciation> getPronunciations() {
		return pronunciations;
	}

	public void setPronunciations(Set<Pronunciation> pronunciations) {
		this.pronunciations = pronunciations;
	}

	public String getMeaninngs() {
		return meaninngs;
	}

	public void setMeaninngs(String meaninngs) {
		this.meaninngs = meaninngs;
	}

	public String getRelatedVocabulary() {
		return relatedVocabulary;
	}

	public void setRelatedVocabulary(String relatedVocabulary) {
		this.relatedVocabulary = relatedVocabulary;
	}

	public Vocabulary() {
		super();
	}
	
	
}
