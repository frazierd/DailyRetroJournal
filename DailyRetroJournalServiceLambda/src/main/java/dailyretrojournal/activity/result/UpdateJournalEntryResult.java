package dailyretrojournal.activity.result;

import dailyretrojournal.activity.request.UpdateJournalEntryRequest;
import dailyretrojournal.models.JournalEntryModel;

public class UpdateJournalEntryResult {

    private final JournalEntryModel journalEntryModel;

    public UpdateJournalEntryResult(JournalEntryModel journalEntryModel) {
        this.journalEntryModel = journalEntryModel;
    }

    public JournalEntryModel getJournalEntryModel() {
        return journalEntryModel;
    }

    @Override
    public String toString() {
        return "UpdateJournalEntryResult{" +
                "journalEntryModel=" + journalEntryModel +
                '}';
    }

    public static class Builder {

        private JournalEntryModel journalEntryModel;

        public Builder withJournalEntryModel(JournalEntryModel journalEntryModel) {
            this.journalEntryModel = journalEntryModel;
            return this;
        }

        public UpdateJournalEntryResult build() {
            return new UpdateJournalEntryResult(journalEntryModel);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
