package net.magicli.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.util.*;

public class MagicliConsumer {
    public void consumerMessage() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "PLAINTEXT://127.0.0.1:9091,PLAINTEXT://127.0.0.1:9092,PLAINTEXT://127.0.0.1:9093");
        props.put("group.id", "1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test_01"));
        consumer.seekToBeginning(new ArrayList<>());

        // ===== 拿到所有的topic ===== //
        Map<String, List<PartitionInfo>> listTopics = consumer.listTopics();
        Set<Map.Entry<String, List<PartitionInfo>>> entries = listTopics.entrySet();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000 * 60);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("[fetched from partition " + record.partition() + ", offset: " + record.offset() + ", message: " + record.value() + "]");
            }
        }
    }
}
