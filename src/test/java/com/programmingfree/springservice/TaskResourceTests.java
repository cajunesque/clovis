package com.programmingfree.springservice;

import com.programmingfree.springservice.demo.Task;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.web.context.WebApplicationContext;

import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@ContextConfiguration
//@WebApplicationContext
@WebAppConfiguration
@IntegrationTest(value = "server.port=8080")

public class TaskResourceTests {

    private RestTemplate restTemplate = new TestRestTemplate();

    @Ignore
    @Test
    public void getsTask() {
        // arrange
        String task1Url = "http://localhost:8080/tasks/1";
        ParameterizedTypeReference<Resource<Task>> responseType = new ParameterizedTypeReference<Resource<Task>>() {};

        // act
        ResponseEntity<Resource<Task>> responseEntity = restTemplate.exchange(task1Url, GET, null, responseType);

        // assert
        Task task = responseEntity.getBody().getContent();
        assertEquals("MEDIUM", task.getTaskPriority());
    }


}