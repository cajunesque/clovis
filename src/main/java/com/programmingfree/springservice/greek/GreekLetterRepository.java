package com.programmingfree.springservice.greek;

import com.programmingfree.springservice.greek.model.GreekLetter;
import com.programmingfree.springservice.greek.model.GreekLexeme;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.domain.Sort;

import java.util.List;

@RepositoryRestResource
public interface GreekLetterRepository extends CrudRepository<GreekLetter, Integer> {

	//@Query("SELECT l FROM GreekLetter l WHERE l.translit Like :translit% ORDER BY length(l.translit) Desc")
	//public List<GreekLetter> findByTranslitLengthDesc(@Param("translit") String translit, Sort sort);
	public List<GreekLetter> findByTranslitStartsWith(@Param("translit") String translit, Sort sort);

	List<GreekLetter> findByPresent(@Param("present") String present);

	List<GreekLetter> findByTypesLike(@Param("types") String types, Sort sort);

}