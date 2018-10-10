package telran.forum.service;

import java.util.List;
import java.util.stream.Stream;

import telran.forum.domain.Post;
import telran.forum.dto.DatePeriodDto;
import telran.forum.dto.NewCommentDto;
import telran.forum.dto.NewPostDto;
import telran.forum.dto.PostUpdateDto;

public interface ForumService {
	
	Post addNewPost(NewPostDto newPost);
	
	Post getPost(String id);
	
	Post removePost(String id);
	
	Post updatePost(PostUpdateDto updatepost);
	
	boolean addLike(String id);
	
	Post addComment(String id, NewCommentDto newComment );
	
	Stream<Post> findByTags(List<String> tags);
	
	Stream<Post> findByAuthor(String author);
	
	Stream<Post> findByDate(DatePeriodDto period);

}
 