package com.example.accessingdatarest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Hypermedia-based (so called Hypermedia-driven) RESTful front end and a JPA-based back end.
 * At runtime, Spring Data REST automatically creates an implementation of this interface.
 * Then it uses the @RepositoryRestResource annotation to direct Spring MVC to create RESTful endpoints at /people.
 * @RepositoryRestResource is not required for a repository to be exported.
 * It is used only to change the export details, such as using /people instead of the default value of /persons.
 *
 * http://localhost:8080/people   contains content-type: application/hal+json
 * HAL - Hypertext Application Language - enables discoverability within API
 *
 * add (POST) curl -i -H "Content-Type:application/json" -d '{"firstName": "Frodo", "lastName": "Baggins"}' http://localhost:8080/people
 * find (GET): curl http://localhost:8080/people
 * custom find (GET): curl http://localhost:8080/people/search/findByLastName?name=Baggins
 * replaces an entire record: curl -X PUT -H "Content-Type:application/json" -d '{"firstName": "Bilbo", "lastName": "Baggins"}' http://localhost:8080/people/1
 * update subset of fields: curl -X PATCH -H "Content-Type:application/json" -d '{"firstName": "Bilbo Jr."}' http://localhost:8080/people/1
 * remove record: curl -X DELETE http://localhost:8080/people/1
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    List<Person> findByLastName(@Param("name") String name);
}
