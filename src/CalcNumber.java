/**
 * Created by userdev on 1/14/2016.
 */
public class CalcNumber {

    private double number;
    private boolean isFloat;
    private int floatPower;

    public CalcNumber(int digit, boolean isFloat) {

        if (isFloat) {
            this.isFloat = true;
            number = 0;
            this.floatPower = 0;
        } else {
            this.isFloat = false;
            number = digit;
        }
    }

    public CalcNumber(double num) {

        number = num;
        isFloat = Math.ceil(num) != num;
    }

    public void addDigit(int digit) { /* when new digit is pressed */
        if (number < 0) {
            digit *= -1;
        }

        if (!isFloat) {
            number *= 10;
            number += digit;
        } else {
            floatPower++;
            double newDigit = digit * Math.pow(10,-floatPower);
            number += newDigit;
        }
    }

    public void setFloat() {
        isFloat = true;
    }

    public void setNegative() {
        this.number *= -1;
    }

    public double getNumber(){
        return number;
    }

    public String getNumberString() {
        if (isFloat)
            return Double.toString(number);

        return Integer.toString((int)number);
    }
}
