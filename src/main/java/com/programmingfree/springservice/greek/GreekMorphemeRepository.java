package com.programmingfree.springservice.greek;

import com.programmingfree.springservice.greek.model.GreekLexeme;
import com.programmingfree.springservice.greek.model.GreekMorpheme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface GreekMorphemeRepository extends CrudRepository<GreekMorpheme, Integer> {

	List<GreekMorpheme> findByPars(@Param("pars") String pars);
}