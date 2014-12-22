package com.programmingfree.springservice.demo;

import com.programmingfree.springservice.Application;
import com.programmingfree.springservice.demo.model.Task;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@ContextConfiguration
//@WebApplicationContext
@WebAppConfiguration
@IntegrationTest(value = "server.port=8080")

public class TaskResourceTest {

    private RestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    TaskRepository repository;

    //@Ignore
    @Test
    public void getsTask() {
        // arrange
        String task1Url = "http://localhost:8080/tasks/1";
        ParameterizedTypeReference<Resource<Task>> responseType = new ParameterizedTypeReference<Resource<Task>>() {};

        // act
        ResponseEntity<Resource<Task>> responseEntity = restTemplate.exchange(task1Url, GET, null, responseType);

        // assert
        Task task = responseEntity.getBody().getContent();
        Assert.assertEquals("MEDIUM", task.getTaskPriority());
    }

    //@Ignore
    @Test
    public void testFindAll() {
        Iterable results = repository.findAll();
        System.out.println(" ==================================================================");
        System.out.println(" ======================          TASK REPOSITORY RESULTS = "+results.iterator().hasNext());
        System.out.println(" ==================================================================");
        Assert.assertTrue(results.iterator().hasNext());
        for (Iterator<Task> resultsIter = results.iterator(); resultsIter.hasNext();){
            System.out.println(resultsIter.next());
        }
        System.out.println(" ==================================================================");
        System.out.println(" ======================          TASK REPOSITORY RESULTS = "+results.iterator().hasNext());
        System.out.println(" ==================================================================");
    }

}