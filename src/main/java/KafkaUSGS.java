/**
 * Created by Isaac Godfried on 1/27/2017.
 */
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.FileInputStream;
import java.util.Properties;

public class KafkaUSGS {
    public static void main(String [ ] args){
        System.out.println("hello this jar is working");
        Properties props = initProp("someFile.properties");

    }
    public static Properties initProp(String settingsPath){
        Properties props = new Properties();
        return props;



    }


}
