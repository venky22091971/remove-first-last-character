# Remove First and Last Character API

A simple Spring Boot REST API that removes the first and last characters from a given string.

The application is implemented using **Java 17**, **Spring Boot**, and **Maven** and includes automated tests for the required scenarios.

## Requirements

* Java 17
* Maven
* Spring Boot

## Endpoint

### Remove First and Last Character

**HTTP Method**

`GET`

**Endpoint**

`/remove`

**Query Parameter**

`original` — The input string from which the first and last characters will be removed.

### Example Request

```text
GET /remove?original=eloquent
```

### Example Response

```text
loquen
```

HTTP Status:

```text
200 OK
```

## Business Rules

The endpoint handles input according to the following rules:

| Input Length           | Behaviour                             |
| ---------------------- | ------------------------------------- |
| Less than 2 characters | Returns `400 Bad Request`             |
| Exactly 2 characters   | Returns an empty string               |
| 3 or more characters   | Removes the first and last characters |

## Examples

| Input           | Output            |
| --------------- | ----------------- |
| `eloquent`      | `loquen`          |
| `country`       | `ountr`           |
| `person`        | `erso`            |
| `xyz`           | `y`               |
| `ab`            | Empty string      |
| `12345`         | `234`             |
| `*123*%qwerty+` | `123*%qwerty`     |
| `a`             | `400 Bad Request` |
| Empty string    | `400 Bad Request` |

## Implementation

The core implementation uses Java's `substring()` method:

```java
original.substring(1, original.length() - 1);
```

For an input such as:

```text
eloquent
```

the first character `e` and last character `t` are removed, producing:

```text
loquen
```

Input validation is performed before the substring operation. Strings containing fewer than two characters result in a `400 Bad Request`.

## Running the Application

From the project root directory, run:

```bash
mvn spring-boot:run
```

By default, the application starts on port `8080`.

Example:

```text
GET http://localhost:8080/remove?original=eloquent
```

Expected response:

```text
loquen
```

## Running Automated Tests

Run all automated tests using:

```bash
mvn verify
```

The tests validate both the HTTP status and response body.

## Test Coverage

`DemoControllerTest.java` covers:

* Common words of varying lengths
* Empty string
* Single-character input
* Two-character input
* Three-character input
* Numeric input
* Special characters
* Missing query parameter
* Expected HTTP response status

## Technologies Used

* Java 17
* Spring Boot
* Spring Web
* Maven
* JUnit 5
* MockMvc

## Project Structure

```text
src
├── main
│   └── java
│       └── com.example.demo
│           └── DemoController.java
│
└── test
    └── java
        └── com.example.demo
            └── DemoControllerTest.java
```

## Design Considerations

The implementation is intentionally kept simple because the endpoint has a single responsibility.

Separate service and persistence layers are not introduced because no business persistence or complex business logic is required for this exercise.

`MockMvc` is used for automated testing so that the HTTP endpoint behaviour, response status, query parameter handling, and response body are tested without requiring a separately running web server.
