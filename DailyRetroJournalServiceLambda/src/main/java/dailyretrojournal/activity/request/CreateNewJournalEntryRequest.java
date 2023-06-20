package dailyretrojournal.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.IOException;
import java.util.List;

@JsonDeserialize(builder = CreateNewJournalEntryRequest.Builder.class)
public class CreateNewJournalEntryRequest {

    private final String entryId;
    private final String content;
    private final String dateEntered;
    private final List<String> hashtag;

    private CreateNewJournalEntryRequest(String entryId, String content, String dateEntered, List<String> hashtag) {
       this.entryId = entryId;
       this.content = content;
       this.dateEntered = dateEntered;
       this.hashtag = hashtag;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEntryId() {
        return entryId;
    }

    public String getContent() {
        return content;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public List<String> getHashtag() {
        return hashtag;
    }

    @Override
    public String toString() {
        return "CreateNewJournalEntryRequest{" +
                "entryId='" + entryId + '\'' +
                ", content='" + content + '\'' +
                ", dateEntered='" + dateEntered + '\'' +
                ", hashtag=" + hashtag +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String entryId;
        private String content;
        private String dateEntered;
        private List<String> hashtag;

        public Builder withId(String entryId) {
            this.entryId = entryId;
            return this;
        }
        public Builder withContent(String content) {
            this.content = content;
            return this;
        }
        public Builder withDateEntered(String dateEntered) {
            this.dateEntered = dateEntered;
            return this;
        }

        public Builder withHashtag(List<String> hashtag) {
            this.hashtag = hashtag;
            return this;
        }

        public CreateNewJournalEntryRequest build() {
            return new CreateNewJournalEntryRequest (entryId, content, dateEntered, hashtag);
        }
    }
}