#!/usr/bin/env python
'''
Created on 09-Aug-2015

@author: joyghosh
'''

import sys

N = 3

for line in sys.stdin:
    line = line.strip()
    
    (year, total) = line.split('\t')
    total = float(total)
    moving_averge = total/N;
    
    print '%s\t%s' % (year, moving_averge)
