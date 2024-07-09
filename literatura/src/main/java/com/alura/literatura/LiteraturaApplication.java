package com.alura.literatura;
import com.alura.literatura.repository.LibroRepository;
//import com.alura.literatura.service.LibroService;
import com.alura.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alura.literatura.principal.Principal;


@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner{

	@Autowired
	private LibroRepository repository;

	@Autowired
	private LibroService libroService;

	public static void main(String[] args){
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, libroService);
		principal.muestraElMenu();
	}
}