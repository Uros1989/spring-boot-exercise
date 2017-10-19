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

import com.exercise.model.Measurement;
import com.exercise.model.Median;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SchedulerServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MeasurementService measurementService;
    
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
     	
    	Measurement m1 = new Measurement(2L, new BigDecimal("1.0000"), new Timestamp(1000L));
    	Measurement m2 = new Measurement(2L, new BigDecimal("2.0000"), new Timestamp(1001L));
    	Measurement m3 = new Measurement(2L, new BigDecimal("3.0000"), new Timestamp(1003L));
    	measurementService.addMeasurement(m1);
    	measurementService.addMeasurement(m2);
    	measurementService.addMeasurement(m3);
    	
    	schedulerService.calculateForSensor(2L, new Timestamp(999L), new Timestamp(10004L));;
    	
    	assertEquals(2, medianService.getMedians(2L).size());
    	assertEquals(new BigDecimal("2.0000"), ((ArrayList<Median>)medianService.getMedians(2L)).get(1).getValue());
    	
    	
    }

}
