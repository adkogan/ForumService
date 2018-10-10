package telran.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.forum.domain.Post;

public interface ForumRepository extends MongoRepository<Post, Integer> {
	
	
	//@Query("{'tags': { $all: ?1}")
	Stream<Post> findByTagsIn(List<String> tags);
	
	Stream<Post> findByAuthor(String author);

	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);
	
	Stream<Post> findAllsBy();
}