package dailyretrojournal.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.apache.commons.lang3.builder.Builder;

public class DeleteJournalEntryRequest {

    private final String entryId;

    public DeleteJournalEntryRequest(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryId() {
        return entryId;
    }

    @Override
    public String toString() {
        return "DeleteJournalEntryRequest{" +
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

        public DeleteJournalEntryRequest build() {
            return new DeleteJournalEntryRequest(entryId);
        }
    }
        public static Builder builder() {
            return new Builder();
        }



}
