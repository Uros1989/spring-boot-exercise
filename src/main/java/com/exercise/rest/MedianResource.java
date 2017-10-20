package com.exercise.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exercise.model.Median;
import com.exercise.service.MedianService;

@RequestMapping("/medians")
@Controller
public class MedianResource {

	@Autowired
	private MedianService medianService;

	@RequestMapping
	ResponseEntity getMedians(@RequestParam("id") long id, @RequestParam("start") long start,
			@RequestParam("end") long end) {
		Collection<Median> medians = medianService.getMedians(id, start, end);
		return ResponseEntity.ok(medians);
	}

}
