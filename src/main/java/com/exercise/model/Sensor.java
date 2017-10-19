package com.exercise.model;

public class Sensor {

	private String sensorId;

	private Long id;

	public Sensor() {
	}

	public Sensor(String sensorId) {
		this.sensorId = sensorId;
	}

	public Sensor(Long id, String sensorId) {
		this.id = id;
		this.sensorId = sensorId;
	}

	public String getSensorId() {
		return sensorId;
	}

	public Long getId() {
		return id;
	}

}
