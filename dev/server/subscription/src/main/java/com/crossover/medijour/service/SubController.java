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
public class SubController {
	
    @Autowired
    private ContentRepository ContentRepo;
    
	final Logger logger = Logger.getLogger(SubController.class); 

    
    @RequestMapping(value="/sub" , method=RequestMethod.POST,
		    		consumes = {MediaType.APPLICATION_JSON_VALUE},
		    		produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Content> findContentsByQueryParam(
    		final @RequestParam(defaultValue = "0", required = false) int page,
     	    final @RequestParam(defaultValue = "10", required = false) int pageSize,
     	    @RequestBody ContentQuery query) {
    	
		logger.debug("Quering Contents for : " + '\n' + query);

		Pageable pager = new PageRequest(page, pageSize);


		//List<Content> matchList = ContentRepo.findByQueryParam(origin, destiny, query.getSeats(),queryDeparture,queryReturning,pager);			

		List<Content> matchList = new Vector();
		return matchList;

    }    

    @RequestMapping(value="/sub/{Content_id}", method=RequestMethod.PUT)
    public Content updateContent(@RequestBody Content Content) {    	
    	Content entity = ContentRepo.findById(Content.getId());
    	
    	entity.update(Content);
    		
    	
        return entity;
    }       
    
    
    @RequestMapping("sub")
    public Page<Content> findAllContents(
    		final @RequestParam(defaultValue = "0", required = false) int page,
     	    final @RequestParam(defaultValue = "10", required = false) int pageSize) {
    	Pageable pager = new PageRequest(page, pageSize);
    	Page<Content> entities = ContentRepo.findAll(pager);
        return entities;
    }
}
