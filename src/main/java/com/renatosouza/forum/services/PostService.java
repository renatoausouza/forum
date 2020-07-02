package com.renatosouza.forum.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatosouza.forum.domain.Post;
import com.renatosouza.forum.repository.PostRepository;
import com.renatosouza.forum.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public List<Post> findAll(){
		return repo.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("There is no post with the id equal to " + id + "."));
	}
	
	public Post insert(Post obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Post newObj, Post obj) {
		newObj.setAuthor(obj.getAuthor());
		newObj.setBody(obj.getBody());
		newObj.setDate(obj.getDate());
		newObj.setTitle(obj.getTitle());
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
		//return repo.findByTitleContaining(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
