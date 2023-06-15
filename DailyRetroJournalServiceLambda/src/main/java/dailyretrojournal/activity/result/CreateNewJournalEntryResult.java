package dailyretrojournal.activity.result;

import dailyretrojournal.dynamodb.models.JournalEntry;
import dailyretrojournal.models.JournalEntryModel;
import org.apache.commons.lang3.builder.Builder;

public class CreateNewJournalEntryResult {

    private final JournalEntryModel newEntry;

    private CreateNewJournalEntryResult(JournalEntryModel newEntry) {
        this.newEntry = newEntry;
    }

    public JournalEntryModel getNewEntry() {
        return newEntry;
    }

    @Override
    public String toString() {
        return "CreateNewJournalEntryResult{" +
                "newEntry=" + newEntry +
                '}';
    }

    public static class Builder {
        private JournalEntryModel newEntry;

        public Builder withNewEntry(JournalEntryModel newEntry) {
            this.newEntry = newEntry;
            return this;
        }

        public CreateNewJournalEntryResult build() {
            return new CreateNewJournalEntryResult(newEntry);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
