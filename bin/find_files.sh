#!/bin/sh
cd ../etl
path=$(pwd)
result=0
filenames=$(find . -name "*unit_test.kjb" -print|tr '\n' ' ')
cd ..
IFS=' '
set -- $filenames
for file in $@;do 
	./kitchen.sh -file=$path/$file > log.txt
	result=$(($result+$?))
done


echo $result a
