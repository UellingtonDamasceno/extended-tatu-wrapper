package extended.tatu.wrapper.util;

import extended.tatu.wrapper.model.Sensor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Uellington Damasceno
 */
public class SensorWrapper {

    private static final String INVALID_SENSOR = "INVALID_SENSOR";

    public static List<Sensor> getAllSensors(List sensors) {
        return SensorWrapper.getAllSensors(new JSONArray(sensors));
    }

    public static List<Sensor> getAllSensors(JSONArray sensors) {
        return (sensors == null || sensors.isEmpty())
                ? new ArrayList()
                : sensors.toList()
                        .stream()
                        .filter(Map.class::isInstance)
                        .map(Map.class::cast)
                        .map(SensorWrapper::toSensor)
                        .collect(Collectors.toList());
    }

    public static List<String> getAllJSONSensors(List<Sensor> sensors) {
        return (sensors == null || sensors.isEmpty())
                ? new ArrayList()
                : sensors.stream()
                        .map(SensorWrapper::toJSON)
                        .collect(Collectors.toList());
    }

    public static Sensor toSensor(Map sensor) {
        String id = (String) sensor.getOrDefault("id", INVALID_SENSOR);
        String type = (String) sensor.getOrDefault("type", INVALID_SENSOR);
        int collectionTime = (int) sensor.getOrDefault("collection_time", 0);
        int publishingTime = (int) sensor.getOrDefault("publishing_time", 0);
        return new Sensor(id, type, collectionTime, publishingTime);
    }

    public static Sensor toSensor(String sensor) {
        JSONObject jSensor = new JSONObject(sensor);
        String id = jSensor.getString("id");
        String type = jSensor.getString("type");
        int collectionTime = jSensor.getInt("collection_time");
        int publishingTime = jSensor.getInt("publishing_time");
        return new Sensor(id, type, collectionTime, publishingTime);
    }

    public static String toJSON(Sensor sensor) {
        return sensor == null
                ? JSONObject.NULL.toString()
                : new JSONObject()
                        .put("id", sensor.getId())
                        .put("type", sensor.getType())
                        .put("collection_time", sensor.getCollectionTime())
                        .put("publishing_time", sensor.getPublishingTime())
                        .toString();
    }
}
