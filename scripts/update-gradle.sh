#!/usr/bin/env bash


PROJECTS="sample-android sample-groovy sample-kotlin sample-kotlin-js sample-multi-modules"
VERSiON="plugins/gradle-version.txt"
DIR=$( pwd )
echo "Reading Gradle versions from $VERSiON"
cat $VERSiON

for project in $PROJECTS ;
do
    # shellcheck disable=SC2164
    cd "$DIR/$project"
    echo cd "$DIR/$project"
    echo "Runnin gradle wrapper"
    ./gradlew wrapper || gradle wrapper
    echo
done
