# webhosting-analytical-tool

Web-hosting company provides customer support via email. They record reply waiting time, type of question, category, and service. This test case was designed to process data in order to improve customer experience.  

## Summary

  - [Implementation details](#implementation-details)
  - [Run and test](#run-and-test)
  - [Authors](#authors)

### Implementation details
    
There are two endpoints for upload and evaluate data. Format of file is the following:

    7
    C 1.1 8.15.1 P 15.10.2012 83
    C 1 10.1 P 01.12.2012 65
    C 1.1 5.5.1 P 01.11.2012 117
    D 1.1 8 P 01.01.2012-01.12.2012
    C 3 10.2 N 02.10.2012 100
    D 1 * P 8.10.2012-20.11.2012
    D 3 10 P 01.12.2012
   
There are two types of records:

Waiting timeline:
     
    C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time
    
Query line:

    D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date_from[-date_to] 

Values in square brackets are optional.

service_id[.variation_id] - service 9.1 represent service 9 of variation 1.

question_type_id[.category_id.[sub-category_id]] - question type 7.14.4 represent question type 7
category 14 and sub-category 4.

P/N - response type ‘P’ (first answer) or ‘N’ (next answer).

date - response date format is DD.MM.RRRR, for example, 27.11.2012 (27.november 2012).

time - time in minutes represent waiting time.

It is possible to calculate the average time for WaitingTimeLine records that are before Query record and match all criteria.

Link for upload file is:  

    http://localhost:8085/data/upload-file/ 
    
Link for evaluate data (get average time) is:
    
    /evaluate/average-time
    
You can get evaluated data in JSON. 


  - Maven Checkstyle Plugin and Travis CI have been configured
  - Unit tests are present

## Run and test

You need for test:

  - JDK 8 or higher
  - Apache Maven
  - Tomcat

## Authors

  - [Andrii Borozdykh](https://github.com/aborozdykh/)
