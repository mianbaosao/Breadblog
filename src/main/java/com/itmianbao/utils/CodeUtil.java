package com.itmianbao.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CodeUtil {

    private static final char[] chars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    private static final int SIZE = 4;
    private static final int LINES = 5;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private static final int FONT_SIZE = 30;

    public static Object[] createImage() {
        StringBuilder sb = new StringBuilder();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();
        graphic.setColor(Color.LIGHT_GRAY);
        graphic.fillRect(0, 0, WIDTH, HEIGHT);

        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            int n = random.nextInt(chars.length);
            graphic.setColor(getRandomColor());
            Font font = new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE);
            graphic.setFont(font);
            graphic.drawString(String.valueOf(chars[n]), i * WIDTH / SIZE, HEIGHT * 2 / 3);
            sb.append(chars[n]);
        }

        for (int i = 0; i < LINES; i++) {
            graphic.setColor(getRandomColor());
            graphic.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT),
                    random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }

        graphic.dispose(); // 添加此行以释放图形上下文资源

        return new Object[]{sb.toString(), image};
    }

    public static Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}