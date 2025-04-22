# Pomona-Transit-System
Features:
1. Display the schedule of all trips for a given name and date
2. Edit the schedule i.e. edit the table of Trip Offering
3. Display the stops of a given trip
4. Display the weekly schedule of a given driver and date
5. Add a driver
6. Add a bus
7. Delete a bus
8. Record the actual trip stop data

Setup:
1. Create database and insert dummy data using SQL scripts
2. Ensure MySQL is running on localhost:3306, username:root, password:root (or change Main.java with your credentials)
3. Compile project: javac -cp "lib/mysql-connector-j-9.3.0.jar" -d out src/*.java
4. Run project: java -cp "lib/mysql-connector-j-9.3.0.jar:out" Main
