package com.joy.montecarlo;

import java.io.IOException;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Monte-carlo reducer implementation.
 * @author joyghosh
 * @version 1.0
 *
 */
public class MonteCarloReducer extends Reducer<Text, BooleanWritable, 
							Text, DoubleWritable> {

	private static int TOTAL_NO_POINTS = 0;
	private static int TOTAL_POINTS_INSIDE_CIRCLE = 0;
	/***
	 * reduce function estimates the value of PI.
	 */
	@Override
	protected void reduce(Text key, Iterable<BooleanWritable> values,
				Context context) throws IOException, InterruptedException {
		
		if(key.toString().equalsIgnoreCase("monte-carlo")){
			
			MonteCarloReducer.TOTAL_NO_POINTS = MonteCarloReducer.TOTAL_NO_POINTS + 1;
			for(BooleanWritable value : values){
				if(value.get()){
					MonteCarloReducer.TOTAL_POINTS_INSIDE_CIRCLE = MonteCarloReducer.TOTAL_POINTS_INSIDE_CIRCLE + 1;
				}
			}
		}
		
		//Estimate the value of PI and write it to the output file.
		float pi = 4*(TOTAL_POINTS_INSIDE_CIRCLE/TOTAL_NO_POINTS);
		context.write(new Text("Value of PI:"), new DoubleWritable(pi));
	}
	
}
