import numpy as np
import pandas
import csv
import sys
import re
import  time

from sklearn.preprocessing import Imputer
from itertools import islice

#dataframe = pandas.read_csv('ctrl2.csv',  sep= ',', header= None)
dataframe = 'ctrl2.csv'
#print "Dataset Lenght:: ", len(dataframe)
#print "Dataset Shape:: ", dataframe.shape

#print "Dataset:: "
#print dataframe.head(n=6)

with open(dataframe, 'rb') as csvfile_r:
       #with open('ctri2_new.csv', 'w') as csvfile_w:
    spamreader = csv.reader(csvfile_r, delimiter=',', quotechar='|')
       #writer = csv.DictWriter(csvfile_w, fieldnames=fieldnames)
    
    k = 0;
    s = 0;
    j = 0;
    mean = 0;
    columns = len(spamreader.next())
    '''
    for row in spamreader:
        Tid=row[1]
        if(Tid != 'NA' and Tid != ''):
           s  += float(Tid);
           #print Tid;
           j +=1;
     #print j;
    mean = s / j;
    print mean, "\n"; 
    ''' 
    for i in range(1, columns-1):
        print i
        s = 0;
        j = 0;
        mean = 0;
        for row in islice(spamreader, 1, None):
           Tid=row[i]
           if(Tid != 'NA' and Tid != ''):
               s  += float(Tid);
               #print Tid;
               j +=1;
        #print j;
        mean = s / j;
        print mean, "\n";



        
#array = dataframe.values

#X = np.array(array)

#print X

#imp = Imputer(missing_values='NA', strategy='mean', axis=0)
#X = imp.fit_transform(X)

print "Done"
