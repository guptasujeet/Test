package test.q1;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

public class Solution1 {

    private static String REST_END_POINT = "https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users";


    private static final Map<String, Function<UserProfile, String>> READER = new HashMap<>();


    static {
        READER.put("id", UserProfile::getId);
        READER.put("name", UserProfile::getName);
        READER.put("username", UserProfile::getUsername);
        READER.put("email", UserProfile::getEmail);
        READER.put("address.street", userProfile -> userProfile.getAddress().getStreet());
        READER.put("address.suite", userProfile -> userProfile.getAddress().getSuite());
        READER.put("address.city", userProfile -> userProfile.getAddress().getCity());
        READER.put("address.zipcode", userProfile -> userProfile.getAddress().getZipcode());
        READER.put("address.geo.lat", userProfile -> userProfile.getAddress().getGeo().getLatString());
        READER.put("address.geo.lng", userProfile -> userProfile.getAddress().getGeo().getLngString());
        READER.put("website", UserProfile::getWebsite);
        READER.put("company.name", userProfile -> userProfile.getCompany().getName());
        READER.put("company.basename", userProfile -> userProfile.getCompany().getBasename());
    }


    public static List<Integer> apiResponseParser(List<String> inputList, int size) {
        String content = getApiContent(REST_END_POINT);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<UserProfile>>() {
        }.getType();
        List<UserProfile> userProfiles = gson.fromJson(content, listType);

        //System.out.println(userProfiles);

        String filterField = inputList.get(0);
        FilterType filterType = FilterType.valueOf(inputList.get(1));
        String filterValues = inputList.get(2);

        FilterEngine filterEngine = new FilterEngine(userProfiles);
        List<Integer> filterIds = filterEngine.applyFilter(filterField, filterType, filterValues);
        return filterIds.isEmpty() ? Collections.singletonList(-1) : filterIds;

    }


    enum FilterType {
        EQUALS, IN
    }


    static class FilterEngine {

        private List<UserProfile> profiles;

        public FilterEngine(List<UserProfile> profiles) {
            this.profiles = profiles;
        }

        public List<Integer> applyFilter(String filterField, FilterType filterType, String filterValue) {
            List<String> valuesToFilter = new FilterValueParser(filterType, filterValue).parseValue();
            return filterValues(filterField, valuesToFilter);
        }


        private List<Integer> filterValues(String filterField, List<String> valuesToFilter) {
            Function<UserProfile, String> fieldReader = READER.get(filterField);
            List<Integer> ids = new ArrayList<>();
            for (UserProfile profile : profiles) {
                String value = fieldReader.apply(profile);
                if (valuesToFilter.contains(value)) {
                    ids.add(Integer.parseInt(profile.getId()));
                }
            }
            return ids;
        }
    }

    static class FilterValueParser {

        private FilterType filterType;
        private String filterValue;


        public FilterValueParser(FilterType filterType, String filterValue) {
            this.filterType = filterType;
            this.filterValue = filterValue;
        }

        public List<String> parseValue() {
            switch (filterType) {
                case EQUALS:
                    return visitEquals();
                case IN:
                    return visitIn();
            }
            return Collections.emptyList();
        }

        private List<String> visitIn() {
            if (filterValue != null) {
                String[] split = filterValue.split(",");
                List<String> values = new ArrayList<>(split.length);
                for (String value : split) {
                    values.add(value.trim());
                }
                return values;
            }
            return Collections.emptyList();
        }


        private List<String> visitEquals() {
            return Collections.singletonList(filterValue);
        }

    }


    private static String getApiContent(String apiUrl) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Scanner scanner = null;
            try {
                URL url = new URL(apiUrl);
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


    static class UserProfile {
        private String id;
        private String name;
        private String username;
        private String email;
        private Address address;
        private String website;
        private Company company;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        @Override
        public String toString() {
            return "UserProfile{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", address=" + address +
                    ", website='" + website + '\'' +
                    ", company=" + company +
                    '}';
        }
    }


    static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public Geo getGeo() {
            return geo;
        }

        public void setGeo(Geo geo) {
            this.geo = geo;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", suite='" + suite + '\'' +
                    ", city='" + city + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", geo=" + geo +
                    '}';
        }
    }

    static class Geo {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public String getLatString() {
            return String.valueOf(lat);
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public String getLngString() {
            return String.valueOf(lng);
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return "Geo{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }

    static class Company {
        private String name;
        private String basename;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBasename() {
            return basename;
        }

        public void setBasename(String basename) {
            this.basename = basename;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", basename='" + basename + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) throws Exception {
        ArrayList<String> filterCriteria = Lists.newArrayList("username", "EQUALS", "vinayk");
        List<Integer> integers = apiResponseParser(filterCriteria, 3);
        System.out.println(integers);

        ArrayList<String> filterCriteria2 = Lists.newArrayList("address.city", "EQUALS", "Kolkata");
        List<Integer> integers2 = apiResponseParser(filterCriteria2, 3);
        System.out.println(integers2);

        ArrayList<String> filterCriteria3 = Lists.newArrayList("address.city", "IN", "Mumbai,Kolkata");
        List<Integer> integers3 = apiResponseParser(filterCriteria3, 3);
        System.out.println(integers3);

        ArrayList<String> filterCriteria4 = Lists.newArrayList("username", "EQUALS", "Tom");
        List<Integer> integers4 = apiResponseParser(filterCriteria4, 3);
        System.out.println(integers4);

    }

}
