package dailyretrojournal.activity;

import com.google.common.collect.Sets;
import dailyretrojournal.activity.request.GetJournalAllEntriesRequest;
import dailyretrojournal.activity.result.GetAllJournalEntriesResult;
import dailyretrojournal.dynamodb.JournalEntryDao;
import dailyretrojournal.dynamodb.models.JournalEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetAllJournalEntriesActivityTest {

    @Mock
    private JournalEntryDao journalEntryDao;

    @InjectMocks
    private GetAllJournalEntriesActivity getAllJournalEntriesActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void handleRequest_savedJournalEntryFound_returnsAllJournalEntries() {
        // GIVEN
        List<JournalEntry> expectedEntries = List.of(
                createJournalEntry("EntryId1", "expected content1", "2023-04-17", List.of("#tag1", "#tag2")),
                createJournalEntry("EntryId2", "expected content2", "2023-05-18", List.of("#tag3"))
        );
        // Mock the behavior of the journalEntryDao.getAllJournalEntries() method
        when(journalEntryDao.getAllJournalEntries()).thenReturn(expectedEntries);

        //WHEN
        GetAllJournalEntriesResult result = getAllJournalEntriesActivity.handleRequest();

        //THEN
        assertEquals(expectedEntries, result.getAllJournalEntriesList());
    }

    @Test
    public void handleRequest_emptyJournalEntries_returnsEmptyResult() {
        //GIVEN
        List<JournalEntry> emptyList = Collections.emptyList();
        when(journalEntryDao.getAllJournalEntries()).thenReturn(emptyList);

        //WHEN
        GetAllJournalEntriesResult result = getAllJournalEntriesActivity.handleRequest();

        //THEN
        assertNotNull(result);
        assertTrue(result.getAllJournalEntriesList().isEmpty());
    }

    // Helper method to create a JournalEntry object
    private JournalEntry createJournalEntry(String entryId, String content, String dateEntered, List<String> hashtag) {
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setId(entryId);
        journalEntry.setContent(content);
        journalEntry.setDateEntered(dateEntered);
        journalEntry.setHashtag(hashtag);
        return journalEntry;
    }
}