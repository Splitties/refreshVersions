#!/usr/bin/env bash
fail() {
    echo "$1"
    exit 1
}
checkInstalled() {
    which "$1" ||  fail "ERROR: please install $1"
}

runGradleTaskInFolder() {
    echo
    echo "== cd $1 =="
    cd $1 || fail "ERROR: Folder $1 doens't exist"
    pwd

    echo '$' "./gradlew $TASK"
    ./gradlew task || fail "ERROR for task $TASK"
    cd ..
}

DIR="$(basename $PWD)"
TASK="check refreshVersions"

test -n "$1" && TASK="$1"

test "$DIR" = "refreshVersions" || fail "ERROR: must be called from the refreshVersions folder"
checkInstalled java
checkInstalled node
checkInstalled yarn


cd plugins || fail "can't cd plugins"
./gradlew test publishToMavenLocal
cd ..

runGradleTaskInFolder sample-kotlin
runGradleTaskInFolder sample-multi-modules
runGradleTaskInFolder sample-groovy
runGradleTaskInFolder sample-kotlin-js

test -n "$ANDROID_SDK_ROOT" && {
    runGradleTaskInFolder sample-android
}

echo "SUCCESS"
test "$TASK" = "refreshVersionsCleanup" || {
    echo "To clean up your git history, you can run:"
    echo "  ./checkPlugins.sh refreshVersionsCleanup"
}
