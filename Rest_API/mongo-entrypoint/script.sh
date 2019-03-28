#!/usr/bin/env bash
echo "Creating mongo users..."
mongo admin --host localhost -u root -p example --eval "db.createUser({user: 'test', pwd: 'test', roles: [{role: 'readWrite', db: 'test'}]}); db.createUser({user: 'admin', pwd: 'admin', roles: [{role: 'userAdminAnyDatabase', db: 'admin'}]});"
echo "Mongo users created."
