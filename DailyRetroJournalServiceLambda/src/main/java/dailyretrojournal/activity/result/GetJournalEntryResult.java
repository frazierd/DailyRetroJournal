package dailyretrojournal.activity.result;

import dailyretrojournal.models.JournalEntryModel;

public class GetJournalEntryResult {

    private final JournalEntryModel journalEntryModel;

    public GetJournalEntryResult(JournalEntryModel journalEntryModel) {
        this.journalEntryModel = journalEntryModel;
    }

    public JournalEntryModel getJournalEntry() {
        return journalEntryModel;
    }

    @Override
    public String toString() {
        return "GetJournalEntryResult{" +
                "journalEntryModel=" + journalEntryModel +
                '}';
    }

    public static class Builder {
        private JournalEntryModel journalEntryModel;

        public Builder withJournalEntry(JournalEntryModel journalEntryModel) {
            this.journalEntryModel = journalEntryModel;
            return this;
        }

        public GetJournalEntryResult build() {
            return new GetJournalEntryResult(journalEntryModel);
        }

    }
    public static Builder builder() {
        return new Builder();
    }
}
