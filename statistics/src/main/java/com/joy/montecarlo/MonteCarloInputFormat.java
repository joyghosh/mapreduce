package com.joy.montecarlo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * Monte-Carlo InputFormat
 * @author joyghosh
 * @version 1.0
 */

public class MonteCarloInputFormat extends InputFormat<Text, FloatWritable> {

	public static final String NUM_OF_MAP_TASKS = "montecarlo.generator.map.tasks";
	public static final String NUM_OF_RECORDS_PER_TASK = "montecarlo.generator.num.records.map.task";
	public static final String RADIUS = "montecarlo.generator.radius";
	
	@Override
	public RecordReader<Text, FloatWritable> createRecordReader(InputSplit split,
			TaskAttemptContext context) throws IOException, InterruptedException {
		MonteCarloRecordReader mcrr = new MonteCarloRecordReader();
		mcrr.initialize(split, context);
		return mcrr;
	}

	@Override
	public List<InputSplit> getSplits(JobContext job) throws IOException,
			InterruptedException {
		
		//Get the number of map tasks configured for.
		int totalSplits = job.getConfiguration().getInt(NUM_OF_MAP_TASKS, -1);
		
		//Create number of input splits equivalent to number of map tasks.
		List<InputSplit> splits = new ArrayList<InputSplit>();
		for(int i = 0; i < totalSplits; i++){
			splits.add(new DummyInputSplit());
		}
		
		return splits;
	}

	public static void setNumMapTasks(Job job, int numOfTasks){
		job.getConfiguration().setInt(NUM_OF_MAP_TASKS, numOfTasks);
	}
	
	public static void setNumRecordsPerTask(Job job, int numRecordsPerTask){
		job.getConfiguration().setInt(NUM_OF_RECORDS_PER_TASK, numRecordsPerTask);
	}
	
	public static void setRadius(Job job, float radius){
		job.getConfiguration().setFloat(RADIUS, radius);
	}
}
