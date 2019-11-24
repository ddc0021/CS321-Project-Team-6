import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class DemoSqlite {

    public static void main(String[] args) {

        // CSV file path.
        File csvFile = new File("C:\\Demo\\personimport.csv");

        // Database and connection variables.
        File database = new File("C:\\sqlite\\Test.db");
        Connection connect = null;

        // Check if database file exists.
        if (!database.isFile()) {

            // Confirm incorrect database location and stop program execution.
            System.out.println("Error locating database.");
            System.exit(0);

        }

        try {

            // Connect to database.
            connect = DriverManager.getConnection("jdbc:sqlite:" + database.getPath());

        } catch (SQLException e) {

            // Confirm unsuccessful connection and stop program execution.
            System.out.println("Database connection unsuccessful.");
            System.exit(0);

        }

        // Check if CSV file exists.
        if (!csvFile.isFile()) {

            // Confirm incorrect CSV file location and stop program execution.
            System.out.println("Error locating CSV file.");
            System.exit(0);

        } else {

            try {

                // Assign CSV file to reader object and extract the records.
                Reader reader = Files.newBufferedReader(Paths.get(csvFile.getPath()));
                Iterable<CSVRecord> records =
                        CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

                // Record count.
                int recordCount = 0;

                // Query and statement.
                String sqlPersonInfo;
                PreparedStatement statement = null;

                // Insert person information into the database.
                for (CSVRecord record : records) {

                    // Construct the insert statement.
                    sqlPersonInfo = "INSERT INTO person ";
                    sqlPersonInfo += "(firstname, lastname, title, dob) ";
                    sqlPersonInfo += "VALUES (?, ?, ?, ?) ";

                    // Create statement and bind the parameters.
                    statement = connect.prepareStatement(sqlPersonInfo);
                    statement.setString(1, record.get("firstname"));
                    statement.setString(2, record.get("lastname"));
                    statement.setString(3, record.get("title"));
                    statement.setString(4, record.get("dob"));

                    // Execute query.
                    statement.executeUpdate();

                    // Increment the record count.
                    recordCount += 1;

                }

                // Provide feedback on the number of records added.
                if (recordCount == 0) {

                    System.out.println("No new person records added.");

                } else if (recordCount == 1) {

                    System.out.println(recordCount + " person record added.");

                } else {

                    System.out.println(recordCount + " person records added.");

                }

                // Close the statement and database connection.
                statement.close();
                connect.close();

            } catch (Exception e) {

                // Confirm error adding person information and quit.
                System.out.println("Error adding person information.");
                System.exit(0);

            }

        }

    }




}
