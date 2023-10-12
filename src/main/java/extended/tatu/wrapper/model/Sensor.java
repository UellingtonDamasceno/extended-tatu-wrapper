package extended.tatu.wrapper.model;

import java.util.Objects;

/**
 *
 * @author Uellington Damasceno
 */
public class Sensor {

    public static final double DEFAULT_MIN_VALUE = 10;
    public static final double DEFAULT_MAX_VALUE = 30;
    public static final double DEFAULT_DELTA = 0.5;

    protected final String id;
    protected final String type;
    protected int collectionTime;
    protected int publishingTime;
    protected double minValue, maxValue, delta;
    private int hashcode;

    public Sensor(String id, String type, int collectionTime, int publishingTime) {
        this(id, type, collectionTime, publishingTime, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, DEFAULT_DELTA);
    }

    public Sensor(String id, String type, int collectionTime, int publishingTime, double minValue, double maxValue, double delta) {
        this.id = id;
        this.type = type;
        this.collectionTime = collectionTime;
        this.publishingTime = publishingTime;
        if (minValue > maxValue) {
            throw new IllegalArgumentException("O sensor [id]: " + id + " [type]:" + type + " poussi um valor minimo maior:" + minValue + " que o máximo " + maxValue + ".");
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.delta = delta;
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

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getDelta() {
        return delta;
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
