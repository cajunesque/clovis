package com.programmingfree.springservice.greek;

import java.util.List;

import com.programmingfree.springservice.greek.model.GreekLexeme;
import com.programmingfree.springservice.greek.model.GreekString;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GreekLexemeRepository extends CrudRepository<GreekLexeme, Integer> {

	List<GreekLexeme> findByPars(@Param("pars") String pars);
}