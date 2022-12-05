# API Spec Researchfund

## Register
Request:
- Method: POST
- Endpoint: `/register`
- Header
    - Content-Type: application/json
    - Accept: application/json
- Body
```json
{
    "username": "funder01",
    "email": "funder01@mail.com",
    "password": "pass7890",
    "role": 1
}
```

Response:

```json
{
    "code": 201,
    "status": "success",
    "data": {
        "username": "funder01",
        "email": "funder01@mail.com",
        "password": "pass7890",
        "role": 1
    }
}
```

## Login
Request:
- Method: POST
- Endpoint: `/login`
- Header
    - Content-Type: application/json
    - Accept: application/json
- Body
```json
{
    "email": "funder01@mail.com",
    "password": "pass7890"
}
```

Response:
```json
{
    "code": 202,
    "status": "success",
    "data": {
        "username": "funder01",
        "email": "funder01@mail.com",
        "password": "pass7890",
        "role": 1
    }
} 
```