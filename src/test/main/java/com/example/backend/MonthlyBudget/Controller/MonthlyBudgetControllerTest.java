package com.example.backend.MonthlyBudget.Controller;

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

//1212 2022 -- #4 forsøg på unit testing af API kald til REST controller
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonthlyBudgetControllerTest {
@LocalServerPort
    private int port;
    private String apiAddress;
    TestRestTemplate restTemplate = new TestRestTemplate(); //this makes POSTs and GETs etc
    HttpHeaders headers = new HttpHeaders();  //this sets the header of the http entity

    @Test
    public void whenServerIsReady_GETallMonthlyBudgets_SeeAllMonthlyBudgets() throws JSONException {
        apiAddress = "/api/v1/monthlybudget"; //the address of the REST API
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(apiAddress), HttpMethod.GET, entity, String.class);
       // System.out.println("This is the thing we get: " + response.getBody()); //sout-test call
        String expected = "[" +  //this is the data we expect to receive.
       " {" +
           " \"monthly_Id\": 1, " +
               " \"date\": \"2022-12\"," +
               " \"monthlyMoney\": 2500.0, " +
                "\"user\": { " +
            "\"user_id\": 2, " +
                  "  \"name\": \"Hans\"," +
                   " \"password\": \"kodeord13\" " +
       " }," +
         "   \"dailyBudgets\": []" +
       " },  " +
      "  {" +
          "  \"monthly_Id\": 2," +
              "  \"date\": \"2022-12\"," +
              "  \"monthlyMoney\": 4000.0," +
              "  \"user\": {" +
        "    \"user_id\": 1," +
                 "   \"name\": \"Timmie\"," +
                  "  \"password\": \"jegErSuperSej\"" +
      "  }," +
        "    \"dailyBudgets\": []" +
      "  }," +
      "  {"+
        "    \"monthly_Id\": 3," +
             "   \"date\": \"2022-12\"," +
               " \"monthlyMoney\": 4000.0," +
            "    \"user\": {" +
          "  \"user_id\": 3," +
                "    \"name\": \"Nicolas\"," +
                  "  \"password\": \"kodeord13\" " +
      "  }," +
         "   \"dailyBudgets\": []" +
      "  }" +
"]" ;
     JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
