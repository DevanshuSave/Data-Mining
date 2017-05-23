import csv
from collections import Counter

column_totals = Counter()
with open('C:\Users\Devanshu\OneDrive\Data Mining\HW1\ctrl2.csv',"rb") as f:
    reader = csv.reader(f)
    row_count = 0.0
    for row in reader:
        for column_idx, column_value in enumerate(row):
            try:
                n = float(column_value)
                column_totals[column_idx] += n
            except ValueError:
                a=0
                #print "Error -- ({}) Column({}) could not be converted to float!".format(column_value, column_idx)                    
        row_count += 1.0            

# row_count is now 1 too many so decrement it back down
row_count -= 1.0

# make sure column index keys are in order
column_indexes = column_totals.keys()
column_indexes.sort()

# calculate per column averages using a list comprehension
averages = [column_totals[idx]/row_count for idx in column_indexes]
print averages