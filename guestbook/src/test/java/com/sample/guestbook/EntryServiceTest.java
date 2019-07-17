package com.sample.guestbook;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.sample.guestbook.dao.EntryRepository;
import com.sample.guestbook.entity.Entry;
import com.sample.guestbook.service.EntryServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EntryServiceTest {

	
	@InjectMocks
	private EntryServiceImpl business;

	@Mock
	private EntryRepository repository;

	@Test
	public void retrieveAllEntry() {
		
		Entry first = new Entry();
        first.setId(1);
        first.setUserId("rajiv");
        first.setTxtEntry("Hello");
 
        Entry second = new Entry();
        second.setId(2);
        second.setUserId("ansh");
        second.setTxtEntry("Hello1");
        
		when(repository.findAllByOrderByLastUpdateAsc()).thenReturn(Arrays.asList(first, second));
		List<Entry> enteries = business.findAll();
		assertEquals(2, enteries.size());
		assertEquals(1, enteries.get(0).getId());
		assertEquals("rajiv", enteries.get(0).getUserId());
		assertEquals("Hello", enteries.get(0).getTxtEntry());
		assertEquals(2, enteries.get(1).getId());
		assertEquals("ansh", enteries.get(1).getUserId());
		assertEquals("Hello1", enteries.get(1).getTxtEntry());
	}
	@Test
	public void retrieveEntryById() {
		
		Entry first = new Entry();
        first.setId(1);
        first.setUserId("rajiv");
        first.setTxtEntry("Hello");
 
        Entry second = new Entry();
        second.setId(2);
        second.setUserId("ansh");
        second.setTxtEntry("Hello1");
        
		when(repository.findAllByUserId("rajiv")).thenReturn(Arrays.asList(first, second));
		List<Entry> enteries = business.findByUserId("rajiv");
		
		assertEquals(1, enteries.get(0).getId());
		assertEquals("rajiv", enteries.get(0).getUserId());
		assertEquals("Hello", enteries.get(0).getTxtEntry());
		assertEquals(2, enteries.get(1).getId());
		assertEquals("ansh", enteries.get(1).getUserId());
		assertEquals("Hello1", enteries.get(1).getTxtEntry());
	}
}
