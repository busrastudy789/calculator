public class CalculatorLogic {
    private double currentValue = 0;
    private double previousValue = 0;
    private String operation = "";
    private String display = "0";
    private boolean newNumber = true;
    private double memory = 0;
    private double lastAnswer = 0;
    private boolean isInverse = false;
    private boolean isDegrees = true;
    
    public String getDisplay() {
        return display;
    }
    
    public void setInverse(boolean inverse) {
        this.isInverse = inverse;
    }
    
    public boolean isInverse() {
        return isInverse;
    }
    
    public void setDegrees(boolean degrees) {
        this.isDegrees = degrees;
    }
    
    public boolean isDegrees() {
        return isDegrees;
    }
    
    public void appendNumber(String num) {
        if (newNumber) {
            display = num;
            newNumber = false;
        } else {
            if (num.equals(".") && display.contains(".")) return;
            if (display.equals("0") && !num.equals(".")) {
                display = num;
            } else {
                display += num;
            }
        }
    }
    
    public void performOperation(String op) {
        currentValue = Double.parseDouble(display);
        
        if (!operation.isEmpty()) {
            calculateResult();
        } else {
            previousValue = currentValue;
        }
        
        operation = op;
        newNumber = true;
    }
    
    public void calculateResult() {
        if (operation.isEmpty()) return;
        
        currentValue = Double.parseDouble(display);
        double result = 0;
        
        switch (operation) {
            case "+":
                result = previousValue + currentValue;
                break;
            case "-":
                result = previousValue - currentValue;
                break;
            case "×":
                result = previousValue * currentValue;
                break;
            case "÷":
                if (currentValue != 0) {
                    result = previousValue / currentValue;
                } else {
                    display = "Error";
                    return;
                }
                break;
        }
        
        lastAnswer = result;
        display = formatResult(result);
        operation = "";
        newNumber = true;
    }
    
    public void equals() {
        calculateResult();
    }
    
    public void clearDisplay() {
        display = "0";
        newNumber = true;
    }
    
    public void clearAll() {
        display = "0";
        currentValue = 0;
        previousValue = 0;
        operation = "";
        newNumber = true;
    }
    
    public void deleteLastChar() {
        if (display.length() > 1) {
            display = display.substring(0, display.length() - 1);
        } else {
            display = "0";
        }
    }
    
    public void memoryAdd() {
        memory += Double.parseDouble(display);
        newNumber = true;
    }
    
    public void memorySubtract() {
        memory -= Double.parseDouble(display);
        newNumber = true;
    }
    
    public void memoryRecall() {
        display = formatResult(memory);
        newNumber = true;
    }
    
    public void memoryDelete() {
        memory = 0;
    }
    
    public void getAnswer() {
        display = formatResult(lastAnswer);
        newNumber = true;
    }
    
    public void randomNumber() {
        display = formatResult(Math.random());
        newNumber = true;
    }
    
    // Scientific operations
    public void sine() {
        double value = Double.parseDouble(display);
        if (isDegrees) value = Math.toRadians(value);
        double result = isInverse ? Math.asin(value) : Math.sin(value);
        if (isDegrees) result = Math.toDegrees(result);
        display = formatResult(result);
        newNumber = true;
    }
    
    public void cosine() {
        double value = Double.parseDouble(display);
        if (isDegrees) value = Math.toRadians(value);
        double result = isInverse ? Math.acos(value) : Math.cos(value);
        if (isDegrees) result = Math.toDegrees(result);
        display = formatResult(result);
        newNumber = true;
    }
    
    public void tangent() {
        double value = Double.parseDouble(display);
        if (isDegrees) value = Math.toRadians(value);
        double result = isInverse ? Math.atan(value) : Math.tan(value);
        if (isDegrees) result = Math.toDegrees(result);
        display = formatResult(result);
        newNumber = true;
    }
    
    public void logarithm() {
        double value = Double.parseDouble(display);
        if (value <= 0) {
            display = "Error";
            return;
        }
        double result = isInverse ? Math.pow(10, value) : Math.log10(value);
        display = formatResult(result);
        newNumber = true;
    }
    
    public void naturalLog() {
        double value = Double.parseDouble(display);
        if (value <= 0) {
            display = "Error";
            return;
        }
        double result = isInverse ? Math.exp(value) : Math.log(value);
        display = formatResult(result);
        newNumber = true;
    }
    
    public void squareRoot() {
        double value = Double.parseDouble(display);
        if (value < 0) {
            display = "Error";
            return;
        }
        double result = Math.sqrt(value);
        display = formatResult(result);
        newNumber = true;
    }
    
    public void square() {
        double value = Double.parseDouble(display);
        double result = value * value;
        display = formatResult(result);
        newNumber = true;
    }
    
    public void factorial() {
        double value = Double.parseDouble(display);
        if (value < 0 || value != (int)value) {
            display = "Error";
            return;
        }
        double result = 1;
        for (int i = 1; i <= (int)value; i++) {
            result *= i;
        }
        display = formatResult(result);
        newNumber = true;
    }
    
    public void percentage() {
        double value = Double.parseDouble(display);
        if (!operation.isEmpty()) {
            value = previousValue * value / 100;
        } else {
            value = value / 100;
        }
        display = formatResult(value);
        newNumber = true;
    }
    
    public void power(double base, double exponent) {
        double result = Math.pow(base, exponent);
        display = formatResult(result);
        newNumber = true;
    }
    
    public void pi() {
        display = formatResult(Math.PI);
        newNumber = true;
    }
    
    public void euler() {
        display = formatResult(Math.E);
        newNumber = true;
    }
    
    private String formatResult(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return "Error";
        }
        
        if (value == (long)value) {
            return String.format("%d", (long)value);
        } else {
            return String.format("%.6f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
        }
    }
    
    public double getCurrentValue() {
        try {
            return Double.parseDouble(display);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
