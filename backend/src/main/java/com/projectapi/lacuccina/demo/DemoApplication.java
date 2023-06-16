package com.projectapi.lacuccina.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.projectapi.lacuccina.demo.model.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {

		MongoOperations mongoOps = new MongoTemplate(MongoClients.create("mongodb+srv://milena:ProjLacuccina@lacuccina.a9vdzqh.mongodb.net/"), "lacuccina");
		//mongoOps.insert(new Menu("Creme de leite fresco, queijo parmesão ralado e manteiga", "Fetuccine Alfredo", 29, "../../res/drawable/fetuccine.jpeg"));

		List<Menu> menu =  mongoOps.findAll(Menu.class);
		System.out.println("Registros no menu:" + menu);
	}

}
