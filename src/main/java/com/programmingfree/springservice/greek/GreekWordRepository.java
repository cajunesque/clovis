package com.programmingfree.springservice.greek;

import com.programmingfree.springservice.greek.model.GreekLetter;
import com.programmingfree.springservice.greek.model.GreekWord;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface GreekWordRepository extends CrudRepository<GreekWord, Integer> {

}