# Random Forest Classification
import pandas
from sklearn import model_selection
from sklearn.ensemble import RandomForestClassifier
from sklearn.cross_validation import train_test_split
from sklearn.metrics import accuracy_score


#url = "https://archive.ics.uci.edu/ml/machine-learning-databases/pima-indians-diabetes/pima-indians-diabetes.data"
#names = ['bkblk','bknwy','bkon8','bkona','bkspr','bkxbq','bkxcr','bkxwp','blxwp','bxqsq','cntxt','dsopp','dwipd','hdchk','katri',
#         'mulch','qxmsq','r2ar8','reskd','reskr','rimmx','rkxwp','rxmsq','simpl','skach','skewr','skrxp','spcop','stlmt','thrsk',
#         'wkcti','wkna8','wknck','wkovl','wkpos','wtoeg','class']
dataframe = pandas.read_csv('chess2.csv',  sep= ',', header= None)

print "Dataset Lenght:: ", len(dataframe)
print "Dataset Shape:: ", dataframe.shape

print "Dataset:: "
print dataframe.head(n=6)

array = dataframe.values
X = array[:,0:35]
Y = array[:,36]
num_trees = 10
max_features = 35
X_train, X_test, y_train, y_test = train_test_split( X, Y, test_size = 0.094)
#kfold = model_selection.KFold(n_splits=10)
#kfold = model_selection.KFold(test_size = 0.094)
model = RandomForestClassifier(n_estimators=num_trees, max_features=max_features, min_samples_leaf=1)
#results = model_selection.cross_val_score(model, X, Y, cv=kfold)
model.fit(X_train, y_train)
y_pred = model.predict(X_test)
results = accuracy_score(y_test,y_pred)*100
print(results.mean())