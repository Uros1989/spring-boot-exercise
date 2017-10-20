package com.exercise.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.exercise.model.Measurement;
import com.exercise.model.Median;
import com.exercise.model.Sensor;

@Component
public class SchedulerService {

	@Autowired
	private MedianService medianService;

	@Autowired
	private SensorService sensorService;

	@Autowired
	private MeasurementService measurementService;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	public static final int period = 3600000;

	@Scheduled(fixedRate = period)
	public void runCalculation() {

		long currentTime = System.currentTimeMillis();

		Timestamp startTimestamp = new Timestamp(currentTime - period);
		Timestamp endTimestamp = new Timestamp(currentTime);

		Date date = new Date();
		System.out.println("The time is now {}" + dateFormat.format(date));

		calculateMedians(startTimestamp, endTimestamp);

	}

	private void calculateMedians(Timestamp startTimestamp, Timestamp endTimestamp) {
		Collection<Sensor> sensors = sensorService.getSensors();

		for (Sensor sensor : sensors) {
			calculateForSensor(sensor.getId(), startTimestamp, endTimestamp);

		}
	}

	public void calculateForSensor(Long sensorId, Timestamp start, Timestamp end) {

		Collection<Measurement> sensorMeasurements = measurementService.getMeasurements(sensorId, start, end);
		BigDecimal medianValue;
		if (!sensorMeasurements.isEmpty()) {
			List<BigDecimal> bigDecimalsValues = new ArrayList<>();

			// BigDecimal average = new BigDecimal(0);
			int i = 0;
			for (Measurement measurement : sensorMeasurements) {
				bigDecimalsValues.add(measurement.getValue());
				i++;
			}
			
			Collections.sort(bigDecimalsValues);

			if (i % 2 == 0) {
				medianValue = (bigDecimalsValues.get(i / 2).add(bigDecimalsValues.get(i / 2 - 1)))
						.divide(new BigDecimal(2));

			} else {
				medianValue = bigDecimalsValues.get(i / 2);

			}

		} else {
			medianValue = new BigDecimal(0);
		}

		Median median = new Median(sensorId, medianValue, end);
		medianService.addMedian(median);
	}

}
