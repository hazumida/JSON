import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String readString(String nameFile) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                line = line + s;
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        return line;
    }

    public static List jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        JSONParser parser = new JSONParser();
        try {
            JSONArray array = (JSONArray) parser.parse(json);
            for (Object obj : array) {
                Employee employee = gson.fromJson(obj.toString(), Employee.class);
                list.add(employee);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {

        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }

    }
}