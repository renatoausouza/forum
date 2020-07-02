package com.renatosouza.forum.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.renatosouza.forum.domain.Post;
import com.renatosouza.forum.domain.User;
import com.renatosouza.forum.dto.AuthorDTO;
import com.renatosouza.forum.dto.CommentDTO;
import com.renatosouza.forum.repository.PostRepository;
import com.renatosouza.forum.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User renato = new User(null, "Renato Souza", "renato@gmail.com");
		User henrique = new User(null, "Henrique Manata", "henrique@gmail.com");
		User ellen = new User(null, "Ellen Souza", "ellen@gmail.com");
		User eloa = new User(null, "Eloá Souza", "eloa@gmail.com");
		User marco = new User(null, "Marco Antonio", "marco@gmail.com");
		User cassia = new User(null, "Cassia Souza", "cassia@gmail.com");
		User helio = new User(null, "Hélio Souza", "helio@gmail.com");

		userRepository.saveAll(Arrays.asList(renato, henrique, ellen, eloa, marco, cassia, helio));
		
		Post post1 = new Post(null, sdf.parse("30/06/2020"), "What's up?!", "Hey guys! I hope u all are going...", new AuthorDTO(renato));
		Post post2 = new Post(null, sdf.parse("30/06/2020"), "Hey", "Hey guys! I hope u all are going...", new AuthorDTO(renato));
		Post post3 = new Post(null, sdf.parse("30/06/2020"), "Good Morning!", "She is one year old, so... heluieaheuaehuaeh heusheu ", new AuthorDTO(eloa));
		Post post4 = new Post(null, sdf.parse("30/06/2020"), "Hello", "He is one year old, so... bababa lala blabadala ", new AuthorDTO(marco));
		
		CommentDTO c1 = new CommentDTO("so sweat", sdf.parse("30/06/2020"), new AuthorDTO(renato));
		CommentDTO c2 = new CommentDTO("my baby", sdf.parse("30/06/2020"), new AuthorDTO(ellen));
		CommentDTO c3 = new CommentDTO("so beautiful", sdf.parse("30/06/2020"), new AuthorDTO(cassia));
		
		post3.getComments().addAll(Arrays.asList(c3));
		post4.getComments().addAll(Arrays.asList(c2));
		post1.getComments().addAll(Arrays.asList(c1));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));
		
		renato.getPosts().addAll(Arrays.asList(post1));
		renato.getPosts().addAll(Arrays.asList(post2));
		eloa.getPosts().addAll(Arrays.asList(post3));
		marco.getPosts().addAll(Arrays.asList(post4));
		userRepository.saveAll(Arrays.asList(renato, eloa, marco));
	}

}
