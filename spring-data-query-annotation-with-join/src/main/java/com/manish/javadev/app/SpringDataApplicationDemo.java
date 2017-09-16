package com.manish.javadev.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.manish.javadev.app.entities.RoleEntity;
import com.manish.javadev.app.entities.UserEntity;
import com.manish.javadev.repository.UserRepository;

@SpringBootApplication
public class SpringDataApplicationDemo implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplicationDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		saveAccountData();
		findUser();
	}

	private void findUser() {
		List<String[]> userEntity = userRepository.getAllRecord();
		System.out.println("Done" + userEntity.size());

		for (Object[] strings : userEntity) {
			System.out.println("\nUser Name " + strings[0] + "\t" + "\tUser Role " + strings[1]);
		}

		// System.out.println("\nUser Name " + userEntity[0] + "\t" + "\tUser
		// Role " + userEntity[1]);

	}

	private void saveAccountData() {

		Set<RoleEntity> roles = new HashSet<RoleEntity>();
		RoleEntity roleEntity1 = new RoleEntity("ROLE_ADMIN");
		RoleEntity roleEntity2 = new RoleEntity("ROLE_USER");

		roles.add(roleEntity1);
		roles.add(roleEntity2);

		UserEntity userEntity1 = new UserEntity("msmanish95", "P@ssw0rd", true);
		userEntity1.getRoles().addAll(roles);

		roleEntity1.setUsers(userEntity1);
		roleEntity2.setUsers(userEntity1);

		UserEntity userEntity2 = new UserEntity("msmanish96", "P@ssw0rd", true);

		userEntity2.getRoles().add(roleEntity2);
		roleEntity2.setUsers(userEntity2);

		userRepository.save(userEntity1);
		userRepository.save(userEntity2);
		System.out.println("Done");

	}
}