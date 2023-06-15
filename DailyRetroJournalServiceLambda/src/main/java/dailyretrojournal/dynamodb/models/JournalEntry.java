package dailyretrojournal.dynamodb.models;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "journalEntryTable")
public class JournalEntry {

    private String entryId;
    private String content;
    private String dateEntered;
    private List<String> hashtag;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return entryId;
    }

    public void setId(String entryId) {
        this.entryId = entryId;
    }

    @DynamoDBAttribute(attributeName = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @DynamoDBAttribute(attributeName = "date")
    public String getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    @DynamoDBAttribute(attributeName = "hashtag")
    public List<String> getHashtag() {
        return hashtag;
    }

    public void setHashtag(List<String> hashtag) {
        this.hashtag = hashtag;
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "id='" + entryId + '\'' +
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

