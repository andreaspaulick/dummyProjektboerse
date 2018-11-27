# Dummy Projektboerse
Simulates the API of a simple post-orientated CMS without any security- or authentication features. For local testing only.

Requirements: Java 8, Apache Web Server, MySQL/MariaDB

Supports the following HTTP-methods: PUT, POST and DELETE

## API Documentation:
#### List all posts (GET):

`http://localhost:8045/posts`

---

#### List a single post (GET):

`http://localhost:8045/posts/<id>`

---

#### Add Post (POST):

`http://localhost:8045/posts/add?title=<title>&content=<content>&status=publish`

---

#### Delete Post (DELETE):

`http://localhost:8045/posts/delete?id=<id>`

---

#### Add Post via JSON-REST-Request (POST):

`http://localhost:8045/posts/jsonadd`

#### Expected JSON Format:

```json
    {
       "title": "<title>",
       "content": "<content>",
       "status": "publish"
    }
```
