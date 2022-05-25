package com.api_validators;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

//Configuramos el escenario aqui porque solo lo lanzamos una vez
@Configuration
public class BroadcastConfig {
	
	private static final boolean NON_DURABLE = false;

	public final static String TOPIC_QUEUE_NAME = "VALIDADOR";
	public final static String TOPIC_EXCHANGE_NAME = "miExchange";
	public static final String BINDING_PATTERN_VALIDADOR= "*.validador";

	@Bean
	//Método que devuelve un objeto del tipo declarable. en estos declarables vamos a tener en un caso la config del Topic y en otra la del fanout
	public Declarables topicBindings() {
		Queue topicQueue1 = new Queue(TOPIC_QUEUE_NAME, NON_DURABLE);
	
		TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME, NON_DURABLE, false);

		return new Declarables(topicQueue1, topicExchange,
				BindingBuilder.bind(topicQueue1).to(topicExchange).with(BINDING_PATTERN_VALIDADOR));	
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
	
}
