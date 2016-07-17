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
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ListingController {
	
    @Autowired
    private JournalsRepository journalsRepo;
    
	final Logger logger = Logger.getLogger(ListingController.class); 

    
    @RequestMapping(value="/listing" , method=RequestMethod.POST,
		    		consumes = {MediaType.APPLICATION_JSON_VALUE},
		    		produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Journals> findContentsByQueryParam(
    		final @RequestParam(defaultValue = "0", required = false) int page,
     	    final @RequestParam(defaultValue = "10", required = false) int pageSize,
     	    @RequestBody JournalsQuery query) {
    	
		logger.debug("Quering Contents for : " + '\n' + query);

		Pageable pager = new PageRequest(page, pageSize);


		//List<Content> matchList = journalsRepo.findByQueryParam(origin, destiny, query.getSeats(),queryDeparture,queryReturning,pager);			

		List<Journals> matchList = new Vector();
		return matchList;

    }    
    
    @RequestMapping("/listing")
    public Page<Journals> findAllContents(
    		final @RequestParam(defaultValue = "0", required = false) int page,
     	    final @RequestParam(defaultValue = "10", required = false) int pageSize) {
    	Pageable pager = new PageRequest(page, pageSize);
    	Page<Journals> entities = journalsRepo.findAll(pager);
        return entities;
    }
}
