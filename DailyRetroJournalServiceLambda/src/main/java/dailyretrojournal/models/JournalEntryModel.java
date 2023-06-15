package dailyretrojournal.models;

import java.util.List;
import java.util.Objects;

public class JournalEntryModel {

    private String entryId;
    private String content;
    private String dateEntered;
    private List<String> hashtag;

    public JournalEntryModel(String entryId, String content, String dateEntered, List<String> hashtag) {
        this.entryId = entryId;
        this.content = content;
        this.dateEntered = dateEntered;
        this.hashtag = hashtag;
    }

    public String getId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JournalEntryModel)) return false;
        JournalEntryModel that = (JournalEntryModel) o;
        return getId().equals(that.getId()) && getContent().equals(that.getContent()) && getDateEntered().equals(that.getDateEntered()) && getHashtag().equals(that.getHashtag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getDateEntered(), getHashtag());
    }

    public static Builder builder() {
        return new Builder();
    }

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

        public JournalEntryModel build() {
            return new JournalEntryModel(entryId, content, dateEntered, hashtag);
        }
    }
}





