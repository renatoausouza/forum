package com.renatosouza.forum.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatosouza.forum.domain.Post;
import com.renatosouza.forum.domain.User;
import com.renatosouza.forum.dto.UserDTO;
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

//	public Post fromDTO(UserDTO objDTO) {
//		return new User(objDTO.getId(),objDTO.getName(),objDTO.getEmail());
//	}
}
