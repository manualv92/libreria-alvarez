package com.manuel; /**
 * Created by manua on 11/8/2016.
 */

import com.manuel.model.Cliente;
import com.manuel.model.TipoDocumento;
import com.manuel.model.TipoPersona;
import com.manuel.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.manuel")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
/*
    @Bean
    public CommandLineRunner demo(ClienteRepository repository) {
        return (args) -> {
            // save a couple of clientes

            repository.save(new Cliente("manuel1","alvarez1","misoines1","asd@gmail.com1",true,123,1234,1235451,123143));
            repository.save(new Cliente("manuel2","alvarez2","misoines2","asd@gmail.com2",true,123,1234,1235451,123143));
            repository.save(new Cliente("manuel3","alvarez3","misoines3","asd@gmail.com3",true,123,1234,1235451,123143));
            repository.save(new Cliente("manuel4","alvarez4","misoines4","asd@gmail.com4",true,123,1234,1235451,123143));
            repository.save(new Cliente("manuel","alvarez","misoines","asd@gmail.com",1,new TipoDocumento(1,"DNI"),new TipoPersona(1,"Fisica"),1235451,123143));
            repository.save(new Cliente("manuel","alvarez","misoines","asd@gmail.com",1,new TipoDocumento(1,"DNI"),new TipoPersona(2,"Juridica"),1235451,123143));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }*/
}

