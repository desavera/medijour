package com.crossover.medijour.service;

import java.util.Date;
import java.util.List;
import java.util.Comparator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
	Content findById(Integer id);
	Page<Content> findAll(Pageable pager);
		              
	//
	@Query("SELECT s FROM Schedule s WHERE (s.origin.id=:#{#origin.id} OR s.origin.id=:#{#destiny.id}) AND (s.numOfSeats > :#{#nseats}) AND (s.departureTime between :#{#departureTime} and :#{#returnTime})")	
	List<Content> findByQueryParam(	@Param("nseats") Integer nseats,
									@Param("departureTime") Date departureTime,
									@Param("returnTime") Date returnTime,											
									Pageable pager);
	

	

}