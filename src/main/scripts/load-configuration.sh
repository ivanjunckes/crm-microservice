#!/bin/bash
#set -x

cd "$(dirname "$0")"
source ./import.sh


HOST="localhost"

# set the default key size for public/private keys to 2048
import http://localhost:8080/tag ../resources/default-keysize-2048.json

# load 4 accounts with their respective set of roles
import http://localhost:8080/tag ../resources/account-claudete.json
import http://localhost:8080/tag ../resources/account-fernanda.json
import http://localhost:8080/tag ../resources/account-leila.json
import http://localhost:8080/tag ../resources/account-rafael.json

