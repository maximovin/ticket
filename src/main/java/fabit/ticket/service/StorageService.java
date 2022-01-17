package fabit.ticket.service;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import fabit.ticket.config.S3Properties;
import fabit.ticket.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.prefs.Preferences;

@Service
public class StorageService {

    String idKey = "idKey";

    @Autowired
    private S3Properties s3Properties;
    @Autowired
    private AmazonS3 s3;
    @Autowired
    private ObjectMapper objectMapper;

    public void create(Ticket ticket) {
        checkBucket();
        try {
            ticket.setId(generateId());
            s3.putObject(s3Properties.getBucket(), ticket.getId().toString(), objectMapper.writeValueAsString(ticket));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void checkBucket() {
        if (!s3.doesBucketExist(s3Properties.getBucket())) {
            s3.createBucket(s3Properties.getBucket());
        }
    }

    public Ticket read(Long id) {
        try {
            return objectMapper.readValue(s3.getObjectAsString(s3Properties.getBucket(), id.toString()), Ticket.class);
        } catch (Exception e) {

        }
        return null;
    }

    private Long generateId() {
        Preferences prefs = Preferences.userNodeForPackage(this.getClass());
        Long result = prefs.getLong(idKey, 0);
        result++;
        prefs.putLong(idKey, result);
        return result;
    }
}
