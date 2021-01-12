package extended.tatu.wrapper.model;

import java.util.Objects;

/**
 *
 * @author Uellington Damasceno
 */
public class Sensor {

    protected final String id;
    protected final String type;
    protected int collectionTime;
    protected int publishingTime;
    private int hashcode;

    public Sensor(String id, String type, int collectionTime, int publishingTime) {
        this.id = id;
        this.type = type;
        this.collectionTime = collectionTime;
        this.publishingTime = publishingTime;
        this.hashcode = -1;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getCollectionTime() {
        return collectionTime;
    }

    public int getPublishingTime() {
        return publishingTime;
    }

    public void setCollectionTime(int collectionTime) {
        this.collectionTime = collectionTime;
    }

    public void setPublishingTime(int publishingTime) {
        this.publishingTime = publishingTime;
    }

    @Override
    public int hashCode() {
        if (this.hashcode == -1) {
            hashcode = 67 * hashcode + Objects.hashCode(this.id);
            hashcode = 67 * hashcode + Objects.hashCode(this.type);
            hashcode = 67 * hashcode + this.collectionTime;
            hashcode = 67 * hashcode + this.publishingTime;
        }
        return this.hashcode;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Sensor)
                && ((Sensor) object).hashCode() == this.hashCode();
    }
}
