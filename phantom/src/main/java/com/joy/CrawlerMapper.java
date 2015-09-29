package com.joy;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CrawlerMapper 
		extends Mapper<LongWritable, Text, Text, IntWritable> {

}
