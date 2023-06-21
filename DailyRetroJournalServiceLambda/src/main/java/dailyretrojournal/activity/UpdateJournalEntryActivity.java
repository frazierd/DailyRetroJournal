//package dailyretrojournal.activity;
//
//import dailyretrojournal.activity.request.UpdateJournalEntryRequest;
//import dailyretrojournal.converters.ModelConverter;
//import dailyretrojournal.dynamodb.JournalEntryDao;
//import dailyretrojournal.dynamodb.models.JournalEntry;
//import dailyretrojournal.models.JournalEntryModel;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.inject.Inject;
//
//public class UpdateJournalEntryActivity {
//
//    private final Logger log = LogManager.getLogger();
//
//    private final JournalEntryDao journalEntryDao;
//
//    @Inject
//    public UpdateJournalEntryActivity(JournalEntryDao journalEntryDao) {
//        this.journalEntryDao = journalEntryDao;
//    }
//
//    public UpdateJournalEntryActivity handleRequest(final UpdateJournalEntryRequest updateEntryRequest){
//        String requestedEntryId = UpdateJournalEntryRequest.getEntryId();
//        JournalEntry journalEntry = journalEntryDao.updateJournalEntry(requestedEntryId);
//        JournalEntryModel journalEntryModel = new ModelConverter().toJournalEntryModel(journalEntry);
//    }
//
//
//
//
//}
