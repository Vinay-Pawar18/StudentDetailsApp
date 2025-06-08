# StudentDetailsApp

**USE BELOW URL:**
-- **POST** localhost:port/test/course
-- **PUT** localhost:port/test/course/id
-- **GET** localhost:port/test/course
-- **GET** localhost:port/test/course/id

This is the CRUD application where we are going to maintain the details of student as:

Id--Auto generated 

From Postman:
1) If we are creating the **POST** request need to give body as:

{
    "name": "JAVA",
    "price": "15000"
}

2) To modify the detail we have **PUT** call with request bodya as below:
{
    "name": "JAVA",
    "price": "15000"
}

3) To get all the student details we have **GET** call as with below Response:
{
        "cid": 1,
        "name": "JAVA",
        "price": 15000.0
}

4) To get the student by id we have one more **GET** call with below Response:

{
        "cid": 1,
        "name": "JAVA",
        "price": 15000.0
}




