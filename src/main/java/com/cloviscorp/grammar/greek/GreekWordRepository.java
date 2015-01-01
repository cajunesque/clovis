package com.cloviscorp.grammar.greek;

import com.cloviscorp.grammar.greek.model.GreekWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GreekWordRepository extends CrudRepository<GreekWord, Integer> {

}