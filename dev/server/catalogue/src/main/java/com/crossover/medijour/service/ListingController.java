package com.crossover.medijour.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.medijour.service.*;


@RestController
public class ListingController {
	
    @Autowired
    private JournalsRepository journalsRepo;
    
    @Autowired
    private PublisherRepository publisherRepo;
    
    
    
	final Logger logger = Logger.getLogger(ListingController.class); 

        
    @RequestMapping(value="/listing" , method=RequestMethod.GET,
    		 consumes = {MediaType.ALL_VALUE},
             produces = MediaType.APPLICATION_JSON_VALUE)		    		
    public Page<Journals> findJournalsForPublicUsers(
    		final @RequestParam(defaultValue = "0", required = false) int page,
     	    final @RequestParam(defaultValue = "10", required = false) int pageSize) {
    	
		logger.debug("Quering public users journals..." + '\n');

		Pageable pager = new PageRequest(page, pageSize);

		Page<Journals> matchList = journalsRepo.findAll(pager);			

		return matchList;

    }
    
    @RequestMapping(value="/listing/{provunq}" , method=RequestMethod.GET,
   		 consumes = {MediaType.ALL_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)		    		
   public Page<Journals> findJournalsForPublisher(
   		final @RequestParam(defaultValue = "0", required = false) int page,
    	    final @RequestParam(defaultValue = "10", required = false) int pageSize,
    	    @PathVariable(value="provunq") String provunq) {
   	
		logger.debug("Quering journals for publisher with id : " + provunq + '\n');

		Pageable pager = new PageRequest(page, pageSize);
		
		Publisher publisher = publisherRepo.findByProvunq(provunq);

		Page<Journals> matchList = journalsRepo.findByPublisher(publisher,pager);			

		return matchList;

   }             

}
