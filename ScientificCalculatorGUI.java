import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculatorGUI {
    private final JTextField display;
    private final StringBuilder input = new StringBuilder();

    public ScientificCalculatorGUI() {
        JFrame frame = new JFrame("Scientific Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 4, 5, 5));
        frame.add(buttonPanel, BorderLayout.CENTER);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sqrt", "x^y", "log", "C",
                "sin", "cos", "tan", "Exit"
        };

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            try {
                switch (command) {
                    case "=":
                        double result = evaluateExpression(input.toString());
                        display.setText(String.valueOf(result));
                        input.setLength(0);
                        break;
                    case "C":
                        input.setLength(0);
                        display.setText("");
                        break;
                    case "sqrt":
                        handleUnaryFunction(Math::sqrt);
                        break;
                    case "log":
                        handleUnaryFunction(Math::log10);
                        break;
                    case "sin":
                        handleUnaryFunction(val -> Math.sin(Math.toRadians(val)));
                        break;
                    case "cos":
                        handleUnaryFunction(val -> Math.cos(Math.toRadians(val)));
                        break;
                    case "tan":
                        handleUnaryFunction(val -> Math.tan(Math.toRadians(val)));
                        break;
                    case "x^y":
                        input.append("^");
                        display.setText(input.toString());
                        break;
                    case "Exit":
                        System.exit(0);
                        break;
                    default:
                        input.append(command);
                        display.setText(input.toString());
                }
            } catch (Exception ex) {
                display.setText("Error");
                input.setLength(0);
            }
        }

        private void handleUnaryFunction(java.util.function.DoubleUnaryOperator func) {
            if (input.length() == 0) {
                display.setText("Error");
                return;
            }
            double value = Double.parseDouble(input.toString());
            display.setText(String.valueOf(func.applyAsDouble(value)));
            input.setLength(0);
        }
    }

    private double evaluateExpression(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                double x = parseBase();
                while (eat('^')) x = Math.pow(x, parseFactor());
                return x;
            }

            double parseBase() {
                if (eat('+')) return parseBase();
                if (eat('-')) return -parseBase();

                double x;
                int startPos = pos;
                if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, pos));
                } else if (ch == '(') {
                    nextChar();
                    x = parseExpression();
                    eat(')');
                } else {
                    throw new RuntimeException("Unexpected character: " + (char) ch);
                }
                return x;
            }
        }.parse();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScientificCalculatorGUI::new);
    }
}
