package com.exercise.service;

import static com.exercise.generated.public_.tables.Measurement.MEASUREMENT;

import java.sql.Timestamp;
import java.util.Collection;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.generated.public_.tables.records.MeasurementRecord;
import com.exercise.model.Measurement;

@Service
public class MeasurementService {

	@Autowired
	private DSLContext create;

	public Collection<Measurement> getMeasurements(Long sensorId) {
		return create.selectFrom(MEASUREMENT).where(MEASUREMENT.SENSOR_ID.eq(sensorId))
				.fetch(record -> new Measurement(record.getSensorId(), record.getValue(), record.getTimestamp()));
	}

	public Collection<Measurement> getMeasurements(Long sensorId, Timestamp start, Timestamp end) {
		return create.selectFrom(MEASUREMENT).where(MEASUREMENT.SENSOR_ID.eq(sensorId))
				.and(MEASUREMENT.TIMESTAMP.between(start, end)).orderBy(MEASUREMENT.TIMESTAMP.asc())
				.fetch(record -> new Measurement(record.getSensorId(), record.getValue(), record.getTimestamp()));
	}

	@Transactional
	public void addMeasurement(Measurement measurement) {
		MeasurementRecord measurementRecord = create.newRecord(MEASUREMENT);
		measurementRecord.setSensorId(measurement.getSensorId());
		measurementRecord.setValue(measurement.getValue());
		measurementRecord.setTimestamp(measurement.getTimestamp());
		measurementRecord.store();
	}

}
