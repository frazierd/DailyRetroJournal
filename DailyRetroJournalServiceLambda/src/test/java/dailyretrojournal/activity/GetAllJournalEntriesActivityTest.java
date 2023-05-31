package dailyretrojournal.activity;

import dailyretrojournal.dynamodb.JournalEntryDao;
import dailyretrojournal.dynamodb.models.JournalEntry;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class GetAllJournalEntriesActivityTest {

    @Mock
    private JournalEntryDao journalEntryDao;

    private GetAllJournalEntriesActivity getAllJournalEntriesActivity;

    void setup() {
        openMock(this);
        getAllJournalEntriesActivity = new GetAllJournalEntriesActivity(journalEntryDao);
    }

    @Test
    void handleRequest() {
    }
}