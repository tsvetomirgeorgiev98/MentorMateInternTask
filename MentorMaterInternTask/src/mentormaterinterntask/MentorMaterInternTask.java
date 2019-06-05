package mentormaterinterntask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.*;

/**
 *
 * @author Tsvetomir Georgiev
 */
public class MentorMaterInternTask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        //report definition
        final int topPerformersThreshold = 10;
        final boolean useExperienceMultiplier = false;
        final int periodLimit = 20;

        // saving report definition to a json object
        JSONObject reportDefinition = new JSONObject();
        reportDefinition.put("topPerformersThreshhold", topPerformersThreshold);
        reportDefinition.put("useExperienceMultiplier", useExperienceMultiplier);
        reportDefinition.put("periodLimit", periodLimit);

        //writing the report definition to a json file
        FileWriter repdef = new FileWriter("Report Definition.json");
        repdef.write(reportDefinition.toJSONString());
        repdef.flush();

        //used to pick a random name
        String[] names = {"Emma Smith", "Liam Johnson", "Olivia Williams",
            "Noah Jones", "Ava Brown", "William Davis", "Isabella Miller",
            "James Wilson", "Sophia Moore", "Oliver Taylor", "Charlotte Anderson",
            "Benjamin Thomas", "Mia Jackson", "Elijah White", "Amelia Harris",
            "Lucas Martin", "Harper Thompson", "Mason Garcia", "Evelyn Martinez", "Logan Robinson"};

        //list to hold all the data plus the scores 
        ArrayList<Employee> employees = new ArrayList<>();
 
        //an array that holds the data without the scores in json
        JSONArray list = new JSONArray();

        //picking names from the random names array and generating random values for the other variables for every employee
        for (int x = 0; x < 20; x++) {
            // temporary json object used to save all the data as one object
            JSONObject person = new JSONObject();

            int totalSales = (int) (Math.random() * 250) + 1;
            int salesPeriod = (int) (Math.random() * 20) + 1;
            double experienceMultiplier = (Math.random() * 0.5);
            experienceMultiplier = Math.round(experienceMultiplier * 100.0) / 100.0;
            double score = 0.0;
            if (useExperienceMultiplier) {
                score = ((double) totalSales / (double) salesPeriod) * experienceMultiplier;
                score = Math.round(score * 100.0) / 100.0;
            } else {
                score = (double) totalSales / (double) salesPeriod;
                score = Math.round(score * 100.0) / 100.0;
            }

            
            person.put("name", names[x]);
            person.put("totalSales", totalSales);
            person.put("salesPeriod", salesPeriod);
            person.put("experienceMultiplier", experienceMultiplier);
            Employee temp = new Employee(names[x], totalSales, salesPeriod, experienceMultiplier, score);
            
            // adding the data to the json array and the arraylist and resetting everything for the next run
            list.add(person);
            employees.add(temp);
            temp = null;
            person = null;
            score = 0;

        }
        
        // writing all the data from the json array to a json file
        try {
            FileWriter f = new FileWriter("Data.json");

            f.write(list.toJSONString());

            f.flush();
        } catch (Exception e) {
            System.out.println(e);
        }

        
        //creating string for csv file
        String csv = "Name  ,Score" + "\n";
        int topPerformers = (int) (20 * ((double) topPerformersThreshold / 100.0)); // calculating number of top performers
        double topScore = 0.0; // helper variable
        ArrayList<Employee> tempList = new ArrayList(employees); // temporary list to hold all of the data
        Employee temp = new Employee(); // temporary employee object used to get details and remove it from the temporary list

        // every time these loops run the person with the top scored is determined and if that person's sales period is 
        // less that or equal to the time limit the person's details are added to the csv string and are removed from the
        // temporary arraylist
        for (int x = 0; x < topPerformers; x++) {
            for (Employee e : tempList) {
                if (e.getScore() > topScore) {
                    if (e.getSalesPeriod() <= periodLimit) {
                        topScore = e.getScore();
                        temp = e;
                    }

                }

            }
            csv += temp.getName() + ", " + temp.getScore() + "\n";
            if(tempList.contains(temp)){
                tempList.remove(temp);
            }
            

            topScore = 0.0;
            temp = new Employee("Null", 0, 0, 0.0, 0.0);

        }
        
        // writing csv string to a file
        FileWriter csvWriter = new FileWriter("Report.csv");
        csvWriter.write(csv);
        csvWriter.flush();
        
        System.out.println("The three files called Data.json, Report Definition.json and Report.csv are all in the main folder of this project.");
        
        
        
        

    }

}
