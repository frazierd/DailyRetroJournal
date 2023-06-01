package dailyretrojournal.activity.result;

import dailyretrojournal.dynamodb.models.JournalEntry;

import java.util.ArrayList;
import java.util.List;

public class GetAllJournalEntriesResult {

    private final List<JournalEntry> allJournalEntries;

    private GetAllJournalEntriesResult(List<JournalEntry> allJournalEntries) {
        this.allJournalEntries = allJournalEntries;
    }

    public List<JournalEntry> getAllJournalEntriesList() {
        return allJournalEntries;
    }

    @Override
    public String toString() {
        return "GetAllJournalEntriesResult{" +
                "journalEntryModel=" + allJournalEntries +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<JournalEntry> allJournalEntries;

        public Builder withAllJournalEntriesList(List<JournalEntry> allJournalEntries) {
            this.allJournalEntries = new ArrayList<>(allJournalEntries);
            return this;
        }

        public GetAllJournalEntriesResult build() {
            return new GetAllJournalEntriesResult(allJournalEntries);
        }

    }

}
