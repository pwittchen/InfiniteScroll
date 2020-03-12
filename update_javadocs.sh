#!/usr/bin/env bash

git checkout master
./gradlew clean androidJavadocs
git checkout gh-pages
rm -rf
cp -avr library/build/docs/javadoc/* ./
git add -A
git commit -m "updating JavaDoc"
rm -rf library/build/docs
echo "javadocs updated"

echo "javadocs updated - now you can push your changes"