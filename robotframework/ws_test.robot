*** Settings ***
Documentation     Example test cases for testing our simple web service

Library           Collections
Library           requests         
Library           JsonValidator

*** Test Cases ***
Check expected result of web service
	${result} =  get  http://localhost:8080/today
	Log  ${result.status_code}
	Log  ${result.text}
    Should Be Equal  ${result.status_code}  ${200}
	${json} =  Set Variable  ${result.text}
	Element should exist    ${json}    .status:contains("workday")
    
