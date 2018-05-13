*** Settings ***
Documentation     Example test cases for testing our simple web service

Library           Collections
Library           requests         
Library           JsonValidator
Library           DateTime

*** Test Cases ***
Check expected result of web service
	${result} =  get  http://localhost:8080/today
	Log  ${result.status_code}
	Log  ${result.text}
    Should Be Equal  ${result.status_code}  ${200}
	${json} =  Set Variable  ${result.text}
	${currentDay} =  Get Current Date    result_format=%d
	Run Keyword If	'${currentDay}' != '23'	Element should exist    ${json}    .status:contains("workday") 
	Run Keyword If  '${currentDay}' == '23'  Element should exist    ${json}    .status:contains("holiday")
