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
		
		postRepository.saveAll(Arrays.asList(post1));
		
		renato.getPosts().addAll(Arrays.asList(post1));
		userRepository.save(renato);
	}

}
