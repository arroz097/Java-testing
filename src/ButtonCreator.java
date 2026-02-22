import javax.swing.*;

public class ButtonCreator {

    private static final Utility utility = new Utility();

    private boolean isMoving = false;

    JFrame frame;
    JButton button;

    public ButtonCreator(String frameText, String buttonText) {

        // configura o frame
        this.frame = new JFrame(frameText);
        this.frame.setSize(300, 200);
        this.frame.setLocationRelativeTo(null); // centraliza o gui no meio da tela
        this.frame.setLayout(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // configura o botão
        this.button = new JButton(buttonText);
        this.button.setBounds(70, 70, 150, 30);
        this.button.setFocusPainted(false);

        this.frame.add(this.button);
        this.frame.setVisible(true);

    }


    public void moveButton(int xPos, int yPos) {
        if (this.isMoving) return;

        this.isMoving = true;

        new Thread(() -> {
            double xLocation = 0;
            double yLocation = 0;

            int currentX = this.frame.getX();
            int currentY = this.frame.getY();

            this.button.setText("moving...");

            for (int i = 0; i < 30; i++) {
                xLocation = utility.lerp(xLocation, xPos, 0.1);
                yLocation = utility.lerp(yLocation, yPos, 0.1);

                this.frame.setLocation(currentX + (int) xLocation, currentY + (int) yLocation);

                utility.sleep(0.1);
            }

            utility.sleep(1);

            this.isMoving = false;

            this.button.setText("move!");

        }).start();

    }

}
