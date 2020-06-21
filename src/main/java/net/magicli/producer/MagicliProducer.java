package net.magicli.producer;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class MagicliProducer {

    private static final Logger logger = LoggerFactory.getLogger(MagicliProducer.class);

    public void pushMsg(String msg) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "PLAINTEXT://127.0.0.1:9091,PLAINTEXT://127.0.0.1:9092,PLAINTEXT://127.0.0.1:9093");
        props.put("acks", "0");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer(props);
        ProducerRecord<String, String> record = new ProducerRecord<>("test_01", "123", msg);

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e != null) {
                    e.printStackTrace();
                }
                logger.info("pushMsg of msg: {}, metadata: {}", msg, metadata);
            }
        });
        producer.close();
    }
}
