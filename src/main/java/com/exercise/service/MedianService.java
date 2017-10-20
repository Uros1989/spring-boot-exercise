package com.exercise.service;

import static com.exercise.generated.public_.tables.Measurement.MEASUREMENT;
import static com.exercise.generated.public_.tables.Median.MEDIAN;

import java.sql.Timestamp;
import java.util.Collection;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.generated.public_.tables.records.MedianRecord;
import com.exercise.model.Median;

@Service
public class MedianService {

	@Autowired
	private DSLContext create;

	public Collection<Median> getMedians(Long sensorId) {
		return create.selectFrom(MEDIAN).where(MEDIAN.SENSOR_ID.eq(sensorId))
				.fetch(record -> new Median(record.getSensorId(), record.getValue(), record.getTimestamp()));
	}

	public Collection<Median> getMedians(Long sensorId, Long start, Long end) {
		return create.selectFrom(MEDIAN).where(MEDIAN.SENSOR_ID.eq(sensorId))
				.and(MEDIAN.TIMESTAMP.between(new Timestamp(start), new Timestamp(end))).orderBy(MEDIAN.TIMESTAMP.asc())
				.fetch(record -> new Median(record.getSensorId(), record.getValue(), record.getTimestamp()));
	}

	@Transactional
	public void addMedian(Median median) {

		MedianRecord medianRecord = create.newRecord(MEDIAN);
		medianRecord.setSensorId(median.getSensorId());
		medianRecord.setValue(median.getValue());
		medianRecord.setTimestamp(median.getTimestamp());
		medianRecord.store();
	}

}
