package dailyretrojournal.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.ZonedDateTime;
import java.util.Objects;

@DynamoDBTable(tableName = "hashtagTable")
public class Hashtag {

    private String hashtag;
    private ZonedDateTime createdAt;

    @DynamoDBHashKey(attributeName = "hashtag")
    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
    @DynamoDBAttribute (attributeName = "timeCreated")
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "hashtag='" + hashtag + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hashtag)) return false;
        Hashtag hashtag1 = (Hashtag) o;
        return getHashtag().equals(hashtag1.getHashtag()) && getCreatedAt().equals(hashtag1.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHashtag(), getCreatedAt());
    }
}
