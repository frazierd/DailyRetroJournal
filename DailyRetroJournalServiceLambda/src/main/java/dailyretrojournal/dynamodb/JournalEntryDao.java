package dailyretrojournal.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dailyretrojournal.dynamodb.models.JournalEntry;
import dailyretrojournal.exceptions.JournalEntryNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class JournalEntryDao {

    private final DynamoDBMapper mapper;
    private final Logger log = LogManager.getLogger();

    @Inject
    public JournalEntryDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public JournalEntry getJournalEntry(String id) {
        JournalEntry journalEntry = this.mapper.load(JournalEntry.class, id);

        if(journalEntry == null){
            throw new JournalEntryNotFoundException("Could not find journal entry wit id " + id);
        }
        return journalEntry;
    }

    public JournalEntry removeJournalEntry(String id) {
        JournalEntry journalEntryToRemove;
        try {
            journalEntryToRemove = getJournalEntry(id);
        }catch (JournalEntryNotFoundException e) {
            throw new JournalEntryNotFoundException(String.format("Journal entry was not deleted because the Journal entry archive does not contain an entry with id '%s'", id), e);
        }
        log.info("getJournalEntry was called on {} and returned {}", id, journalEntryToRemove);
        this.mapper.delete(journalEntryToRemove);
        log.info("delete method was called on {}", journalEntryToRemove);
        return journalEntryToRemove;
    }

    public JournalEntry saveJournalEntry(JournalEntry journalEntry) {
        this.mapper.save(journalEntry);
        return journalEntry;
    }
}
