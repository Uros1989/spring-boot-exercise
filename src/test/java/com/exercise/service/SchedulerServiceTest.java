package com.exercise.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.exercise.model.Median;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SchedulerServiceTest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private SchedulerService schedulerService;
    
    @Autowired
    private MedianService medianService;
    
    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void should_calculate_medians() {
     	
    	
    	schedulerService.calculateForSensor(2L, new Timestamp(639983431121L), new Timestamp(645254431125L));;
    	
    	assertEquals(1, medianService.getMedians(2L, 639983431120L, 645254431126L).size());
    	assertEquals(new BigDecimal("28.435"), ((ArrayList<Median>)medianService.getMedians(2L, 639983431120L, 645254431126L)).get(0).getValue());
    	
    	
    }

}
