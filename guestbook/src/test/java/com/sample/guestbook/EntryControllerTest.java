package com.sample.guestbook;


import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.guestbook.controller.EntryController;
import com.sample.guestbook.entity.Entry;
import com.sample.guestbook.service.EntryServiceImpl;


@RunWith(SpringRunner.class)
@WebMvcTest(EntryController.class)
@EnableAutoConfiguration(exclude = {
SecurityAutoConfiguration.class,
SecurityFilterAutoConfiguration.class
})

public class EntryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EntryServiceImpl entryServiceMock;
	
	@MockBean
	DataSource securityDataSource;
	
	@Test
	public void entry_list_admin() throws Exception{
		Entry first = new Entry();
        first.setId(1);
        first.setUserId("rajiv");
        first.setTxtEntry("Hello");
 
        Entry second = new Entry();
        second.setId(2);
        second.setUserId("ansh");
        second.setTxtEntry("Hello1");
 
       when(entryServiceMock.findAll()).thenReturn(Arrays.asList(first, second));
 
        mockMvc.perform(get("/list/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("list-entry"))
               // .andExpect(forwardedUrl("/list-entry.html"))
                .andExpect(model().attribute("entries", hasSize(2)))
                .andExpect(model().attribute("entries", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("txtEntry", is("Hello")),
                                hasProperty("userId", is("rajiv"))
                        )
                )))
                .andExpect(model().attribute("entries", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("txtEntry", is("Hello1")),
                                hasProperty("userId", is("ansh"))
                        )
                )));
        
        
        
        verify(entryServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(entryServiceMock);
	}
	
	
	@Test
	@WithMockUser("rajiv")
	public void entry_list_user() throws Exception{
		Entry first = new Entry();
        first.setId(1);
        first.setUserId("rajiv");
        first.setTxtEntry("Rajiv's Comment1");
 
        Entry second = new Entry();
        second.setId(2);
        second.setUserId("rajiv");
        second.setTxtEntry("Rajiv's Comment2");
        
        when(entryServiceMock.findByUserId("rajiv")).thenReturn(Arrays.asList(first, second));
 
        mockMvc.perform(get("/list/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("list-entry"))
                .andExpect(model().attribute("entries", hasSize(2)))
                .andExpect(model().attribute("entries", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("txtEntry", is("Rajiv's Comment1")),
                                hasProperty("userId", is("rajiv"))
                        )
                )))
                .andExpect(model().attribute("entries", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("txtEntry", is("Rajiv's Comment2")),
                                hasProperty("userId", is("rajiv"))
                        )
                )));
        
        
        
        verify(entryServiceMock, times(1)).findByUserId("rajiv");
        verifyNoMoreInteractions(entryServiceMock);
	}

	
	@Test
	@WithMockUser(username = "rajiv", roles={"ADMIN"})
	public void approve_entry() throws Exception{
		
		Entry theEntry = new Entry(1,"rajiv", "Rajiv's Comment1", new byte[0], "", new Date(), 0);
		Optional<Entry> firstOptional = Optional.of(theEntry);
		
		
		if(firstOptional.isPresent()) {}
 
        when(entryServiceMock.findById(1)).thenReturn(firstOptional);
        doNothing().when(entryServiceMock).save(firstOptional.get());
        mockMvc.perform(get("/approve").param("id","1"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));
        verify(entryServiceMock, times(1)).findById(1);
        verify(entryServiceMock, times(1)).save(firstOptional.get());
        verifyNoMoreInteractions(entryServiceMock);
	}
	
	@Test
	@WithMockUser(username = "rajiv", roles={"ADMIN"})
	public void delete_entry() throws Exception{
		
		doNothing().when(entryServiceMock).deleteById(1);
        mockMvc.perform(get("/delete").param("id","1"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));
        
        verify(entryServiceMock, times(1)).deleteById(1);
        verifyNoMoreInteractions(entryServiceMock);
	}
	
	
}
