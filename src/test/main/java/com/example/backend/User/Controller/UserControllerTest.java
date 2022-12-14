package com.example.backend.User.Controller;

import com.example.backend.BackendApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;
    private String apiAddress; //this is the URL of the call
    //private String apiAddress = "/api/v1/users/1"; //this is the URL of the call -- it only gets #1
    TestRestTemplate restTemplate = new TestRestTemplate(); //this makes POSTs and GETs etc
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void whenServerInitialisedWithUsers_getFirstUser_confirmUserData() throws JSONException {
        apiAddress = "/api/v1/users/1"; //this is the URL of the call -- it only gets #1
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(apiAddress), HttpMethod.GET, entity, String.class);
        //  System.out.println("This is the data we got: " + response.getBody()); //sout-test call
        String expected = "{\"user_id\": 1, \"name\":\"Timmie\", \"password\":\"jegErSuperSej\"}";
         JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void whenServerStarted_postNewUserData_testUserPostedCorrectly() throws JSONException {
        headers.add("content-type", "application/json"); //sets the header content type
        //to json, så it can post
        apiAddress = "/api/v1/users"; //sets the url
        String bodyContent = "\n" + //sets the data to be posted
                "{\n" +
                "        \"name\": \"johny\",\n" +
                "        \"password\":\"dinfuck\"\n" +
                "        }";
      //  System.out.println("This is body contnt: " +bodyContent); //debugging sout
        HttpEntity<String> entity = new HttpEntity<>(bodyContent, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(apiAddress), HttpMethod.POST, entity, String.class);
         // System.out.println("This is the data we got: " + response.getBody()); //sout-test call
        String expected = "{\"name\":\"johny\", \"password\":\"dinfuck\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
