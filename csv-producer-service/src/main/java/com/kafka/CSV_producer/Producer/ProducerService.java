package com.kafka.CSV_producer.Producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.CSV_producer.DTO.StudentRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class ProducerService {
    private final KafkaTemplate<String, String> template;
    private final ObjectMapper mapper = new ObjectMapper();

    public ProducerService(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public CompletableFuture<Void> send(String topic, StudentRecord rec) {
        try {
            String json = mapper.writeValueAsString(rec);
            String key = String.valueOf(rec.getStudentId());

            return template
                    .send(topic, key, json)  // returns CompletableFuture<SendResult<K, V>>
                    .thenApply(r -> null);  // convert to CompletableFuture<Void>
        } catch (Exception e) {
            CompletableFuture<Void> f = new CompletableFuture<>();
            f.completeExceptionally(e);
            return f;
        }
    }
}
