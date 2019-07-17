package com.sample.guestbook;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.guestbook.dao.EntryRepository;
import com.sample.guestbook.entity.Entry;

import org.mockito.InjectMocks;

//@AutoConfigureTestDatabase
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class EntryRepositoryTest {
//	
//	@InjectMocks
//	private EntryRepository repository;
//	
//	@Test
//	public void findAllEntry() {
//		Entry first = new Entry();
//        first.setId(1);
//        first.setUserId("rajiv");
//        first.setTxtEntry("Hello");
// 
//        Entry second = new Entry();
//        second.setId(2);
//        second.setUserId("ansh");
//        second.setTxtEntry("Hello1");
//        
//		List<Entry> entry = repository.findAllByOrderByLastUpdateAsc();
//		assertEquals(2,entry.size());
//	}
//
//	@Test
//	public void findAllEntryByUserId() {
//		Entry first = new Entry();
//        first.setId(1);
//        first.setUserId("rajiv");
//        first.setTxtEntry("Hello");
// 
//        Entry second = new Entry();
//        second.setId(2);
//        second.setUserId("ansh");
//        second.setTxtEntry("Hello1");
//        
//        List<Entry> entry = repository.findAllByUserId("rajiv");
//		
//		assertEquals(2,entry.size());
//	}
//
//}
