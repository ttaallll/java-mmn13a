/**
 * Created by userdev on 1/14/2016.
 */
public class CalcManager {

    private CalcNumber number1;
    private CalcNumber number2;
    private boolean startedNumber2; /* check if the number2 is already started to be typed */
    private String lastOperator; /* save the last operator pressed */

    public CalcManager() {
        number1 = new CalcNumber(0, false);
        lastOperator = "";
    }
    private void cleanCalc() {
        number1 = new CalcNumber(0, false);
        number2 = new CalcNumber(0, false);
        startedNumber2 = false;
        lastOperator = "";
    }

    public String pressButton(String btn) {

        String output1 = "";

        if (btn.equals("C")) { /* clean the calc */

            cleanCalc();
            output1 = number1.getNumberString();
        }
        else if (btn.equals("0") ||
                btn.equals("1") ||
                btn.equals("2") ||
                btn.equals("3") ||
                btn.equals("4") ||
                btn.equals("5") ||
                btn.equals("6") ||
                btn.equals("7") ||
                btn.equals("8") ||
                btn.equals("9")
                ) {

            int newDigit = Integer.parseInt(btn);

            if (startedNumber2) { /* check if we already started to type the number 2 */
                number2.addDigit(newDigit); /* if so, add new digit */
            } else {
                startedNumber2 = true; /* otherwise, create new number with the new digit */
                number2 = new CalcNumber(newDigit, false);
            }

            output1 = number2.getNumberString();

        } else if (btn.equals("+") ||
                btn.equals("-") ||
                btn.equals("*") ||
                btn.equals("/") ||
                btn.equals("=")
                ) {

            boolean isErr = false;

            if (startedNumber2) { /* do the operator only if the 2 numbers are typed */

                double result = 0;

                /* check which operator */
                if (lastOperator.equals("+")) {
                    result = number1.getNumber() + number2.getNumber();
                } else if (lastOperator.equals("-")) {
                    result = number1.getNumber() - number2.getNumber();
                } else if (lastOperator.equals("*")) {
                    result = number1.getNumber() * number2.getNumber();
                } else if (lastOperator.equals("/")) {

                    /* if we divide in 0 */
                    if (number2.getNumber() == 0) {
                        isErr = true;
                    }
                    else {
                        result = number1.getNumber() / number2.getNumber();
                    }

                } else {
                    result = number2.getNumber();
                }


                number1 = new CalcNumber(result);

            }

            if (isErr) {
                cleanCalc();
                output1 = "Err";
            } else {
                output1 = number1.getNumberString();
                startedNumber2 = false;
                lastOperator = btn;
            }
        } else if (btn.equals(".")) { /* when . is typed, convert the number to be double */

            if (startedNumber2) {
                number2.setFloat();
            } else {
                number1.setFloat();
                number2 = number1;
                startedNumber2 = true;
            }

            output1 = number2.getNumberString();

        }  else if (btn.equals("+/-")) { /* when plus minus is pressed, change the negativity of the number */
            if (startedNumber2) {
                number2.setNegative();
                output1 = number2.getNumberString();
            } else {
                number1.setNegative();
                output1 = number1.getNumberString();
            }

        }

        return output1;
    }
}
