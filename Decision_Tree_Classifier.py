import numpy as np
import pandas as pd
import csv
from sklearn.cross_validation import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from sklearn import tree

#with open('chess2.csv', 'rb') as csvfile:
    #balance_data = csv.reader(csvfile, delimiter=' ', quotechar='|')
balance_data = pd.read_csv('chess2.csv',  sep= ',', header= None)


print "Dataset Lenght:: ", len(balance_data)
print "Dataset Shape:: ", balance_data.shape

print "Dataset:: "
print balance_data.head(n=6)

X = balance_data.values[:, 0:35]
Y = balance_data.values[:,36]

X_train, X_test, y_train, y_test = train_test_split( X, Y, test_size = 0.094)

clf_gini = DecisionTreeClassifier(criterion = "gini", min_samples_leaf=2, max_features=35)
print clf_gini.fit(X_train, y_train)

clf_entropy = DecisionTreeClassifier(criterion = "entropy", min_samples_leaf=2)
print clf_entropy.fit(X_train, y_train)

y_pred = clf_gini.predict(X_test)
print y_pred

y_pred_en = clf_entropy.predict(X_test)
print y_pred_en


print "Accuracy is ", accuracy_score(y_test,y_pred)*100

print "Accuracy is ", accuracy_score(y_test,y_pred_en)*100