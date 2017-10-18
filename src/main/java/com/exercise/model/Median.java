package com.exercise.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Median {
    
    private BigDecimal value;
    
    private Timestamp timestamp;

    private Long sensorId;
    
    public Median(){
    	
    }
    
	public Median(Long sensorId, BigDecimal value, Timestamp timestamp) {
		super();
		this.sensorId = sensorId;
		this.value = value;
		this.timestamp = timestamp;
	}
	
	public Median(BigDecimal value, Timestamp timestamp) {
		super();
		this.value = value;
		this.timestamp = timestamp;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public Long getSensorId() {
		return sensorId;
	}

    

}
