package com.longdict.vocabulary.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.longdict.data.model.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vocabulary")
@Getter
@Setter
@Builder
public class Vocabulary implements Model{	
	private static final long serialVersionUID = 1581838348490214976L;

	@Id
	private String word;

	@OneToMany(cascade = CascadeType.PERSIST)
	private Set<Pronunciation> pronunciations = new HashSet<>(); 
	
	@Column(columnDefinition = "TEXT")
	private String meaninngs;
	
	@Column(columnDefinition = "TEXT")
	private String examples;

	@Column(columnDefinition = "TEXT")
	private String extraVocabulary;
	
	@Column(columnDefinition = "TEXT")
	private String relatedVocabulary;	
	
	public Vocabulary() {
	}

	private Vocabulary(String word, Set<Pronunciation> pronunciations, String meaninngs, String examples,
			String extraVocabulary, String relatedVocabulary) {
		this.word = word;
		this.pronunciations = pronunciations;
		this.meaninngs = meaninngs;
		this.examples = examples;
		this.extraVocabulary = extraVocabulary;
		this.relatedVocabulary = relatedVocabulary;
	}
	
	
}
