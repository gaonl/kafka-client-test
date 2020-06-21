package net.magicli.producer;

import net.magicli.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MagicliProducerTest {

    private static final Logger logger = LoggerFactory.getLogger(MagicliProducerTest.class);

    @Test
    public void pushMessage() throws Exception {
        MagicliProducer magicliProducer = new MagicliProducer();


        magicliProducer.pushMsg("test_test_test_test");
    }
}
