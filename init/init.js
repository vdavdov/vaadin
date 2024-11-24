print("Initializing database...");

db = db.getSiblingDB('contact');

db.createCollection('contacts');

db.contacts.insertMany([
    { id: "1", name: "example", email: "sample@example.com", phone: "+7 9879478962" },
    { id: "2", name: "example2", email: "sample2@example.com", phone: "+7 9879478963" }
]);
