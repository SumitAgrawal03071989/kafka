package com.kafka.poc.consumers;

import com.avro.poc.User;
import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerWithCustomClass {

    public static void main(String args[]){

        Properties kafkaProps = new Properties();
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "0:9092");
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
        kafkaProps.put("schema.registry.url", "http://localhost:8081");

        kafkaProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        String topic = "topic1";

        final Consumer<String, User> consumer = new KafkaConsumer<String, User>(kafkaProps);
        consumer.subscribe(Arrays.asList(topic));

        try {
            while (true) {
                ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(10000));
                for (ConsumerRecord<String, User> record : records) {

                    System.out.println(record.value());

                }
            }
        } finally {
            consumer.close();
        }

    }
}
