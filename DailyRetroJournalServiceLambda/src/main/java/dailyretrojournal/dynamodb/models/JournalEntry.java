package dailyretrojournal.dynamodb.models;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;

@DynamoDBTable(tableName = "journalEntryTable")
public class JournalEntry {

    private String id;
    private String content;
    private ZonedDateTime dateEntered;
    private ArrayList<String> hashtag;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @DynamoDBAttribute(attributeName = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @DynamoDBAttribute(attributeName = "date")
    public ZonedDateTime getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(ZonedDateTime dateEntered) {
        this.dateEntered = dateEntered;
    }
    @DynamoDBAttribute(attributeName = "hashtag")
    public ArrayList<String> getHashtag() {
        return hashtag;
    }

    public void setHashtag(ArrayList<String> hashtag) {
        this.hashtag = hashtag;
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", dateEntered=" + dateEntered +
                ", hashtag=" + hashtag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JournalEntry)) return false;
        JournalEntry that = (JournalEntry) o;
        return getId().equals(that.getId()) && getContent().equals(that.getContent()) && getDateEntered().equals(that.getDateEntered()) && getHashtag().equals(that.getHashtag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getDateEntered(), getHashtag());
    }
}

