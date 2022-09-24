#!/bin/bash

# What: Runs a given command repeatedly until a failure happens.

# Usage:
# 1. Pass a number first to limit how many times the script tries to
# run the command (optional)
# 2. Pass the command and its arguments (after the limit, if any)

# Examples :
# ./counted-stress-test.sh 10 ./gradlew -Dorg.gradle.jvmargs="-Xmx70M"
# ./counted-stress-test.sh ./gradlew -Dorg.gradle.jvmargs="-Xmx70M"
# The first example will try running the given command 10 times,
# The second one will try running it again and again, with no
# limit (since Long.MAX_VALUE can't be practically reached on
# a modern machine).
# In both cases, one failure of the given command aborts the script.

is_uint() { case $1 in '' | *[!0-9]* ) return 1;; esac ;}

is_uint "$1"
if [ $? -eq 1 ]; then
    # First argument is not a number specifying a runCount limit, so we default to "infinite".
    runLimit=9223372036854775807 # Long.MAX_VALUE (64 bits)
else
    # First argument is the limit for runCount, so we take it into account, and shift arguments.
    runLimit=$1
    shift
fi

runCount=1
echo "Run number $runCount"
$@
# The command must be run "last" here so $? evaluates the given command,
# instead of said, our successful run of the echo command
while [ $? -eq 0 ] && [ $runCount -lt $runLimit ]; do
    ((runCount++))
    echo "Run number $runCount"
    $@
    # The command must be run last so $? evaluates the given command,
    # instead of said, our successful run of the echo command.
done
