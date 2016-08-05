#!/bin/sh
cd /home/carlos/projects/code/unity_test
./kitchen.sh -file=$1 > log.txt
echo $?
