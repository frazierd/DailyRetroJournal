package dailyretrojournal.activity;

import dailyretrojournal.activity.request.CreateNewJournalEntryRequest;
import dailyretrojournal.activity.result.CreateNewJournalEntryResult;
import dailyretrojournal.converters.ModelConverter;
import dailyretrojournal.dynamodb.JournalEntryDao;
import dailyretrojournal.dynamodb.models.JournalEntry;
import dailyretrojournal.models.JournalEntryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class CreateNewJournalEntryActivity {

    private final Logger log = LogManager.getLogger();
    private final JournalEntryDao journalEntryDao;

    @Inject
    public CreateNewJournalEntryActivity(JournalEntryDao journalEntrydao) {
        this.journalEntryDao = journalEntrydao;
    }


    public CreateNewJournalEntryResult handleRequest(final CreateNewJournalEntryRequest request) {
        log.info("recieved createJournalEntryRequest {}", request);

        JournalEntry savedJournalEntry = journalEntryDao.saveJournalEntry(request.getContent(), request.getDateEntered(), request.getHashtag());

        JournalEntryModel journalEntryModel = new ModelConverter().toJournalEntryModel(savedJournalEntry);

        return CreateNewJournalEntryResult.builder()
                .withNewEntry(journalEntryModel)
                .build();
    }
}


