# cjudge
an online judge designed for random test case

## install
At first, please install tomcat 9 and postgresql.

### frontend
Change "src/plugins/axios.js" line 9 into your server url.

Run "vue-cli-service build" to generate "dist" folder.

### backend
Set up on file "src/main/resources/application.properties".

Run "src/main/resources/db.sql".

Replace files in "src/main/resources/static/" with files in "dist" which generated before.

Run "package" in springboot to get "cjudge.war".

Upload it in tomcat server.
