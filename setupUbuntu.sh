#! /bin/bash

# Commands to install Maven and openjdk on ubuntu.
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get -y update 
sudo apt-get install -y openjdk-8-jdk maven
sudo apt-get install -y  graphviz
