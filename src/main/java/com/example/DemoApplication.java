package com.example;

import com.example.accessingdatajpa.Customer;
import com.example.accessingdatajpa.CustomerRepository;
import com.example.fileuploader.storage.StorageProperties;
import com.example.fileuploader.storage.StorageService;
import com.example.jms.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(StorageProperties.class)
@EnableJms
@EnableScheduling
@EnableAsync
public class DemoApplication {

    public static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

    @Bean
    CommandLineRunner listBeans(final ApplicationContext ctx) {
        return args -> {
            final String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                LOG.info("bean name: " + beanDefinitionName);
            }
        };
    }

    @Bean
    CommandLineRunner jpademo(CustomerRepository repository) {
        return (args) -> {
            if (repository.count() == 0) {
                repository.save(new Customer("Alan", "Walker"));
                repository.save(new Customer("Jack", "Bauer"));
                repository.save(new Customer("Chloe", "O'Brian"));
                repository.save(new Customer("Kim", "Bauer"));
                repository.save(new Customer("David", "Palmer"));
                repository.save(new Customer("Michelle", "Dessler"));
            }

            LOG.info("Customers found with findAll():");
            LOG.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                LOG.info(customer.toString());
            }
            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            LOG.info("Customer found with findById(1L):");
            LOG.info("--------------------------------");
            LOG.info(customer.toString());
            LOG.info("");

            // fetch customers by last name
            LOG.info("Customer found with findByLastName('Bauer'):");
            LOG.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                LOG.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  LOG.info(bauer.toString());
            // }
            LOG.info("");
        };
    }

    @Bean
    CommandLineRunner sendJmsMessage(final ApplicationContext ctx) {
        return args -> {
            final JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
            LOG.info("Sending Email instance via JMS");
            jmsTemplate.setPubSubDomain(true);
            jmsTemplate.convertAndSend("mailbox", new Email("grzesk@example.com", "My Message"));
        };
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
