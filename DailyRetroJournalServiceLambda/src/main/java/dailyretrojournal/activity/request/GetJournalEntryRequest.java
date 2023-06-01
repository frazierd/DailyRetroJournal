package dailyretrojournal.activity.request;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = GetJournalEntryRequest.Builder.class)
public class GetJournalEntryRequest {
    private final String entryId;

    public GetJournalEntryRequest(String entryId) {
        this.entryId = entryId;
    }

    public String getId() {
        return entryId;
    }

    @Override
    public String toString() {
        return "getJournalEntryRequest{" +
                "id='" + entryId + '\'' +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String entryId;

        public Builder withId(String entryId) {
            this.entryId = entryId;
            return this;
        }

        public GetJournalEntryRequest build() {
            return new GetJournalEntryRequest(entryId);
        }
    }
        public static Builder builder() {
            return new Builder();
        }

}

