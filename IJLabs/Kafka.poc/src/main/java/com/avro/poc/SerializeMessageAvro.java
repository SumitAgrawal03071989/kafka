package com.kafka.poc.producers;

import com.oracle.tools.packager.IOUtils;
import example.avro.User;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.Properties;


public class SerializeMessageAvro {

    public static void main(String[] args) throws IOException{

        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
        user1.setFavoriteColor("Pink");

        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), new File("/Users/sumitagrawal/PSpace/Projects/kafka/IJLabs/Kafka.poc/src/main/avro/users.avro"));
        dataFileWriter.append(user1);

        dataFileWriter.close();

        File file = new File("/Users/sumitagrawal/PSpace/Projects/kafka/IJLabs/Kafka.poc/src/main/avro/users.avro");
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
// Reuse user object by passing it to next(). This saves us from
// allocating and garbage collecting many objects for files with
// many items.
            user = dataFileReader.next(user);
            System.out.println(user);
        }


    }

}
