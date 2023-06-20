package dailyretrojournal.activity.result;

import dailyretrojournal.models.JournalEntryModel;

public class DeleteJournalEntryResult {
    private final JournalEntryModel journalEntryModel;

    public DeleteJournalEntryResult(JournalEntryModel journalEntryModel) {
        this.journalEntryModel = journalEntryModel;
    }

    public JournalEntryModel getJournalEntryModel() {
        return journalEntryModel;
    }

    @Override
    public String toString() {
        return "DeleteJournalEntryResult{" +
                "journalEntryModel=" + journalEntryModel +
                '}';
    }

    public static class Builder {
        private JournalEntryModel journalEntryModel;

        public Builder withJournalEntryModel(JournalEntryModel journalEntryModel) {
        this.journalEntryModel = journalEntryModel;
        return this;
        }
        public DeleteJournalEntryResult build() {
            return new DeleteJournalEntryResult(journalEntryModel);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
