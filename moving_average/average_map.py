#!/usr/bin/env python
'''
Created on 09-Aug-2015

@author: joyghosh
'''

import sys

#input from STDIN.
for line in sys.stdin:
    #remove leading and trailing spaces.
    line = line.strip()
    #split the fields in CSV.
    data = line.split(',')
    #Emit the stock closing price values using year as key.
    print '%s\t%s' % (data[0],data[4])
