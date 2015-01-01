package com.cloviscorp.grammar.demo;

import java.util.List;

import com.cloviscorp.grammar.demo.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task, Integer> {

	List<Task> findByTaskArchived(@Param("archived") boolean taskArchived);
	List<Task> findByTaskStatus(@Param("status") String taskStatus);

}
