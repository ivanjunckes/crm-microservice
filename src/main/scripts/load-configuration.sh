#!/bin/bash
#set -x

cd "$(dirname "$0")"
source ./import.sh


HOST="74.208.214.72"

# API Connection
import http://${HOST}:8080/tag ../resources/api-connection-crmpr.json

#API Claims Source
import http://${HOST}:8080/tag ../resources/api-claims-crmpr.json

# OAuth2 profile
import http://${HOST}:8080/tag ../resources/oauth2-profile-crmpr.json

# set the default key size for public/private keys to 2048
import http://${HOST}:8080/tag ../resources/default-keysize-2048.json

# load 4 accounts with their respective set of roles
import http://${HOST}:8080/tag ../resources/account-carlos.json
import http://${HOST}:8080/tag ../resources/account-claudete.json
import http://${HOST}:8080/tag ../resources/account-fernanda.json
import http://${HOST}:8080/tag ../resources/account-leila.json
import http://${HOST}:8080/tag ../resources/account-rafael.json

