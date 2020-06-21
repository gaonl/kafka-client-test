1、启动kafka服务
bin/kafka-server-start.sh config/server.properties &

2、停止kafka服务
./kafka-server-stop.sh 

3、查看所有的话题
./kafka-topics.sh --list --zookeeper localhost:9092

4、查看所有话题的详细信息
./kafka-topics.sh --zookeeper localhost:2181 --describe

5、列出指定话题的详细信息
./kafka-topics.sh --zookeeper localhost:2181 --describe  --topic demo

6、删除一个话题
./kafka-topics.sh --zookeeper localhost:2181 --delete  --topic test

7、创建一个叫test的话题，有两个分区，每个分区3个副本
./kafka-topics.sh --zookeeper localhost:2181 --create --topic test --replication-factor 3 --partitions 2

 8、测试kafka发送和接收消息（启动两个终端）
#发送消息（注意端口号为配置文件里面的端口号）
./kafka-console-producer.sh --broker-list localhost:9092 --topic test
#消费消息（可能端口号与配置文件保持一致，或与发送端口保持一致）
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning   #加了--from-beginning 重头消费所有的消息
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test         #不加--from-beginning 从最新的一条消息开始消费

9、查看某个topic对应的消息数量
./kafka-run-class.sh  kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic test --time -1

10、显示所有消费者
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

11、获取正在消费的topic（console-consumer-63307）的group的offset
./kafka-consumer-groups.sh --describe --group console-consumer-63307 --bootstrap-server localhost:9092

11、显示消费者
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list


















## 创建主题（4个分区，2个副本）
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 2 --partitions 4 --topic test


## 查询集群描述
bin/kafka-topics.sh --describe --zookeeper 

## topic列表查询
bin/kafka-topics.sh --zookeeper 127.0.0.1:2181 --list

## topic列表查询（支持0.9版本+）
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

## 新消费者列表查询（支持0.9版本+）
bin/kafka-consumer-groups.sh --new-consumer --bootstrap-server localhost:9092 --list

## 新消费者列表查询（支持0.10版本+）
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

## 显示某个消费组的消费详情（仅支持offset存储在zookeeper上的）
bin/kafka-run-class.sh kafka.tools.ConsumerOffsetChecker --zookeeper localhost:2181 --group test

## 显示某个消费组的消费详情（0.9版本 - 0.10.1.0 之前）
bin/kafka-consumer-groups.sh --new-consumer --bootstrap-server localhost:9092 --describe --group test-consumer-group

## 显示某个消费组的消费详情（0.10.1.0版本+）
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group my-group


## 生产者
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

## 消费者
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test

## 新生产者（支持0.9版本+）
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test --producer.config config/producer.properties

## 新消费者（支持0.9版本+）
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --new-consumer --from-beginning --consumer.config config/consumer.properties

## 高级点的用法
bin/kafka-simple-consumer-shell.sh --brist localhost:9092 --topic test --partition 0 --offset 1234  --max-messages 10

## 平衡leader
bin/kafka-preferred-replica-election.sh --zookeeper zk_host:port/chroot

## 自带压测命令
bin/kafka-producer-perf-test.sh --topic test --num-records 100 --record-size 1 --throughput 100  --producer-props bootstrap.servers=localhost:9092

## 扩容分区
bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic topic1 --partitions 2

## 迁移分区
1. 创建规则json
cat > increase-replication-factor.json <<EOF
{"version":1, "partitions":[
{"topic":"__consumer_offsets","partition":0,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":1,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":2,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":3,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":4,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":5,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":6,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":7,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":8,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":9,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":10,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":11,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":12,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":13,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":14,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":15,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":16,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":17,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":18,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":19,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":20,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":21,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":22,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":23,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":24,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":25,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":26,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":27,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":28,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":29,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":30,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":31,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":32,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":33,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":34,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":35,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":36,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":37,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":38,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":39,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":40,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":41,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":42,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":43,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":44,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":45,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":46,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":47,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":48,"replicas":[0,1]},
{"topic":"__consumer_offsets","partition":49,"replicas":[0,1]}]
}
EOF

2.执行
bin/kafka-reassign-partitions.sh --zookeeper localhost:2181 --reassignment-json-file increase-replication-factor.json --execute

3.验证
bin/kafka-reassign-partitions.sh --zookeeper localhost:2181 --reassignment-json-file increase-replication-factor.json --verify









