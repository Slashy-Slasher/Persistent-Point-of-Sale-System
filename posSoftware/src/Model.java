import java.awt.print.PrinterException;
import java.io.*;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private static final String CSV_FILE_PATH = "Menu.csv";
    private static double currentBill= 0;
    public void ReadCSVFile() {
        String csvFile = "Analytics.csv"; // Replace with the path to your CSV file

        try (Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String column1 = csvRecord.get(0);
                String column2 = csvRecord.get(1);
                System.out.println("Column 1: " + column1 + ", Column 2: " + column2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void completeTransaction(String item, double price)
    {
        //Add analytics code, currently I think the best way to do this is to check if there is an existing item within,
        //if there is then to increment, if not, add it then increment.
        //This would maintain the system that I have here
    }
    public void commitProfit(double price)
    {
        //Increment the profit.csv
    }


    public double getCurrentBill()
    {
        return currentBill;
    }
    public void addToCurrentBill(double additionalItemPrice)
    {
        currentBill += additionalItemPrice;
    }
    public void clearBill()
    {
        currentBill = 0;
    }

    /*
    public static void copyCSVFile(String sourcePath, String destinationPath) throws IOException {
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(sourcePath));
            writer = new FileWriter(destinationPath);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write("\n"); // Add a newline character to separate rows
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
     */

    public List<String> readItemsFromCSV()
    {
        List<String> items = new ArrayList<>();
        try (Reader reader = new FileReader(CSV_FILE_PATH);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                // Assuming the first column of the CSV contains the items
                String item = csvRecord.get(0);
                items.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
    public Double readItemPrice(String item)
    {
        double price = -1;
        try (Reader reader = new FileReader(CSV_FILE_PATH);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT))
        {
            for (CSVRecord csvRecord : csvParser)
            {
                if(csvRecord.get(0).equals(item))
                {
                    price = Double.parseDouble(csvRecord.get(1));
                }
            }
            return price;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
