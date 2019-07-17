package com.sample.guestbook.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.guestbook.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

	//a method to sort by timestamp
	public List<Entry> findAllByOrderByLastUpdateAsc();
	
	@Query("select e from Entry e where e.userId = ?1")
	public List<Entry> findAllByUserId(String userId);
	
}
