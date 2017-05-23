import numpy as np
import pandas as pd
import csv
from sklearn import svm
from numpy import genfromtxt

X = genfromtxt('C:\Users\Devanshu\OneDrive\Data Mining\HW2\\bigtest.csv', delimiter=',')
'''
with open('C:\Users\Devanshu\OneDrive\Data Mining\HW2\\gene.csv') as csvFile:
    reader = csv.reader(csvFile)
    M = reader.next()
'''
A = np.ones((176),dtype=int)
B = np.ones((188),dtype=int)
B = - B
Y = np.concatenate((A,B))
clf = svm.SVC(kernel= 'poly',degree=2,C=1)
clf.fit(X,Y)

#weights = clf.dual_coef_[0]
sup_vec = clf.support_vectors_
'''
w = np.zeros((2,len(clf.dual_coef_[0])),dtype=float)

for i in range(0,len(clf.dual_coef_[0])):
	w[0][i]=i
	w[1][i]=clf.dual_coef_[0][i]

t = sorted(zip(clf.dual_coef_[0], M), reverse=True)

for i in range(0,100):
	print t[i]
'''