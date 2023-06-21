package dailyretrojournal.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class UpdateJournalEntryRequest {

    private final String entryId;

    public UpdateJournalEntryRequest(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryId() {
        return entryId;
    }

    @Override
    public String toString() {
        return "UpdateJournalEntryRequest{" +
                "entryId='" + entryId + '\'' +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String entryId;

        public Builder withId(String entryId) {
            this.entryId = entryId;
            return this;
        }

        public UpdateJournalEntryRequest build() {
            return new UpdateJournalEntryRequest(entryId);
        }
    }
        public Builder builder() {
            return new Builder();
        }


    }
