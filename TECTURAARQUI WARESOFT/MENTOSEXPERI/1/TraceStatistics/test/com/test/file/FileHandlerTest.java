package com.test.file;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.test.json.JsonJoiner;

public class FileHandlerTest {

    @Test
    public void testgetJsonTrace() {
        FileHandler fileHandler = new FileHandler();
        JSONObject jsonObject = fileHandler.getJsonTrace("file/log-26-06-2013-02-05-43.log");
        try {
            Assert.assertEquals(18270994600284l, jsonObject.getLong("startTime"));
            Assert.assertEquals(194552l, jsonObject.getLong("averageTime"));
            Assert.assertEquals(18270994794836l, jsonObject.getLong("endTime"));
            Assert.assertEquals(194552l, jsonObject.getLong("totalTime"));
            Assert.assertEquals(1l, jsonObject.getLong("size"));
            JSONArray jsonArray = jsonObject.getJSONArray("traces");
            Assert.assertEquals(1, jsonArray.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsonJoiner() {

        JsonJoiner joiner = new JsonJoiner();
        System.out.println(joiner.joinFiles("file/log-27-06-2013-01-48-43.log", "file/log-27-06-2013-01-48-59.log"));
    }
}
