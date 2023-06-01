package dailyretrojournal.activity;

import dailyretrojournal.activity.request.GetJournalEntryRequest;
import dailyretrojournal.activity.result.GetJournalEntryResult;
import dailyretrojournal.converters.ModelConverter;
import dailyretrojournal.dynamodb.JournalEntryDao;
import dailyretrojournal.dynamodb.models.JournalEntry;
import dailyretrojournal.models.JournalEntryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetJournalEntryActivity {

    private final Logger log = LogManager.getLogger();
    private final JournalEntryDao journalEntryDao;

    @Inject
    public GetJournalEntryActivity(JournalEntryDao journalEntryDao) {
        this.journalEntryDao = journalEntryDao;
    }

    public GetJournalEntryResult handleRequest (final GetJournalEntryRequest getJournalEntryRequest) {
        log.info("Recieved GetJournalEntryRequest {}", getJournalEntryRequest);
        String requestedId = getJournalEntryRequest.getId();
        JournalEntry journalEntry = journalEntryDao.getJournalEntry(requestedId);
        JournalEntryModel journalEntryModel = new ModelConverter().toJournalEntryModel(journalEntry);

        return GetJournalEntryResult.builder()
                .withJournalEntry(journalEntryModel)
                .build();
    }
}
