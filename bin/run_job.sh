#!/bin/sh
cd bin
./kitchen.sh -file=$1 > log.txt
echo $?
