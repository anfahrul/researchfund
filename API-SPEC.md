# Researchfund API 

## Register
Request:
- Method: POST
- Endpoint: `/api/register`
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
- Endpoint: `/api/login`
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
    "code": 200,
    "status": "success",
    "data": {
        "username": "funder01",
        "email": "funder01@mail.com",
        "role": "FUNDER",
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJmdW5kZXIwMSIsImV4cCI6MTY3MDM3MTM0OX0.ca_mOsC2LQHiGYN-N59tKXjEpCQ4d5a2d2lkWtMye5KvczJKf8sF2xOoU0T_sznuLdgbbz2ACYrU8ZvLHP9S8w"
    }
} 
```