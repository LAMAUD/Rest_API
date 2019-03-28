curl --header "Content-Type: application/json" --request POST --data '{"id":"1", "firstName":"Cedric","lastName":"LAMAUD"}' http://localhost:8081/api/users/save

db.createUser({user: 'test', pwd: 'test', roles: [{role: 'readWrite', db: 'test'}], mechanisms:["SCRAM-SHA-1"]})
db.users.insert({ id:1, firstName: "Cédric", lastName: "LAMAUD" })

