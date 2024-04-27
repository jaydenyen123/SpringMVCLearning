# REST
REST stands for representational state transfer and refers to the
design style for how web servers communicates. REST APIs use HTTP
verb to create, manage and delete server resources, those
resource are usually data structure in JSON or XML. There is HTTP
status code used to communicate success, failure, or errors. REST is very
often stateless, where server does not maintain client state

# URL vs URI
The difference is URL locate and URI identify a resource in internet
URL is a subset of URI

# Hikari Data Pool
When Spring starts up, it automatically starts a set of connections to the database server, in this 
case -- MYSQL, and those connections are maintained in the Hikari Data Source Pool. 

# Hibernate entity to SQL statement mapping
You can setup properties like those in application.properties to allow Hibernate reflect upon the entities
and create the SQL statements that will reflect on the entities to create the database

# default SQL file name
During database initialization, Hibernate will look for import.sql and Spring will look for schema.sql and data.sql
at the root level for execution 

# Flyway

Flyway is designed to perform DB migration task for Spring applications, it is meant only for DB structural changes
and each time you want a new DB migration, you specify a script in certain name in certain folder, but the
folder location can be overriden with spring properties