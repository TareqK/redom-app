# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM adoptopenjdk:11.0.6_10-jre-hotspot

COPY target/qahwagi-*.jar /qahwagi.jar
COPY target/lib ./lib
CMD ["java","-jar","-Dproduction=true","qahwagi.jar"]
