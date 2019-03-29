package com.jms;
import javax.jms.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import com.jms.dm.SheepData;


// this is spring boot JMS messaging within the JVM
// https://spring.io/guides/gs/messaging-jms/

@SpringBootApplication
@EnableJms  // goes off and looks for methods annotated with @JmsListener 
public class JmsDemoApplication {
	
	// JMS needs this JMS listener container factory bean to be in context
	@Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        // This provides all boot's default functionality to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        return factory;
    }

	
	// JMS needs this message converter bean to be in context. for serializing the message content to JSON using TextMessage 
    @Bean 
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
	
	
	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JmsDemoApplication.class, args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // Send a message - the template reuses the message converter
        System.out.println("Sending an email message.");
        jmsTemplate.convertAndSend("databox", new SheepData("Dolly", "white"));
	}
	

}
