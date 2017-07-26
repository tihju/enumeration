#!/bin/bash

RANDOM=$$

javac Ntuple_timed.java
javac Subset_timed.java
echo "#items, MaxCopies, ntuple_time, subset_time" > data.csv


for((size=3;size<=5;size++))
do
  for((copy=1;copy<=4;copy++))
  do
    for((i=0;i<50;i++))
    do
      totalV=0
      > copies.txt
      > values.txt
      > weights.txt
      for((j=0;j<size;j++))
      do
        copyrange=$(($RANDOM%$copy+1))
        value=$(($RANDOM%100+1))
        totalV=$(($totalV+$value))
        echo "$copyrange" >> copies.txt
        echo "$value" >> values.txt
      done
      
      halfW=$(($totalV/4))

      for((k=0;k<size;k++))
      do
        weight=$(($RANDOM%$halfW+1))
        echo "$weight" >> weights.txt
      done

      TIMEFORMAT=%R
      t1=$( time (java Ntuple_timed "$size" copies.txt values.txt weights.txt ) 2>&1)
      t2=$( time (java Subset_timed "$size" copies.txt values.txt weights.txt ) 2>&1)
      echo "$size,$copy,$t1,$t2" >> data.csv
    done
  done
done
echo "All tests passed."
