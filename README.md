# Dummy Projektboerse
Simulates the API of a simple post-orientated CMS without any security- or authentication features. For local testing only.

Requirements: Java 8, Apache Web Server, MySQL/MariaDB

Supports the following HTTP-methods: GET, POST, DELETE

## API Documentation:
#### List all posts (GET):

`http://localhost:8045/posts`

---

#### List a single post (GET):

`http://localhost:8045/posts/id/<id>`

---

#### List the post-titles only (GET):

`http://localhost:8045/posts/titles`

---

#### Add Post (POST):

`http://localhost:8045/posts/add?title=<title>&content=<content>&status=publish`

---

#### Delete Post (GET):

`http://localhost:8045/posts/delete?id=<id>`

---

#### Delete All Posts (DELETE):

`http://localhost:8045/posts/deleteall`

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
