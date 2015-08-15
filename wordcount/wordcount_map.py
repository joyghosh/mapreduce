'''
Created on 03-Aug-2015

@author: joy
'''

import sys

#input from STDIN
for line in sys.stdin:
    #remove leading and trailing spaces.
    line = line.strip()
    #split the lines into words.
    words = line.split()
    #increase the counters.
    for word in words:
        # write the results to STDOUT (standard output);
        # what we output here will be the input for the
        # Reduce step, i.e. the input for reducer.py
        #
        # tab-delimited; the trivial word count is 1
        print '%s\t%s' % (word,1)
