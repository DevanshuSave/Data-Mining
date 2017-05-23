import numpy as np
import pandas as pd
import csv
import sys
import re
import time

from sklearn.preprocessing import Imputer
from itertools import islice

dataframe = 'onlydata.csv'

with open(dataframe, 'rb') as f:
	myfile = csv.reader(f, delimiter=',', quotechar='|')
	temp = list(myfile)
	myfilelen = len(temp)
	print myfilelen
	'''for i,line in enumerate(myfile):
		print 'line[{}] = {}'.format(i, line)
		#print i
		#for j in range (i,len(myfile)):'''

from scipy import spatial
for i in range (0,myfilelen):
	for j in range (i+1,myfilelen):
		dataSetI = np.array(temp[i],dtype=float)
		dataSetII = np.array(temp[j],dtype=float)
		result = 1 - spatial.distance.cosine(dataSetI, dataSetII)
		#print i,j,"---",result
print "Done"