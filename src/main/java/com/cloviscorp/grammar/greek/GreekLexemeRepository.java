package com.cloviscorp.grammar.greek;

import java.util.List;

import com.cloviscorp.grammar.greek.model.GreekLexeme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GreekLexemeRepository extends CrudRepository<GreekLexeme, Integer> {

	List<GreekLexeme> findByPars(@Param("pars") String pars);
}