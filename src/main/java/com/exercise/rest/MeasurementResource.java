package com.exercise.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exercise.model.Measurement;
import com.exercise.service.MeasurementService;

@RequestMapping("/measurements")
@Controller
public class MeasurementResource {
	
    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity getMeasurements(@PathVariable("id") long id) {
        Collection<Measurement> measurements = measurementService.getMeasurements(id);
        return ResponseEntity.ok(measurements);
    }
    
    @PostMapping
    ResponseEntity addMeasurement(@RequestBody Measurement measurement) {
    	measurementService.addMeasurement(measurement);
        return ResponseEntity.ok().build();
    }
	
}
