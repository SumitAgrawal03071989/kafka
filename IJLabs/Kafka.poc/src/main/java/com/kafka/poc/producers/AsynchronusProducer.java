package com.kafka.poc.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;



public class AsynchronusProducer {

    public static void main(String[] args){

        System.out.println("Hello There");

        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "0:9092");
        kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer<String, String>(kafkaProps);



        long testcont = 0;

        try {
            while(true) {

                ProducerRecord<String, String> record = new ProducerRecord<String, String>("test","kannu->"+testcont++);
                producer.send(record,new DemoProducerCallback());
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
