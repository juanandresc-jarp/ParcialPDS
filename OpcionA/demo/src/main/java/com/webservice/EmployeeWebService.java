package com.webservice;

import com.model.Employee;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EmployeeWebService {

    private static final String BASE_URL = "https://retoolapi.dev/avW3Wh/parcialpds?code=";

    public Employee fetchEmployee(String employeeCode) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = BASE_URL + employeeCode;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray array = new JSONArray(response.body());

                if (array.length() > 0) {
                    JSONObject json = array.getJSONObject(0);
                    return new Employee(
                            json.getString("code"),  // code es tu identificador
                            json.getString("name"),
                            json.getString("position")
                    );
                } else {
                    System.out.println("No employee found with code: " + employeeCode);
                }
            } else {
                System.out.println("HTTP error: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
