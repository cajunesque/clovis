package com.cloviscorp.grammar.greek;

import com.cloviscorp.grammar.greek.model.GreekMorpheme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface GreekMorphemeRepository extends CrudRepository<GreekMorpheme, Integer> {

	List<GreekMorpheme> findByPars(@Param("pars") String pars);
}