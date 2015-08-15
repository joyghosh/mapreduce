#!/usr/bin/env python
'''
Created on 09-Aug-2015

@author: joyghosh
'''

import sys

total = 0
year = None
value = 0

data = sys.stdin.readlines()
for i in range(0,len(data)):
    #remove leading and trailing spaces.
    line = (data[i]).strip()
    (year, value) = line.split('\t',1)
    total = float(value)
    
    if(((i+1) < len(data)) & ((i+2) < len(data))):
        line = (data[i+1]).strip()  
        (year, value) = line.split('\t',1)
        total = total + float(value)
        
        line = (data[i+2]).strip()  
        (year, value) = line.split('\t',1)
        total = total + float(value)
        
        line = (data[i+1]).strip()
        year = line.split('\t')[:1]
        
        print '%s\t%s' % (year, total)
