#!/bin/bash
# USAGE:
#    site.sh              # serve website locally
#    site.sh mybranch     # publish to https://jmfayard.github.io/rvdev/
#    site.sh release      # publish to https://jmfayard.github.io/refreshVersions/


# The website is built using MkDocs with the Material theme.
# https://squidfunk.github.io/mkdocs-material/
# It requires Python to run.
pip install -r docs/requirements.txt > /dev/null

# GitHub wants those files to be at the root of the repository
cp -vf CHANGELOG.md  CONTRIBUTING.md docs

git remote add rvdev https://github.com/jmfayard/rvdev 2>/dev/null || true

BRANCH=$1
CONFIG=plugins/build/mkdocs.yml
if test -z $BRANCH; then
    echo "serve website locally";
    mkdocs serve
elif test $BRANCH == 'release' ; then
    mkdocs gh-deploy --force
    echo "Site published to https://jmfayard.github.io/refreshVersions/"
else
   cp mkdocs.yml $CONFIG
   echo 'site_url: https://jmfayard.github.io/rvdev/' >> mkdocs.yml
   mkdocs gh-deploy --remote-name rvdev --force
   cp $CONFIG mkdocs.yml
   echo "Site published to https://jmfayard.github.io/rvdev/";
fi
