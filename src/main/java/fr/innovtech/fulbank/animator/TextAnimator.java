package fr.innovtech.fulbank.animator;

import java.util.Random;

public class TextAnimator implements Runnable {

    private final String text;
    private final int animationTime;
    private final TextOutput textOutput;
    private final Random random = new Random();




    public TextAnimator(String text, int animationTime, TextOutput textField) {
        this.text = text;
        this.animationTime = animationTime;
        this.textOutput = textField;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i <= text.length(); i++) {
                String textAtThisPoint = text.substring(0,i);

                textOutput.writeText(textAtThisPoint);
                Thread.sleep(animationTime + random.nextInt(50));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}