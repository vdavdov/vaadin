mongosh --host localhost -u admin -p pass <<EOF
use contact
db.createUser({
  user: "admin",
  pwd: "pass",
  roles: [{ role: "readWrite", db: "contact" }]
});
EOF