package com.kafka.poc.producers;

import org.apache.kafka.clients.producer.RecordMetadata;

class DemoProducerCallback implements org.apache.kafka.clients.producer.Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
    }
}