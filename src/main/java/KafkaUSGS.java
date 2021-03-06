/**
 * Created by Isaac Godfried on 1/27/2017.
 */
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaUSGS {
    public static void main(String [ ] args){
        System.out.println("Welcome to the Kafka jar");
        Properties props = initProp("someFile.properties");
        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(props);
        //Initialize list of urls of streams. This would probably be loaded from a file or directly form a DB in production.
        List<String> s = new ArrayList<String>();
        s.add("https://waterservices.usgs.gov/nwis/iv/?format=json&sites=01037000&parameterCd=00060");
        s.add("http://waterservices.usgs.gov/nwis/iv/?format=json&sites=14182500&parameterCd=00060");
        //Send Kafka the message
        List<String> jsonString = getDataString(s);
        for(String message:jsonString){
            ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>("test", "a river", message);
            producer.send(producerRecord);
        }
        producer.close();

    }
    //Initialize properties for Kafka Producer (optionally load properties from a file)
    private static Properties initProp(String settingsPath){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return props;
    }
    //Get list of JSON Strings from USGS API
    private static List<String> getDataString(List<String> urls){
        ArrayList<String> completeResults = new ArrayList<String>();
        for(String url:urls) {
            String results = "";
            try {
                URL address = new URL(url);
                BufferedReader reader = new BufferedReader(new InputStreamReader(address.openStream()));
                String line;

                while ((line = reader.readLine()) != null)
                {
                    results+= line;
                }
            } catch (Exception s) {
                s.printStackTrace();
            }
            completeResults.add(results);
        }

        return completeResults;
    }


}
