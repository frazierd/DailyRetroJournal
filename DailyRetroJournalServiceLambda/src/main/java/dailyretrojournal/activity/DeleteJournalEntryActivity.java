package dailyretrojournal.activity;

import dailyretrojournal.activity.request.DeleteJournalEntryRequest;
import dailyretrojournal.activity.result.DeleteJournalEntryResult;
import dailyretrojournal.converters.ModelConverter;
import dailyretrojournal.dynamodb.JournalEntryDao;
import dailyretrojournal.dynamodb.models.JournalEntry;
import dailyretrojournal.models.JournalEntryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteJournalEntryActivity {
    private final Logger log = LogManager.getLogger();
    private final JournalEntryDao journalEntryDao;

    @Inject
    public DeleteJournalEntryActivity(JournalEntryDao journalEntryDao) {
        this.journalEntryDao = journalEntryDao;
    }

    public DeleteJournalEntryResult handleRequest(final DeleteJournalEntryRequest deleteJournalEntryRequest) {
        log.info("received DeleteJournalEntryRequest {}", deleteJournalEntryRequest);
        String requestedEntryId = deleteJournalEntryRequest.getEntryId();
        log.info("Requested EntryId from request is {}", requestedEntryId);
        JournalEntry journalEntry= journalEntryDao.removeJournalEntry(requestedEntryId);
        log.info("Got JournalEntry{}", journalEntry);
        JournalEntryModel journalEntryModel = new ModelConverter().toJournalEntryModel(journalEntry);
        log.info("Got JournalEntryModel {}", journalEntryModel);

        return DeleteJournalEntryResult.builder()
                .withJournalEntryModel(journalEntryModel)
                .build();
    }
}
