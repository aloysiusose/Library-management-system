This SpringBoot application is a backend implementation of a Library Management system.

This Application has been built with SpringBoot 3.2.5 with key libraries as follows:
1. Spring web for configuring RESTFUL services
2. Spring data JPA for Object Relational Mapping
3. Spring security for security Implementation
4. Spring Oauth2 Resource server for configuring Json web token for authentication
5. Flyway for database migration and versioning


## How to use this Program
To use this program, you can fork and clone this repository and provides actual replacement for the following placeholders in the application.yml file

This application have been secured with Spring security using Json Web Tokens for authentication. By default, all endpoint are secured save for two endpoints which are used for registration and acquiring tokens for authentication.

To use this application, a Patron needs to be registered. the registration end point is as follows:
### localhost:8080/api/v1/patron/register
````
{ 
"firstName": "",
"lastName": "",
"email": "",
"password":"",
"contactInformation": {
"id": 1,
"telephone": "",
"streetName": "",
"province": "",
"country": ""
}
}
````
all fields of the above JSON payload are mandatory, failure results in a 400 bad request.


To authenticate, send a POST request to 
#### localhost:8080/api/v1/authenticate/ with a request body payload as follows:
````
{
"username":"email",
"password":"secret"
}
````

successful authentication returns a JWT 
```
 eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoia3AubG1zIiwiY2xhaW0iOiJQQVRST04iLCJleHAiOjE3MTU1OTA1MjQsImlhdCI6MTcxNTU4NjkyNH0.pxng43DIXQEV-Ic-SOqQ7STTIDzS-HqgMKeZb1anijK3qumBPRdXQlb68DZWuZRnZxjuyX3SqgnICGdL5TdLKc_TgLjIPlVBoH4jz8Fcvf2l9I-CpvCAJ5KDd59k6nyp6A_WaUc0q1ip2ZMgF_BOIffA0_ffQW34Hf5IBzjbagNg0PPliMHAQaX9ydKzzYjEYmJ7zzbzuQrEypD-T_fcvu8H4fJomFGPqvo_ksURKMdlNnLBjkFgUy72GJx8cYAokgSynH5KiLI0k0BGbCcULdm0iVVXrLV8gxmrgOq9DFvoUg4AT_Yew1L8n3h0E_-yJ8_VrBwCE8BdEZmZU_aAiA 
 ```
which must be attached as part of the Bearer token header for subsequent request. Also, authorization to delete any resource from this application has  be given to Patrons with ADMIN privileges only.

## Application Work flow
This library management system comprise three domains viz: Books, Patrons and Borrowed books.
1. An admin patron can add and remove books and can also remove other patrons
2. A book can be borrowed and returned by a patron. However, a book has a specific quantity such that the application knows when a book is no longer available for borrow until patrons returns them. Also, there is a check in place to ensure you only return books you have borrowed.
3. Deletion of resources is only allowed for Patrons with ADMIN Privileges
4. Custom Exception handlers have been configured the various functionalities. Also, constraints have been placed on the sql schema to prevent bad data format. 
5. Database versioning and migration have been implemented using flyway migrations
6. A borrowed book is stored in a database with data such as date of borrow, date of return, patron id, book id etc. this table also serves as a log and audit for borrowed books


### Must Haves
1. A code Editor or an IDE
2. latest JDK or version 17+ at least