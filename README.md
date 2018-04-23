[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/olegdatskevich.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/olegdatskevich)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/olegdatskevich/badge.svg?branch=master)](https://coveralls.io/github/Brest-Java-Course-2018/olegdatskevich?branch=master)  

## Description    
Web application for cinema. The application is designed to work with movies and seances.  
## Used technologies
* JDK 1.8  
* Spring 4 Framework  
* Maven v3.2.2
* Jetty v.9.4.8  
* H2 database v1.4.196

## Build  

       $mvn clean install  

## Run REST-service  

       $mvn -pl rest/ jetty:run  
       
       http://localhost:8088/movies  
       
## Run WEB-application  

       $mvn -pl web-app/ jetty:run  
       
       http://localhost:8080/movies  
## 
EPAM Java Course, Brest 2018  
by **Oleg Datskevich**  
e-mail: olegdac@gmail.com  
tel: +375(29)5266584