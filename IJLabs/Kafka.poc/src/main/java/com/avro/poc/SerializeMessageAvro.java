package com.avro.poc;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.*;


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
