package dailyretrojournal.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetJournalAllEntriesRequest.Builder.class)
public class GetJournalAllEntriesRequest {


    public GetJournalAllEntriesRequest() {
    }



    @JsonPOJOBuilder
    public static class Builder {

        public GetJournalAllEntriesRequest build() {
            return new GetJournalAllEntriesRequest();
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
