package com.procesador.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.procesador.domain.File;
import com.procesador.domain.File.Status;

	@Service
	public class ProcesadorService {
		
		static final String uriEditFileMongo= "http://localhost:8083/api/files/edit";

		//Método que procesa el fichero
		public File processedFile (File file) {
			
			File processed_file = file;
			
			//Recorremos el array de Keywords
			for(String keyword : file.getKeywords()) {
				System.out.println(keyword);
				
				//Si un keyword es error actualizamos el estado en el fichero
				if(keyword.equals("error")) {
					processed_file.setState(Status.ERRONEO);
				}
				
				//Si no simulamos un tiempo de procesado en función del tamaño del fichero
				else {
					//Simulamos tiempo de ejecución en función del tamaño del archivo
					if(processed_file.getSize()<=100) {
						try {
				        
				            Thread.sleep(500);
				         } catch (Exception e) {
				            System.out.println(e);
				         }
					}
					
					else if(processed_file.getSize()>100 && processed_file.getSize()<=200) {
						try {
				        
				            Thread.sleep(1000);
				         } catch (Exception e) {
				            System.out.println(e);
				         }
					}
					
					else if(processed_file.getSize()>200 && processed_file.getSize()<=300) {
						try {
				        
				            Thread.sleep(1500);
				         } catch (Exception e) {
				            System.out.println(e);
				         }
					}
					
					else if(processed_file.getSize()>300 && processed_file.getSize()<=400) {
						try {
				        
				            Thread.sleep(2000);
				         } catch (Exception e) {
				            System.out.println(e);
				         }
					}
					
					else if(processed_file.getSize()>400 && processed_file.getSize()<=500) {
						try {
				        
				            Thread.sleep(2500);
				         } catch (Exception e) {
				            System.out.println(e);
				         }
					}
					//Obtenemos la fecha y hora actual
					LocalDateTime added_date = LocalDateTime.now();
					
					//Actualizamos el campo added_date del fichero con la fecha de publicación
					processed_file.setDate(added_date);
					
					//Cambiamos el estado del fichero a PUBLICADO
					processed_file.setState(Status.PUBLICADO);
				}
			}
			
			//Actualizamos el fichero en MongoDB
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.put(
					uriEditFileMongo,
					processed_file,
					File.class);
				
			return processed_file;
		}
	}
