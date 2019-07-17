package com.sample.guestbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.guestbook.dao.EntryRepository;
import com.sample.guestbook.entity.Entry;

/**
 * Service implementation class  
 *It implements all operation supported by guestbook application.
 */
@Service
public class EntryServiceImpl implements EntryService {

	
	private EntryRepository entryRepository;
	
	@Autowired
	public EntryServiceImpl(EntryRepository theEntryRepository) {
		entryRepository = theEntryRepository;
	}
	
	@Override
	public List<Entry> findAll() {
		
		return entryRepository.findAllByOrderByLastUpdateAsc();
	}

	@Override
	public Optional<Entry> findById(int theId) {
		return entryRepository.findById(theId);
	}

	@Override
	public void save(Entry theEntry) {
		entryRepository.save(theEntry);

	}

	@Override
	public void deleteById(int theId) {
		entryRepository.deleteById(theId);

	}

	@Override
	public List<Entry> findByUserId(String theUserId) {
		return entryRepository.findAllByUserId(theUserId);
	}

}
