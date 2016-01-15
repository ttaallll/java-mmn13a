
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calc extends JFrame implements ActionListener {

    private JLabel label;
    private CalcManager manager;

    public Calc() {
        super("Calc");

        /* the core of the calc, the calc manager */
        manager = new CalcManager();

        label = new JLabel("0", SwingConstants.CENTER);
        label.setSize(100, 50);
        setLabelFontSize(label);
        add(label, BorderLayout.NORTH);

        JLabel labelMe = new JLabel("Tal Pais (:", SwingConstants.CENTER);
        labelMe.setSize(100, 30);
        setLabelFontSize(labelMe);
        add(labelMe, BorderLayout.SOUTH);

        createNumberButtons();
        createOperatorButtons();

        setSize(500, 300);
        setVisible(true);
    }

    private void setLabelFontSize(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }

    private void createNumberButtons() {

        JPanel buttonPane = new JPanel();
        buttonPane.setPreferredSize(new Dimension(200, 100));
        buttonPane.setLayout(new GridLayout(5,2));

        int currentNumber = 0;

        // create numbers buttons
        for (int i = 0; i < 10; ++i) {
            JButton button1 = new JButton(Integer.toString(currentNumber));
            button1.addActionListener(this);
            buttonPane.add(button1);

            ++currentNumber;

        }

        add(buttonPane, BorderLayout.WEST);
    }

    private void createOperatorButtons() {

        /* create the right side of the buttons, the operator buttons */

        JPanel buttonPane = new JPanel();
        buttonPane.setPreferredSize(new Dimension(200, 100));
        buttonPane.setLayout(new GridLayout(4,2));

        JButton button1;

        button1 = new JButton("C");
        button1.addActionListener(this);
        buttonPane.add(button1);

        button1 = new JButton("+");
        button1.addActionListener(this);
        buttonPane.add(button1);

        button1 = new JButton("-");
        button1.addActionListener(this);
        buttonPane.add(button1);

        button1 = new JButton("*");
        button1.addActionListener(this);
        buttonPane.add(button1);

        button1 = new JButton("/");
        button1.addActionListener(this);
        buttonPane.add(button1);

        button1 = new JButton(".");
        button1.addActionListener(this);
        buttonPane.add(button1);

        button1 = new JButton("+/-");
        button1.addActionListener(this);
        buttonPane.add(button1);

        button1 = new JButton("=");
        button1.addActionListener(this);
        buttonPane.add(button1);

        add(buttonPane, BorderLayout.EAST);
    }

    public void actionPerformed(ActionEvent e) {

        String btnPressed = e.getActionCommand();

        /* send the button to the calc manager */
        String output1 = manager.pressButton(btnPressed);

        /* put the output of the calc manager to the label */
        label.setText(output1);

    }



    public static void main(String[] args) {
        Calc frame = new Calc();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
