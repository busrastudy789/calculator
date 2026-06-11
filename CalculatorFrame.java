import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorFrame extends JFrame {
    private JLabel displayLabel;
    private JPanel buttonPanel;
    private CalculatorLogic calculator;
    private String currentMode = "GENERAL";
    private JButton[] scientificButtons;
    private boolean waitingForSecondNumber = false;
    
    public CalculatorFrame(String username) {
        calculator = new CalculatorLogic();
        
        setTitle("Calculator - Welcome " + username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(50, 50, 50));
        
        // Header with mode switching
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        headerPanel.setBackground(new Color(40, 40, 40));
        
        JButton generalBtn = new JButton("General");
        generalBtn.setFont(new Font("Arial", Font.BOLD, 12));
        generalBtn.setPreferredSize(new Dimension(100, 35));
        generalBtn.setBackground(new Color(100, 150, 200));
        generalBtn.setForeground(Color.WHITE);
        generalBtn.setFocusPainted(false);
        generalBtn.addActionListener(e -> switchMode("GENERAL"));
        
        JButton scientificBtn = new JButton("Scientific");
        scientificBtn.setFont(new Font("Arial", Font.BOLD, 12));
        scientificBtn.setPreferredSize(new Dimension(100, 35));
        scientificBtn.setBackground(new Color(100, 100, 150));
        scientificBtn.setForeground(Color.WHITE);
        scientificBtn.setFocusPainted(false);
        scientificBtn.addActionListener(e -> switchMode("SCIENTIFIC"));
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 12));
        logoutBtn.setPreferredSize(new Dimension(100, 35));
        logoutBtn.setBackground(new Color(200, 100, 100));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        
        headerPanel.add(generalBtn);
        headerPanel.add(scientificBtn);
        headerPanel.add(logoutBtn);
        
        // Display panel
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(new Color(60, 60, 60));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        displayLabel = new JLabel(calculator.getDisplay());
        displayLabel.setFont(new Font("Arial", Font.BOLD, 32));
        displayLabel.setForeground(new Color(0, 255, 0));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        displayLabel.setPreferredSize(new Dimension(450, 80));
        displayPanel.add(displayLabel);
        
        // Button panel (will be updated based on mode)
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(50, 50, 50));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Assemble frame
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(displayPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        switchMode("GENERAL");
        setVisible(true);
    }
    
    private void switchMode(String mode) {
        currentMode = mode;
        buttonPanel.removeAll();
        
        if (mode.equals("GENERAL")) {
            createGeneralButtons();
        } else {
            createScientificButtons();
        }
        
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
    
    private void createGeneralButtons() {
        buttonPanel.setLayout(new GridLayout(6, 5, 5, 5));
        
        String[][] buttons = {
            {"AC", "CE", "Ans", "÷", ""},
            {"7", "8", "9", "×", "M+"},
            {"4", "5", "6", "-", "M-"},
            {"1", "2", "3", "+", "RND"},
            {"0", ".", "=", "", ""},
            {"", "", "", "", ""}
        };
        
        for (String[] row : buttons) {
            for (String label : row) {
                if (label.isEmpty()) {
                    buttonPanel.add(new JLabel());
                } else {
                    JButton btn = createButton(label);
                    buttonPanel.add(btn);
                }
            }
        }
    }
    
    private void createScientificButtons() {
        buttonPanel.setLayout(new GridLayout(8, 6, 5, 5));
        
        String[][] buttons = {
            {"Deg", "Rad", "Inv", "x!", "sin", "cos"},
            {"tan", "ln", "log", "√", "x^y", "EXP"},
            {"π", "Ans", "e^x", "10^x", "x²", "ⁿ√x"},
            {"Rnd", "(", ")", "%", "CE", "AC"},
            {"÷", "×", "-", "+", "", ""},
            {"7", "8", "9", "", "", ""},
            {"4", "5", "6", "", "", ""},
            {"1", "2", "3", "0", ".", "="}
        };
        
        scientificButtons = new JButton[50];
        int buttonIndex = 0;
        
        for (String[] row : buttons) {
            for (String label : row) {
                if (label.isEmpty()) {
                    buttonPanel.add(new JLabel());
                } else {
                    JButton btn = createButton(label);
                    if (buttonIndex < scientificButtons.length) {
                        scientificButtons[buttonIndex] = btn;
                    }
                    buttonPanel.add(btn);
                }
                buttonIndex++;
            }
        }
    }
    
    private JButton createButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Color coding
        if (isNumber(label) || label.equals(".")) {
            btn.setBackground(new Color(70, 70, 70));
            btn.setForeground(new Color(200, 200, 200));
        } else if (label.equals("=")) {
            btn.setBackground(new Color(50, 150, 50));
            btn.setForeground(Color.WHITE);
        } else if (label.equals("AC") || label.equals("CE") || label.equals("Inv")) {
            btn.setBackground(new Color(200, 100, 100));
            btn.setForeground(Color.WHITE);
        } else if (label.equals("Deg") || label.equals("Rad")) {
            btn.setBackground(new Color(100, 100, 150));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(100, 150, 200));
            btn.setForeground(Color.WHITE);
        }
        
        btn.addActionListener(e -> handleButtonClick(label));
        
        return btn;
    }
    
    private void handleButtonClick(String label) {
        try {
            switch (label) {
                // Basic operations
                case "AC":
                    calculator.clearAll();
                    break;
                case "CE":
                    calculator.clearDisplay();
                    break;
                case "÷":
                case "×":
                case "-":
                case "+":
                    calculator.performOperation(label);
                    break;
                case "=":
                    calculator.equals();
                    break;
                case ".":
                    calculator.appendNumber(".");
                    break;
                case "Ans":
                    calculator.getAnswer();
                    break;
                case "RND":
                    calculator.randomNumber();
                    break;
                case "M+":
                    calculator.memoryAdd();
                    break;
                case "M-":
                    calculator.memorySubtract();
                    break;
                
                // Scientific operations
                case "sin":
                    calculator.sine();
                    break;
                case "cos":
                    calculator.cosine();
                    break;
                case "tan":
                    calculator.tangent();
                    break;
                case "log":
                    calculator.logarithm();
                    break;
                case "ln":
                    calculator.naturalLog();
                    break;
                case "√":
                    calculator.squareRoot();
                    break;
                case "x²":
                    calculator.square();
                    break;
                case "x!":
                    calculator.factorial();
                    break;
                case "%":
                    calculator.percentage();
                    break;
                case "π":
                    calculator.pi();
                    break;
                case "e^x":
                    if (currentMode.equals("SCIENTIFIC")) {
                        calculator.naturalLog(); // Use inverse of ln
                    }
                    break;
                case "10^x":
                    if (currentMode.equals("SCIENTIFIC")) {
                        calculator.logarithm(); // Use inverse of log
                    }
                    break;
                case "ⁿ√x":
                    if (currentMode.equals("SCIENTIFIC")) {
                        showPowerDialog();
                    }
                    break;
                case "x^y":
                    if (currentMode.equals("SCIENTIFIC")) {
                        calculator.performOperation("^");
                        waitingForSecondNumber = true;
                    }
                    break;
                case "EXP":
                    calculator.appendNumber("E");
                    break;
                case "Deg":
                    calculator.setDegrees(true);
                    JOptionPane.showMessageDialog(this, "Switched to Degrees", "Mode", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Rad":
                    calculator.setDegrees(false);
                    JOptionPane.showMessageDialog(this, "Switched to Radians", "Mode", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Inv":
                    calculator.setInverse(!calculator.isInverse());
                    break;
                case "(":
                case ")":
                    calculator.appendNumber(label);
                    break;
                
                // Numbers
                default:
                    if (isNumber(label)) {
                        calculator.appendNumber(label);
                    }
            }
        } catch (NumberFormatException e) {
            calculator.clearAll();
        }
        
        updateDisplay();
    }
    
    private void showPowerDialog() {
        String input = JOptionPane.showInputDialog(this, "Enter the root index:", "2");
        if (input != null && !input.isEmpty()) {
            try {
                double root = Double.parseDouble(input);
                double base = calculator.getCurrentValue();
                double result = Math.pow(base, 1.0 / root);
                calculator.power(result, 1);
                updateDisplay();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void updateDisplay() {
        displayLabel.setText(calculator.getDisplay());
    }
    
    private boolean isNumber(String str) {
        return str.matches("\\d");
    }
}
