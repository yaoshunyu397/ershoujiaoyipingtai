package com.example.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CaptchaUtils {
    private static final String CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final int CODE_LENGTH = 4;
    private static final int LINE_COUNT = 20;
    private static final Random random = new Random();

    public static Map<String, String> generateCaptcha() throws IOException {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        
        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // 生成随机验证码
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            String s = String.valueOf(CODES.charAt(random.nextInt(CODES.length())));
            captcha.append(s);
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString(s, 20 * i + 10, 30);
        }
        
        // 画干扰线
        for (int i = 0; i < LINE_COUNT; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT),
                      random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
        
        g.dispose();
        
        // 转换为Base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        
        Map<String, String> result = new HashMap<>();
        result.put("code", captcha.toString());
        result.put("image", "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray()));
        return result;
    }
}