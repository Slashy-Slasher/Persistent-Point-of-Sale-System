import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class View
{
    private static final String CSV_FILE_PATH = "Menu.csv";
    private final Model backend = new Model();
    public void funkyGUIMagic(JPanel panel)
    {
        List<String> items = backend.readItemsFromCSV();
        for (String item : items) {
            JButton button = new JButton(item);
            //Thinking there might be a better way of doing this maybe putting all buttons into this
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //JOptionPane.showMessageDialog(frame, "Button '" + item + "' was clicked! The Price is " + backend.readItemPrice(item));
                    backend.addToCurrentBill(backend.readItemPrice(item));
                    //JOptionPane.showMessageDialog(frame, "Current Bill: " + backend.getCurrentBill());
                }
            });
            panel.add(button);
        }
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("4H POS software");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3)); // Use a grid layout to stack buttons vertically
        frame.add(new JScrollPane(panel));

        /*
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,1));
        frame.add(new JScrollPane(panel2));
         */

        funkyGUIMagic(panel);


        JButton clearBillButton = new JButton("Clear");
        JButton displayBillButton = new JButton("Show Bill");
        JButton completeTransactionButton = new JButton("Complete Transaction");


        panel.add(clearBillButton);
        panel.add(displayBillButton);
        panel.add(completeTransactionButton);


        clearBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                backend.clearBill();
            }
        });
        displayBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(frame, "Current Bill: " + backend.getCurrentBill());

            }
        });
        completeTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //JOptionPane.showMessageDialog(frame, "Current Bill: " + backend.getCurrentBill());
                //This is going to be a little complicated just because
                backend.completeTransaction("", backend.getCurrentBill());
            }
        });


        frame.setVisible(true);
    }



}
