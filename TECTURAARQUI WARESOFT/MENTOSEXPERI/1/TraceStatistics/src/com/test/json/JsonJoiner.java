package com.test.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.test.file.FileHandler;

public class JsonJoiner {

    public JSONObject joinFiles(String... files) {

        JSONObject jsonResponse = new JSONObject();

        try {
            FileHandler fileHandler = new FileHandler();
            List<JSONObject> jsonObjects = new ArrayList<JSONObject>(files.length);
            for (String file : files) {
                jsonObjects.add(fileHandler.getJsonTrace(file));
            }
            Map<String, List<JSONObject>> allTraces = new HashMap<String, List<JSONObject>>();
            long totalTime = 0;
            long startTime = Long.MAX_VALUE;
            long endTime = 0;
            for (JSONObject jsonObject : jsonObjects) {
                long objectStartTime = jsonObject.getLong("startTime");
                if (objectStartTime < startTime) {
                    startTime = objectStartTime;
                }
                long objectEndTime = jsonObject.getLong("endTime");
                if (objectEndTime > endTime) {
                    endTime = objectEndTime;
                }
                JSONArray traces = jsonObject.getJSONArray("traces");
                fillTracesById(allTraces, traces);
            }
            totalTime = endTime - startTime;
            jsonResponse.put("startTime", startTime);
            jsonResponse.put("endTime", endTime);
            jsonResponse.put("totalTime", totalTime);
            JSONArray tracesArray = getTracesArray(allTraces);
            jsonResponse.put("traces", tracesArray);
            long average = getAverageTime(tracesArray);
            jsonResponse.put("averageTime", average);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }

    private long getAverageTime(JSONArray tracesArray) throws JSONException {
        
        long average = 0;
        for (int i = 0; i < tracesArray.length(); i++) {
            JSONObject trace = tracesArray.getJSONObject(i);
            average += trace.getLong("total");
        }
        
        return average / tracesArray.length();
    }

    private JSONArray getTracesArray(Map<String, List<JSONObject>> allTraces) throws JSONException {
        
        JSONArray tracesArray = new JSONArray();
        
        for (String id : allTraces.keySet()) {
            List<JSONObject> traces = allTraces.get(id);
            JSONObject newTrace = new JSONObject();
            newTrace.put("id", id);
            TreeSet<Long> times = new TreeSet<Long>();
            for (JSONObject jsonObject : traces) {
                JSONArray timesArray = jsonObject.getJSONArray("traces");
                for (int i = 0; i < timesArray.length(); i++) {
                    times.add(timesArray.getLong(i));
                }
            }
            JSONArray newTimesArray = new JSONArray();
            long entTime = 0;
            for (Long time : times) {
                newTimesArray.put(time);
                entTime = time;
            }
            newTrace.put("traces", newTimesArray);
            long totalTime = entTime - newTimesArray.getLong(0);
            newTrace.put("total", totalTime);
            tracesArray.put(newTrace);
        }
        
        return tracesArray;
    }

    private void fillTracesById(Map<String, List<JSONObject>> allTraces, JSONArray traces) throws JSONException {
        for (int i = 0; i < traces.length(); i++) {
            JSONObject trace = traces.getJSONObject(i);
            String traceId = trace.getString("id");
            if (!allTraces.containsKey(traceId)) {
                allTraces.put(traceId, new LinkedList<JSONObject>());
            }
            allTraces.get(traceId).add(trace);
        }
    }
}
