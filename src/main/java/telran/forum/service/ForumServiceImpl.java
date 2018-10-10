package telran.forum.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.forum.dao.ForumRepository;
import telran.forum.domain.Comment;
import telran.forum.domain.Post;
import telran.forum.dto.DatePeriodDto;
import telran.forum.dto.NewCommentDto;
import telran.forum.dto.NewPostDto;
import telran.forum.dto.PostUpdateDto;

@Service
public class ForumServiceImpl implements ForumService {

	@Autowired
	ForumRepository forumRepository;

	@Override
	public Post addNewPost(NewPostDto newPost) {
		Post post = new Post(newPost.getTitle(), newPost.getAuthor(), newPost.getContent(), newPost.getTags());
		return forumRepository.save(post); /// ???
	}

	@Override
	public Post getPost(String id) {
		return forumRepository.findById(Integer.valueOf(id)).orElse(null);
	}

	@Override
	public Post removePost(String id) {
		Post post = forumRepository.findById(Integer.valueOf(id)).orElse(null);
		if (post == null) {
			return null;
		}
		forumRepository.deleteById(Integer.valueOf(id));
		return post; /// ???
	}

	@Override
	public Post updatePost(PostUpdateDto updatepost) {
		Post existing = forumRepository.findById(Integer.valueOf(updatepost.getId())).orElse(null);
		if (existing == null) {
			return null;
		}

		if (updatepost.getContent() != null) {
			existing.setContent(updatepost.getContent());
		}
		return forumRepository.save(existing);

	}

	@Override
	public boolean addLike(String id) {
		Post post = forumRepository.findById(Integer.valueOf(id)).orElse(null);
		if (post == null) {
			return false;
		}
		post.addLikes();
		forumRepository.save(post);
		return true;
	}

	@Override
	public Post addComment(String id, NewCommentDto newComment) {
		Post post = forumRepository.findById(Integer.valueOf(id)).orElse(null);
		if (post == null) {
			return null;
		}
		Comment comment = new Comment();
		comment.setMessage(newComment.getMessage());
		comment.setUser(newComment.getUser());
		post.addComments(comment);
		return forumRepository.save(post);
	}

	@Override
	public Stream<Post> findByTags(List<String> tags) {
		return forumRepository.findByTagsIn(tags);
	}

	@Override
	public Stream<Post> findByAuthor(String author) {
		
		return forumRepository.findByAuthor(author);
	}

	@Override
	public Stream<Post> findByDate(DatePeriodDto period) {
		
		return null;//forumRepository.findByDateCreatedBetween(from, to);
	}

}
