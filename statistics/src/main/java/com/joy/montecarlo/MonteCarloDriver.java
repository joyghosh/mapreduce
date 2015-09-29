package com.joy.montecarlo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Monte-carlo driver
 * @author joyghosh
 * @version 1.0
 */
public class MonteCarloDriver {

	public static void main(String[] args) throws Exception{
		Configuration config = new Configuration();
		
		int numOfPoints = Integer.parseInt(args[0]);
		int numMapTasks = Integer.parseInt(args[1]);
		float radius = Float.parseFloat(args[2]);
		Path outputDir = new Path(args[3]);
		
		int numOfRecordsPerTask = numOfPoints/numMapTasks;
		
		Job job = new Job(config, "MonteCarloDriver");
		job.setJarByClass(MonteCarloDriver.class);
		
		job.setInputFormatClass(MonteCarloInputFormat.class);
		
		MonteCarloInputFormat.setNumMapTasks(job, numMapTasks);
		MonteCarloInputFormat.setNumRecordsPerTask(job, numOfRecordsPerTask);
		MonteCarloInputFormat.setRadius(job, radius);
		
		job.setMapperClass(MonteCarloMapper.class);
		job.setReducerClass(MonteCarloReducer.class);
		
		TextOutputFormat.setOutputPath(job, outputDir);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BooleanWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 2);
	}

}
