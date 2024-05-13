package dev.aloysius.librarymanagementsystem;

import dev.aloysius.librarymanagementsystem.Domains.ContactInformation;
import dev.aloysius.librarymanagementsystem.Domains.Patrons;
import dev.aloysius.librarymanagementsystem.Domains.Roles;
import dev.aloysius.librarymanagementsystem.Repositories.ContactInformationRepository;
import dev.aloysius.librarymanagementsystem.Repositories.PatronsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);

	}
//	@Bean
//	CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder, PatronsRepository patronsRepository, ContactInformationRepository contactInformationRepository){
//		return args -> {
//			ContactInformation c1 = new ContactInformation();
//			c1.setCountry("United Arab Emirate");
//			c1.setStreetName("Khalifa Boulevard");
//			c1.setTelephone("12345678");
//			c1.setProvince("Dubai");
//			contactInformationRepository.save(c1);
//
//			Patrons admin = new Patrons();
//			admin.setEmail("admin.lms");
//			admin.setRoles(Roles.ADMIN);
//			admin.setUserPassword(passwordEncoder.encode("secret"));
//			admin.setFirstName("John");
//			admin.setLastName("Doe");
//			admin.setPatronId("ERTG");
//			admin.setContactInformation(c1);
//
//			patronsRepository.save(admin);
//		};
//	}


}
