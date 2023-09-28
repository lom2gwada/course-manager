package com.pmu.course.services;

import org.springframework.stereotype.Service;

/**
 * Service class for producing messages to Kafka.
 */
@Service
public class KafkaProducerService {

	/*@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;*/

    /**
     * Sends a message to the specified Kafka topic.
     *
     * @param topic   The name of the Kafka topic.
     * @param message The message to be sent.
     * 
     */
	public void sendMessage(String topic, String message) {
		//this.kafkaTemplate.send(topic, message);
	}
}

