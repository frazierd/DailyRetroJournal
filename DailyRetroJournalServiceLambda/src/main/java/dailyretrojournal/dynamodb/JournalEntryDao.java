package dailyretrojournal.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import dailyretrojournal.dynamodb.models.JournalEntry;
import dailyretrojournal.exceptions.JournalEntryNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

//public class JournalEntryDao {
//
//    private final DynamoDBMapper mapper;
//    private final Logger log = LogManager.getLogger();
//
//    @Inject
//    public JournalEntryDao(DynamoDBMapper mapper) {
//        this.mapper = mapper;
//    }
//
//    public List<JournalEntry> getAllJournalEntries() {
//        DynamoDBScanExpression entryScanner = new DynamoDBScanExpression();
//        log.fatal("this shows that entryScanner works{}" + entryScanner);
//        List<JournalEntry> entryScanResult = mapper.scan(JournalEntry.class, entryScanner);
//        for (JournalEntry entry : entryScanResult) {
//            log.fatal("entryScanResult {}", entry.toString());
//        }
//        return entryScanResult;
//    }
//
//
//    public JournalEntry getJournalEntry(String entryId) {
//        JournalEntry journalEntry = this.mapper.load(JournalEntry.class, entryId);
//
//        log.fatal("got journalEntry {}", journalEntry);
//        if(journalEntry == null){
//            throw new JournalEntryNotFoundException("Could not find journal entry wit id " + entryId);
//        }
//        return journalEntry;
//    }
//
//    public JournalEntry removeJournalEntry(String entryId) {
//        JournalEntry journalEntryToRemove;
//        try {
//            journalEntryToRemove = getJournalEntry(entryId);
//        }catch (JournalEntryNotFoundException e) {
//            throw new JournalEntryNotFoundException(String.format("Journal entry was not deleted because the Journal entry archive does not contain an entry with id '%s'", entryId), e);
//        }
//        log.info("getJournalEntry was called on {} and returned {}", entryId, journalEntryToRemove);
//        this.mapper.delete(journalEntryToRemove);
//        log.info("delete method was called on {}", journalEntryToRemove);
//        return journalEntryToRemove;
//    }
//
//    public JournalEntry updateJournalEntry(String entryId) {
//        JournalEntry updateEntry;
//        try{
//            updateEntry = getJournalEntry(entryId);
//        } catch (JournalEntryNotFoundException e) {
//            throw new JournalEntryNotFoundException(String.format("Did not update because could not find journal entry id '%s'", entryId), e);
//        }
//        this.mapper.update(updateEntry);
public class JournalEntryDao {

    private final DynamoDBMapper mapper;
    private final Logger log = LogManager.getLogger();

    @Inject
    public JournalEntryDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public List<JournalEntry> getAllJournalEntries() {
        DynamoDBScanExpression entryScanner = new DynamoDBScanExpression();
        log.fatal("this shows that entryScanner works{}" + entryScanner);
        List<JournalEntry> entryScanResult = mapper.scan(JournalEntry.class, entryScanner);
        for (JournalEntry entry : entryScanResult) {
            log.fatal("entryScanResult {}", entry.toString());
        }
        return entryScanResult;
    }


    public JournalEntry getJournalEntry(String entryId) {
        JournalEntry journalEntry = this.mapper.load(JournalEntry.class, entryId);

        log.fatal("got journalEntry {}", journalEntry);
        if(journalEntry == null){
            throw new JournalEntryNotFoundException("Could not find journal entry wit id " + entryId);
        }
        return journalEntry;
    }

    public JournalEntry removeJournalEntry(String entryId) {
        JournalEntry journalEntryToRemove;
        try {
            journalEntryToRemove = getJournalEntry(entryId);
        }catch (JournalEntryNotFoundException e) {
            throw new JournalEntryNotFoundException(String.format("Journal entry was not deleted because the Journal entry archive does not contain an entry with id '%s'", entryId), e);
        }
        log.info("getJournalEntry was called on {} and returned {}", entryId, journalEntryToRemove);
        this.mapper.delete(journalEntryToRemove);
        log.info("delete method was called on {}", journalEntryToRemove);
        return journalEntryToRemove;
    }

    public JournalEntry updateJournalEntry(String entryId) {
        JournalEntry updateEntry;
        try{
            updateEntry = getJournalEntry(entryId);
        } catch (JournalEntryNotFoundException e) {
            throw new JournalEntryNotFoundException(String.format("Did not update because could not find journal entry id '%s'", entryId), e);
        }
        this.mapper.save(updateEntry);

        return updateEntry;
    }

    public JournalEntry saveJournalEntry(String content, String dateEntered, List<String> hashtag) {
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setId(String.valueOf(UUID.randomUUID()));
        journalEntry.setContent(content);
        journalEntry.setDateEntered(dateEntered);
        journalEntry.setHashtag(hashtag);
        this.mapper.save(journalEntry);
        return journalEntry;
    }
}


