package com.example.komponente_spring.runner;

import com.example.komponente_spring.domain.Admin;
import com.example.komponente_spring.domain.Client;
import com.example.komponente_spring.domain.Manager;
import com.example.komponente_spring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;

@Configuration
public class AppRunner {
    private UserRepository userRepository;

    public AppRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    CommandLineRunner loadData(){               // ovde punimo bazu podacima, desava se pre pokretanja
        return args -> {
            // Admins
            Admin admin1 = new Admin();
            admin1.setUsername("admin");
            admin1.setPassword("admin");
            admin1.setEmail("admin@admin.com");
            admin1.setFirstName("Admin");
            admin1.setLastName("Admin");
            admin1.setDateOfBirth(Date.valueOf(LocalDate.of(1990, 1, 1)));
            userRepository.save(admin1);

            Admin admin2 = new Admin();
            admin2.setUsername("admin2");
            admin2.setPassword("admin2");
            admin2.setEmail("admin2@admin.com");
            admin2.setFirstName("Admin2");
            admin2.setLastName("Admin2");
            admin2.setDateOfBirth(Date.valueOf(LocalDate.of(1990, 1, 1)));
            userRepository.save(admin2);

            Admin admin3 = new Admin();
            admin3.setUsername("admin3");
            admin3.setPassword("admin3");
            admin3.setEmail("admin3@admin.com");
            admin3.setFirstName("Admin3");
            admin3.setLastName("Admin3");
            admin3.setDateOfBirth(Date.valueOf(LocalDate.of(1995, 6, 15)));
            userRepository.save(admin3);

            // Clients
            Client client1 = new Client();
            client1.setUsername("client");
            client1.setPassword("client");
            client1.setEmail("client@client.com");
            client1.setFirstName("Client");
            client1.setLastName("Client");
            client1.setDateOfBirth(Date.valueOf(LocalDate.of(1990, 1, 1)));
            client1.setNumberOfReservations(0);
            userRepository.save(client1);

            Client client2 = new Client();
            client2.setUsername("client2");
            client2.setPassword("client2");
            client2.setEmail("client2@client.com");
            client2.setFirstName("Client2");
            client2.setLastName("Client2");
            client2.setDateOfBirth(Date.valueOf(LocalDate.of(1992, 3, 20)));
            client2.setNumberOfReservations(0);
            userRepository.save(client2);

            Client client3 = new Client();
            client3.setUsername("client3");
            client3.setPassword("client3");
            client3.setEmail("client3@client.com");
            client3.setFirstName("Client3");
            client3.setLastName("Client3");
            client3.setDateOfBirth(Date.valueOf(LocalDate.of(1998, 9, 10)));
            client3.setNumberOfReservations(0);
            userRepository.save(client3);

            // Managers
            Manager manager1 = new Manager();
            manager1.setUsername("manager");
            manager1.setPassword("manager");
            manager1.setEmail("manager@manager.com");
            manager1.setFirstName("Manager");
            manager1.setLastName("Manager");
            manager1.setDateOfBirth(Date.valueOf(LocalDate.of(1980, 5, 25)));
            userRepository.save(manager1);

            Manager manager2 = new Manager();
            manager2.setUsername("manager2");
            manager2.setPassword("manager2");
            manager2.setEmail("manager2@manager.com");
            manager2.setFirstName("Manager2");
            manager2.setLastName("Manager2");
            manager2.setDateOfBirth(Date.valueOf(LocalDate.of(1985, 11, 1)));
            userRepository.save(manager2);

            Manager manager3 = new Manager();
            manager3.setUsername("manager3");
            manager3.setPassword("manager3");
            manager3.setEmail("manager3@manager.com");
            manager3.setFirstName("Manager3");
            manager3.setLastName("Manager3");
            manager3.setDateOfBirth(Date.valueOf(LocalDate.of(1990, 7, 15)));
            userRepository.save(manager3);
        };
    }
}