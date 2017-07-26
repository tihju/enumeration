#!/bin/bash

RANDOM=$$

javac Ntuple.java
javac Subset.java
>ntuple_result.txt
>subset_result.txt


for((i=1;i<=100;i++))
do
  > copies.txt
  > values.txt
  > weights.txt
  for((j=0;j<6;j++))
  do
    value=$(($RANDOM%100+1))
    weight=$(($value/2))
    echo "2" >> copies.txt
    echo "$value" >> values.txt
    echo "$weight" >> weights.txt
  done
    java Ntuple "6" copies.txt values.txt weights.txt > ntuple_result.txt
    java Subset "6" copies.txt values.txt weights.txt > subset_result.txt
    DIFF=$(diff ntuple_result.txt subset_result.txt)
    if [ "$DIFF" != "" ]
    then
      echo "test case $i failed."
      echo "the result from n-tuple implementations is"
      cat ntuple_result.txt
      echo "the result from subset implementations is"
      cat subset_result.txt
      exit
    fi
done
echo "All tests passed."
