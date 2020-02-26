package com.kafka.poc.producers;

import com.avro.poc.User;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerWithCustomClass {

    public static void main(String[] args){

        System.out.println("Hello There");

        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "0:9092");
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        kafkaProps.put("schema.registry.url", "http://localhost:8081");
        KafkaProducer<String, User> producer = new KafkaProducer<String, User>(kafkaProps);

        String key = "key1";

        User u1 = new User();
        u1.setName("Sumit");
        u1.setFavoriteColor("Red");

        int testNumber=0;

        try {
            while(true) {
                u1.setName("Sumit"+testNumber++);
                ProducerRecord<String, User> record = new ProducerRecord<>("topic1", key, u1);
                producer.send(record);
                Thread.sleep(500);
                System.out.println("Sent");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            producer.flush();
            producer.close();
        }


    }

}
