import csv
import sys
import numpy as np
import pandas
import re
from sklearn import tree
from sklearn.ensemble import RandomForestClassifier
from sklearn import model_selection
from sklearn.cross_validation import train_test_split
from sklearn.metrics import accuracy_score
from sklearn import metrics
from sklearn.cluster import KMeans
from sklearn.datasets import load_digits
from sklearn.decomposition import PCA
from sklearn.preprocessing import scale
from sklearn.metrics.pairwise import euclidean_distances

data = pandas.read_csv('onlydata.csv',  sep= ',')


# = pandas.read_csv('testing.csv',  sep= ',', header= None)


data_val = data.values
param_values = data_val[:, 0:8559]

kmeans = KMeans(n_clusters=2)
kmeans.fit(param_values)

# To find the cluster centers and then copy it to realcenters followed by the cluster number
centers = kmeans.cluster_centers_
realcenters =[]
for i in range(0,len(centers)):
    realcenters.append((i,centers[i]))

print realcenters
#To get the labels of each point followed by the label
a = kmeans.labels_ 
labels = []
for i in range(0,len(a)):
    labels.append((i+1,a[i]))

# to calculate the cluster sizes
b=[]
for i in range(0,100):
    count = 0
    for j in a:
        if j == i:
            count+=1
    b.append((i,count))

#to find the exact cluster concerned.    
for i in range(0,len(b)):
    if b[i][1] != 0:
        print str(b[i][0])+' '+str(b[i][1])
        
        
# for picking any row
dataframe = 'onlydata.csv'
with open(dataframe, 'rb') as f:
    myfile = csv.reader(f, delimiter=',', quotechar='|')
    temp = list(myfile)
    print len(temp)


# calculating the average in cluster similarity
inclusim = []
k=2
for i in range(0,k):
    inclusim = []
    for j in range(0,len(labels)):
        m = j+1
        if labels[m][1] == i:
            dataset = np.array(temp[m], dtype=float)
            print j
            sim=euclidean_distances(dataset,realcenters[i][1])
