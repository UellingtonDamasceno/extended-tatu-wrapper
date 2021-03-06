package extended.tatu.wrapper.util;

import extended.tatu.wrapper.model.Device;
import extended.tatu.wrapper.model.Sensor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Uellington Damasceno
 */
public class DeviceWrapper {

    private static final String INVALID_DEVICE = "INVALID_DEVICE";

    public static List<Device> getAllDevices(String devices) {
        return (devices == null || devices.isEmpty())
                ? new ArrayList()
                : new JSONArray(devices)
                        .toList()
                        .stream()
                        .filter(Map.class::isInstance)
                        .map(Map.class::cast)
                        .map(DeviceWrapper::toDevice)
                        .collect(Collectors.toList());

    }

    public static Device toDevice(String device) {
        return toDevice(new JSONObject(device));
    }

    public static Device toDevice(Map device) {
        String id = (String) device.getOrDefault("id", INVALID_DEVICE);
        Double longitude = (Double) device.getOrDefault("longitude", 0);
        Double latitude = (Double) device.getOrDefault("latitude", 0);
        List sensors = (ArrayList) device.getOrDefault("sensors", Collections.emptyList());
        List<Sensor> allSensors = SensorWrapper.getAllSensors(sensors);
        return new Device(id, longitude, latitude, allSensors);
    }

    public static Device toDevice(JSONObject device) {
        String id = device.optString("id", INVALID_DEVICE);
        double longitude = device.optDouble("longitude", 0);
        double latitude = device.optDouble("latitude", 0);
        List<Sensor> sensors = SensorWrapper.getAllSensors(device.getJSONArray("sensors"));
        return new Device(id, longitude, latitude, sensors);
    }

    public static String toJSON(Device device) {
        return toJSONObject(device).toString();
    }

    public static JSONObject toJSONObject(Device device) {
        List<JSONObject> sensors = SensorWrapper.getAllJSONObjectSensor(device.getSensors());
        return new JSONObject()
                .put("id", device.getId())
                .put("latitude", device.getLatitude())
                .put("longitude", device.getLongitude())
                .put("sensors", sensors);
    }
}
