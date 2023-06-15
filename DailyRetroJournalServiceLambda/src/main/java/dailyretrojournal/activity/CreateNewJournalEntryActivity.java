package dailyretrojournal.activity;

import dailyretrojournal.activity.request.CreateNewJournalEntryRequest;
import dailyretrojournal.activity.result.CreateNewJournalEntryResult;
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

    public CreateNewJournalEntryResult handleRequest (CreateNewJournalEntryRequest request) {
         String requestId = request.getEntryId();
         String requestContent = request.getContent();
         String requestDateEntered = request.getDateEntered();
         List<String> requestHashtag = request.getHashtag();

         // create a new JournalEntryModel object.
        JournalEntryModel model = JournalEntryModel.builder()
                .withId(requestId)
                .withContent(requestContent)
                .withDateEntered(requestDateEntered)
                .withHashtag(requestHashtag)
                .build();

        // convert the new model object to a JournalEntry to pass into saveJournalEntry
         JournalEntry newJournalEntry = new JournalEntry(
                 model.getId(),
                 model.getContent(),
                 model.getDateEntered(),
                 model.getHashtag());

         journalEntryDao.saveJournalEntry(newJournalEntry);

//        journalEntry.setId(requestedId);
//        journalEntry.setContent(content);
//        journalEntry.setDateEntered(dateEntered);
//        journalEntry.setHashtag(hashtag);


        CreateNewJournalEntryResult result = CreateNewJournalEntryResult.builder()
                .withNewEntry(journalEntryModel)
                .build();

         return result;
    }

}
