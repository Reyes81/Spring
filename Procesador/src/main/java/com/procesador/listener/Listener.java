package com.procesador.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.procesador.domain.File;
import com.procesador.services.ProcesadorService;

@Component
public class Listener {
	
	@Autowired
	ProcesadorService ps;
	
	
	//Procesador recoge el fichero de la cola "Validador" del Exchange de Rabbit
	@RabbitListener(queues="VALIDADOR")
    public void processFile(File file) {
    	ps.processedFile(file);
    }

}
