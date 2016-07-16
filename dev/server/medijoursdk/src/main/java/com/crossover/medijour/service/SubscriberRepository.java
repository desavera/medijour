package com.crossover.medijour.service;

import java.util.Date;
import java.util.List;
import java.util.Comparator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
	List<Subscriber> findByOwner(Integer id,Pageable pages);	
	Page<Subscriber> findAll(Pageable pager);	
}