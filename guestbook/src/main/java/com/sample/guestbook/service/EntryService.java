package com.sample.guestbook.service;

import java.util.List;
import java.util.Optional;

import com.sample.guestbook.entity.Entry;

/**
 * Entity service interface, all guestbook operation supported are listed/declared here. 
 */
public interface EntryService {
				 
	public List<Entry> findAll();
	
	public Optional<Entry> findById(int theId);
	
	public List<Entry> findByUserId(String theUserId);
	
	public void save(Entry theEntry);
	
	public void deleteById(int theId);
}
