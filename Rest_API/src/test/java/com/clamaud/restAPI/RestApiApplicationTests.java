package com.clamaud.restAPI;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.clamaud.restAPI.domain.jpa.User;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RestApiApplication.class }, webEnvironment 
= WebEnvironment.DEFINED_PORT)
public class RestApiApplicationTests {

	 private static final String API_ROOT = "http://localhost:8081/api/users";

   private User createRandomUser() {
       User user = new User();
       user.setFirstName(randomString(7));;
       user.setLastName(randomString(10));
       return user;
   }

   private String createUserAsUri(User user) {
       Response response = RestAssured.given()
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .body(user)
         .post(API_ROOT);
       return API_ROOT + "/" + response.jsonPath().get("id");
   }
   
   private String randomNumeric() {
	   int random = (int)(Math.random() * 50 + 1);
	   return String.valueOf(random);
   }
   
   private String randomString(int size) {
	    byte[] array = new byte[size];
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    return generatedString;
   }
   
   @Test
   public void whenGetAllUsers_thenOK() {
       Response response = RestAssured.get(API_ROOT);
     
       assertEquals(HttpStatus.OK.value(), response.getStatusCode());
   }
    
   @Test
   public void whenGetUsersByLastName_thenOK() {
       User user = createRandomUser();
       createUserAsUri(user);
       Response response = RestAssured.get(
         API_ROOT + "/lastname/" + user.getLastName());
        
       assertEquals(HttpStatus.OK.value(), response.getStatusCode());
       assertTrue(response.as(List.class)
         .size() > 0);
   }
   @Test
   public void whenGetCreatedUserById_thenOK() {
       User user = createRandomUser();
       String location = createUserAsUri(user);
       Response response = RestAssured.get(location);
        
       assertEquals(HttpStatus.OK.value(), response.getStatusCode());
       assertEquals(user.getFirstName(), response.jsonPath()
         .get("firstName"));
   }
    
   @Test
   @Ignore
   public void whenGetNotExistUserById_thenNotFound() {
       Response response = RestAssured.get(API_ROOT + "/" + randomNumeric());
        
       assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
   }
   
   @Test
   public void whenCreateNewUser_thenCreated() {
       User bouserok = createRandomUser();
       Response response = RestAssured.given()
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .body(bouserok)
         .post(API_ROOT);
        
       assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
   }
    
   @Test
   public void whenInvalidUser_thenError() {
       User user = createRandomUser();
       user.setLastName(null);
       Response response = RestAssured.given()
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .body(user)
         .post(API_ROOT);
        
       assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
   }
   
   @Test
   @Ignore
   public void whenUpdateCreatedUser_thenUpdated() {
       User user = createRandomUser();
       String location = createUserAsUri(user);
       user.setId(Long.parseLong(location.split("api/users/")[1]));
       user.setLastName("newAuthor");
       Response response = RestAssured.given()
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .body(user)
         .put(location);
        
       assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    
       response = RestAssured.get(location);
        
       assertEquals(HttpStatus.OK.value(), response.getStatusCode());
       assertEquals("newAuthor", response.jsonPath()
         .get("author"));
   }
   
   @Test
   @Ignore
   public void whenDeleteCreatedUser_thenOk() {
       User user = createRandomUser();
       String location = createUserAsUri(user);
       Response response = RestAssured.delete(location);
        
       assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    
       response = RestAssured.get(location);
       assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
   }

   
   
   

}
