# Leaderboards

## How to install
  - Run: `docker build -t leaderboards .`
  - Run: `docker-compose up `
## Swagger

    Swagger: http://localhost:8080/swagger-ui/index.html

## API Example:

### GET all-time
Request:
<br>
    `curl --location --request GET 'http://localhost:8080/v1/scores/all-time'`
<br><br>
Response:
``` 
[
    {
        "id": 5,
        "name": "Olivia",
        "value": 452
    },
    {
        "id": 7,
        "name": "Sophia",
        "value": 231
    },
    {
        "id": 4,
        "name": "Ethan",
        "value": 155
    },
    ...
]
```
### GET monthly
Request:
<br>
`curl -X 'GET' \
'http://localhost:8080/v1/scores/monthly?month=JANUARY' \
-H 'accept: application/json'`
<br><br>
Response:
``` 
[
    {
        "id": 5,
        "name": "Olivia",
        "value": 452
    },
    {
        "id": 7,
        "name": "Sophia",
        "value": 231
    },
    {
        "id": 4,
        "name": "Ethan",
        "value": 155
    },
    ...
]
```
### POST scores
Request:
<br>
`curl --location --request POST 'http://localhost:8080/v1/scores' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Thiago",
"value": 35
}'`
<br><br>
Response:
``` 
{
    "id": 16,
    "name": "Thiago",
    "value": 35,
    "createDate": "2023-03-28T19:57:57.8777634"
}
```




