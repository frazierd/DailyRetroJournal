package dailyretrojournal.activity;

import dailyretrojournal.activity.request.GetJournalAllEntriesRequest;
import dailyretrojournal.activity.result.GetAllJournalEntriesResult;
import dailyretrojournal.converters.ModelConverter;
import dailyretrojournal.dynamodb.JournalEntryDao;
import dailyretrojournal.dynamodb.models.JournalEntry;
import dailyretrojournal.models.JournalEntryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetAllJournalEntriesActivity {

    private final Logger log = LogManager.getLogger();
    private final JournalEntryDao journalEntryDao;

    @Inject
    public GetAllJournalEntriesActivity(JournalEntryDao journalEntryDao) {
        this.journalEntryDao = journalEntryDao;
    }

    public GetAllJournalEntriesResult handleRequest () {
        List<JournalEntry> journalEntry = journalEntryDao.getAllJournalEntries();
        log.fatal("this is GetAllJournalEntriesResult handle request {}" + journalEntry);
        return GetAllJournalEntriesResult.builder()
                .withAllJournalEntriesList(journalEntry)
                .build();
    }


}
