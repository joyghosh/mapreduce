package com.joy.montecarlo;

import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class MonteCarloRecordReader extends RecordReader<Text, FloatWritable> {

	private int numRecordsToCreate = 0;
	private int createdRecords = 0;
	private Text key = new Text();
	private FloatWritable value;
	private Random random = new Random();
	
	@Override
	public void close() throws IOException {}

	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		return key;
	}

	@Override
	public FloatWritable getCurrentValue() throws IOException,
			InterruptedException {
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		return (float) createdRecords / (float) numRecordsToCreate;
	}

	@Override
	public void initialize(InputSplit split, TaskAttemptContext context)
			throws IOException, InterruptedException {
		this.numRecordsToCreate = context.getConfiguration()
									.getInt(MonteCarloInputFormat.NUM_OF_RECORDS_PER_TASK, -1);
		this.value = new FloatWritable(context
										.getConfiguration()
										.getFloat(MonteCarloInputFormat.RADIUS, 1));
		
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if(createdRecords < numRecordsToCreate){
			
			float x = random.nextFloat();
			float y = random.nextFloat();
			
			String randomPoint = x+","+y;
			key.set(randomPoint);
			return true;
		}
	
		return false;
	}

}
