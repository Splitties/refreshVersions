#!/bin/bash

is_uint() { case $1 in '' | *[!0-9]* ) return 1;; esac ;}

is_uint "$1"
if [ $? -eq 1 ]; then
    runLimit=9223372036854775807 # Long.MAX_VALUE (64 bits)
else
    runLimit=$1
    shift
fi

runCount=1
echo "Run number $runCount"
$@
while [ $? -eq 0 ] && [ $runCount -lt $runLimit ]; do
    ((runCount++))
    echo "Run number $runCount"
    $@
done
