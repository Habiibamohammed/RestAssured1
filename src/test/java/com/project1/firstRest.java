package com.project1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class firstRest {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
    // GET all posts
    @Test
    public void firstTest() {

        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
    // GET single post
    @Test
    public void secondTest() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1));
    }
    @Test
    public void createPost() {
        String requestBody = "{ \"title\": \"Habiba\",\"body\": \"QA\",\"userId\":5 }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("Habiba"))
                .body("body", equalTo("QA"))
                .body("userId", equalTo(5));
    }
    // GET not found post
    @Test
    public void postNotFound() {
        given()
                .when()
                .get("/posts/0")
                .then()
                .statusCode(404);
    }
    // DELETE post
    @Test
    public void deletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }
}
