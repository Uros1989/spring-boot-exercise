package com.exercise.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exercise.model.Median;
import com.exercise.service.MedianService;

@RequestMapping("/medians")
@Controller
public class MedianResource {
	
    @Autowired
    private MedianService medianService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getMedians(@PathVariable("id") long id) {
        Collection<Median> medians = medianService.getMedians(id);
        return ResponseEntity.ok(medians);
    }
    
/*    @PostMapping
    ResponseEntity addMeasurement(@RequestBody Measurement measurement) {
    	measurementService.addMeasurement(measurement);
        return ResponseEntity.ok().build();
    }*/

	
}
