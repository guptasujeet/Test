package test.q1.tw;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodOutlet {


    private static final String URL_FORMAT = "https://jsonmock.hackerrank.com/api/food_outlets?city={0}&page={1}";


    public static List<String> getRelevantFoodOutlets(String city, int maxCost) {
        List<Outlet> allOutlets = getAllOutletData(city);
        List<String> filteredOutlet = new ArrayList<>();

        for (Outlet outlet : allOutlets) {
            if (outlet.estimated_cost <= maxCost) {
                filteredOutlet.add(outlet.name);
            }
        }
        return filteredOutlet;

    }

    private static List<Outlet> getAllOutletData(String city) {
        int pageNum = 1;
        String url = MessageFormat.format(URL_FORMAT, city, String.valueOf(pageNum));
        ApiResponse apiResponse = fetchAndParseApiResponse(url);
        int totalPage = apiResponse.total_pages;
        List<Outlet> allOutlets = new ArrayList<>(apiResponse.data);

        if (totalPage > pageNum) {
            for (pageNum = 2; pageNum <= totalPage; pageNum++) {
                url = MessageFormat.format(URL_FORMAT, city, String.valueOf(pageNum));
                apiResponse = fetchAndParseApiResponse(url);
                allOutlets.addAll(apiResponse.data);
            }
        }
        return allOutlets;
    }


    public static ApiResponse fetchAndParseApiResponse(String url) {
        String content = fetchApiData(url);
        Gson gson = new Gson();
        return gson.fromJson(content, ApiResponse.class);
    }


    private static String fetchApiData(String apiUrl) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Scanner scanner = null;
            try {
                java.net.URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                scanner = new Scanner(urlConnection.getInputStream());
                while (scanner.hasNextLine()) {
                    outputStream.write(scanner.nextLine().getBytes());
                }
                return outputStream.toString();
            } catch (Exception e) {
                throw e;
            } finally {
                outputStream.close();
                if (scanner != null) {
                    scanner.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static class ApiResponse {
        int page;
        int per_page;
        int total;
        int total_pages;
        List<Outlet> data;
    }


    static class Outlet {
        int id;
        String city;
        String name;
        double estimated_cost;
        UserRating user_rating;

    }

    static class UserRating {
        double average_rating;
        int votes;
    }


    public static void main(String[] args) {
        System.out.println(getRelevantFoodOutlets("Denver", 50));
        System.out.println(getRelevantFoodOutlets("Houston", 30));
    }
}
