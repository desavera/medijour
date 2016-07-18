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
public class MngController {
	
    @Autowired
    private JournalsRepository journalsRepo;
    
	final Logger logger = Logger.getLogger(MngController.class); 

    
    @RequestMapping(value="/mng/", method=RequestMethod.POST,
                              consumes = {MediaType.APPLICATION_JSON_VALUE},
                                produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Integer> updateJournalsAvailability(@RequestBody List<Integer> idList) {    	
    	
    	for (Integer id : idList) {


		logger.debug("Updating availability for journals id : " + id + '\n');
        	Journals entity = journalsRepo.findById(id);
        	
        	// just change the availability flag
        	entity.setAvailable(!entity.isAvailable());        	        
        	journalsRepo.save(entity);

    	}    	

	return idList;
    } 
}
