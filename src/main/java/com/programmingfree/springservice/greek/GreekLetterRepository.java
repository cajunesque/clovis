package com.programmingfree.springservice.greek;

import com.programmingfree.springservice.greek.model.GreekLetter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RepositoryRestResource
public interface GreekLetterRepository extends CrudRepository<GreekLetter, Integer> {
	@Query("SELECT l FROM GreekLetter l WHERE l.translit Like :translit% ORDER BY length(l.translit) Desc")
	public List<GreekLetter> findByTranslitStartsWith(@Param("translit") String translit);

	@Query("SELECT l FROM GreekLetter l WHERE l.present Like :present% ORDER BY length(l.present) Desc")
	public List<GreekLetter> findByPresentStartsWith(@Param("present") String present);

	List<GreekLetter> findByPresent(@Param("present") String present);

	List<GreekLetter> findByTypesLike(@Param("types") String types, Sort sort);

}