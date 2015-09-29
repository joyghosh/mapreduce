package com.joy.montecarlo;

import java.io.IOException;

import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MonteCarloMapper extends Mapper<Text, FloatWritable, 
						Text, BooleanWritable>{

	@Override
	protected void map(Text key, FloatWritable value,Context context)
			throws IOException, InterruptedException {
		
		String[] point = (key.toString()).split(",");
		float x = Float.parseFloat(point[0]);
		float y = Float.parseFloat(point[1]);
		float radius = value.get();
		
		if(Math.pow(x, 2) + Math.pow(y, 2) < Math.pow(radius, 2)){
			context.write(new Text("MONTE-CARLO"), new BooleanWritable(true));
		}else{
			context.write(new Text("MONTE-CARLO"), new BooleanWritable(false));
		}
	}
	
}
