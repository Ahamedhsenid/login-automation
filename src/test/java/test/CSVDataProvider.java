package test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVDataProvider {

    @DataProvider(name = "csvLoginCredentials")
    public Object[][] readCsvData() throws IOException {
        String filePath = "src/test/java/resources/loginData.csv"; // Adjust the path if necessary

        // Using BufferedReader for better efficiency
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        int validEntries = 0;
        for (CSVRecord record : parser) {
            if (isRecordValid(record)) {
                validEntries++;
            }
        }

        Object[][] data = new Object[validEntries][3];

        // Reset reader and parser for actual data processing
        reader = new BufferedReader(new FileReader(filePath));
        parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        int index = 0;
        for (CSVRecord record : parser) {
            if (isRecordValid(record)) {
                String username = record.get("username");
                String password = record.get("password");
                String expectedOutcome = record.get("expectedOutcome").replace("\\n", "\n");

                data[index][0] = username;
                data[index][1] = password;
                data[index][2] = expectedOutcome;

                // Debug print to verify the data is read correctly
                System.out.println("Username: '" + username + "', Password: '" + password + "', Expected Outcome: '" + expectedOutcome + "'");
                index++;
            }
        }

        // Close the parser and reader to release resources
        parser.close();
        reader.close();

        return data;
    }

    private boolean isRecordValid(CSVRecord record) {
        String username = record.get("username");
        String password = record.get("password");
        String expectedOutcome = record.get("expectedOutcome");

        return username != null && !username.trim().isEmpty()
                && password != null && !password.trim().isEmpty()
                && expectedOutcome != null && !expectedOutcome.trim().isEmpty();
    }
}
