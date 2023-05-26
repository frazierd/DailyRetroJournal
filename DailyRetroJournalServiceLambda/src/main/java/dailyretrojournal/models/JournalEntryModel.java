package dailyretrojournal.models;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class JournalEntryModel {

    private String id;
    private String content;
    private ZonedDateTime dateEntered;
    private ArrayList<String> hashtag;

    public JournalEntryModel(String id, String content, ZonedDateTime dateEntered, ArrayList<String> hashtag) {
        this.id = id;
        this.content = content;
        this.dateEntered = dateEntered;
        this.hashtag = hashtag;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public ZonedDateTime getDateEntered() {
        return dateEntered;
    }

    public ArrayList<String> getHashtag() {
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

        private String id;
        private String content;
        private ZonedDateTime dateEntered;
        private ArrayList<String> hashtag;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withDateEntered(ZonedDateTime dateEntered) {
            this.dateEntered = dateEntered;
            return this;
        }

        public Builder withHashtag(ArrayList<String> hashtag) {
            this.hashtag = hashtag;
            return this;
        }

        public JournalEntryModel build() {
            return new JournalEntryModel(id, content, dateEntered, hashtag);
        }
    }
}





