#!/usr/bin/env python
'''
Created on 09-Aug-2015

@author: joyghosh
'''

import sys

current_year = None
year = None
days = 0
current_sum = 0;

#input from STDIN
for line in sys.stdin:
    #remove leading and trailing whitespace
    line = line.strip()
    
    #parse the year and closing stock price from map step.
    year, closing_price = line.split('\t', 1)
    
    #get the year component.
    year = year.split('-')[:1]
    
    #convert closing price currently a string to float value.
    try:
        closing_price = float(closing_price)
    except ValueError:
        # count was not a number, so silently
        # ignore/discard this line
        continue
    
    if current_year == year:
        current_sum = current_sum + closing_price
        days = days + 1;
    else:
        if current_year:
            average = current_sum/days
            # write result to STDOUT
            print '%s\t%s' % (current_year, average)
        current_year = year
        current_sum = closing_price
	days = 1

# print the last year
avg = current_sum/days
print '%s\t%s' % (current_year, avg)
