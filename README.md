# find_house

### Clone source code

`git clone https://github.com/thangyk97/find_house.git`

### Run with Eclipse
1. __Install *spring tools 4*__  : Help -> Eclipse marketplace -> search : spring tools 4 -> install
2. __Import project__ : File -> open project from file system -> Directory -> Select folder
3. __Maven update__: Right click on project -> maven -> update project.
4. __Edit database config__: edit in src/main/resources/application.properties
    * spring.jpa.hibernate.ddl-auto=create  --> delete and create new tables 
    * spring.jpa.hibernate.ddl-auto=none --> not change schema
    * __Remember__ edit *url, username, password*
5. __Run app__: Right click on project -> Run as -> Spring boot app (with success spring tools installation)
6. __Insert data__: Run house.sql

### Good luck !
