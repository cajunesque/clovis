package com.programmingfree.springservice.greek;

import com.programmingfree.springservice.greek.model.GreekLetter;
import com.programmingfree.springservice.greek.model.GreekLexeme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface GreekLetterRepository extends CrudRepository<GreekLetter, Integer> {

	List<GreekLetter> findByTypesLike(@Param("types") String types);

}