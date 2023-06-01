package dailyretrojournal.activity.result;

import dailyretrojournal.models.JournalEntryModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetJournalEntryResultTest {

    @Test
    public void getJournalEntry_journalEntryResult_returnsJournalEntryModel() {
        //GIVEN
        String expectedEntryId = "EntryId1";
        String expectedContent = "expected content1";
        String expectedDateEntered = "2023-02-17";
        List<String> expectedHashtags = List.of("#tag1", "#tag2", "#tag3");

        JournalEntryModel journalEntryModel = JournalEntryModel.builder()
                .withId(expectedEntryId)
                .withContent(expectedContent)
                .withDateEntered(expectedDateEntered)
                .withHashtag(expectedHashtags)
                .build();

        //WHEN
        GetJournalEntryResult result = new GetJournalEntryResult(journalEntryModel);
        JournalEntryModel retrievedEntryModel = result.getJournalEntry();

        //THEN
        Assertions.assertEquals(journalEntryModel, retrievedEntryModel);
    }

}