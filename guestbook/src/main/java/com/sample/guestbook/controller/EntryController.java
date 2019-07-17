package com.sample.guestbook.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.guestbook.entity.Entry;
import com.sample.guestbook.service.EntryService;

/**
 * @Controller class to process all GUI request and return response view. 
 *
 */
@Controller
public class EntryController {

	
	private EntryService entryService;
	
	@Autowired
	public EntryController(EntryService theEntryService) {
		   
		entryService = theEntryService;
	}
	
	/**
	 * @param theModel
	 * @return ModeView
	 * 
	 * method to return home context, depending upon the role of logged user.
	 *  he will be redirected to admin page or user page.
	 */
	@GetMapping("/")
	public String home(Model theModel) {
		
		//to check if the user has a role ROLE_ADMIN
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasAdminRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		
		if(hasAdminRole) return "redirect:/list/admin";
		
		return "redirect:/list/user";
	}
	
	@GetMapping("/list/user")
	public String listEntryUser(Model theModel) {
		
		//get the logged user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String loggedUser = auth.getName();
		
		// get entries from db
		List<Entry> theEntries = entryService.findByUserId(loggedUser);
		
		// add to the spring model
		theModel.addAttribute("entries", theEntries);
				
		return "list-entry";
	}
	
	@GetMapping("/list/admin")
	public String listEntryAdmin(Model theModel) {
		
		// get entries from db
		List<Entry> theEntries = entryService.findAll();
		
		// add to the spring model
		theModel.addAttribute("entries", theEntries);
	
		return "list-entry";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
	
		return "entry-form";
	}
	
	
	@PostMapping("/save") 
    public String singleFileUpload(@RequestParam("file") MultipartFile imgFile,
    							   @RequestParam("entry") String txtEntry,
                                   RedirectAttributes redirectAttributes) {

		byte[] imgBytes = null;

		String imgType = null;
		
		//reading user detail from security context holder
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String loggedUser = auth.getName();
		
		
        try {

        	if (!imgFile.isEmpty()) {
        		//get img type
        		double bytes = imgFile.getBytes().length;
    			double kb = (bytes / 1024);
        		if(kb > 500) {
        			redirectAttributes.addFlashAttribute("message",
        	                "Image size is bigger than 500Kb  : '" + imgFile.getOriginalFilename() + "'");
        	        
        	        return "redirect:/uploadStatus";
        		}
        		imgType = imgFile.getOriginalFilename().split("\\.")[1];
        		if(imgType.equalsIgnoreCase("jpg") || imgType.equalsIgnoreCase("png") || imgType.equalsIgnoreCase("jpeg")) {
        			imgBytes = imgFile.getBytes();
        			//imgString = Base64.getEncoder().encodeToString(imgBytes);
        		}else {
        			redirectAttributes.addFlashAttribute("message", "Please select a file type (jpg/jpeg/png) to upload");
        			return "redirect:uploadStatus";
        		}
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Date lastUpdate = new Date();
        Entry theEntry = new Entry(loggedUser, txtEntry, imgBytes,imgType, lastUpdate, 0);
        
        entryService.save(theEntry);
        
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + imgFile.getOriginalFilename() + "'");
        
        return "redirect:/uploadStatus";
    }
	
	@GetMapping("/approve")
	public String approveEntry(@RequestParam("id") int id) {
		
		//get the entry for id from db.
		Optional<Entry> theEntry = entryService.findById(id);
		
		//mark entry as approved.
		if (theEntry.isPresent()){
		    Entry entry = theEntry.get();
		    entry.setIsApproved(1);
		    entryService.save(entry);
		}
		
		//return to list page
		
		return "redirect:/";
	}
	
	
	@GetMapping("/delete")
	public String deleteEntry(@RequestParam("id") int id) {
		
		//delete the entry for id from db.
		entryService.deleteById(id);
		
		return "redirect:/";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {

		return "entry-form";
		
	}
}
