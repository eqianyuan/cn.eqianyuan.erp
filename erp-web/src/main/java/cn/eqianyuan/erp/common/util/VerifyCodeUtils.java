package cn.eqianyuan.erp.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码工具类
 * Created by jason on 2016-05-22.
 */
public class VerifyCodeUtils {
    //创建Random对象
    private static Random random = new Random();

    //初始化种子
    private static String[] seeds = {"2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "j",
            "k", "l", "m", "n", "p", "q", "r", "s", "t",
            "A", "B", "C", "D", "E", "F", "G", "H", "J",
            "K", "L", "M", "N", "P", "Q", "R", "S", "T"};

    /**
     * 随机生成包含验证码字符串
     *
     * @param num 验证码内容字符个数
     * @return
     */
    public static String random(int num) {
        int seedsLen = seeds.length;
        //接收随机字符
        StringBuilder randomStr = new StringBuilder();
        //随机产生4个字符的字符串
        for (int i = 0; i < num; i++) {
            randomStr.append(seeds[random.nextInt(seedsLen)]);
        }
        return randomStr.toString();
    }

    /**
     * 随机产生定义的颜色
     *
     * @return
     */
    private static Color getRandColor() {
        int colorAlpha = 255;
        //随机产生一个0 ~ 255 值
        return new Color(random.nextInt(colorAlpha), random.nextInt(colorAlpha), random.nextInt(colorAlpha));
    }

    /**
     * 产生随机字体
     *
     * @return
     */
    private static Font getFont() {
        //字体格式
        int fontStyle[] = {Font.BOLD, Font.ITALIC, Font.PLAIN, Font.BOLD + Font.ITALIC};
        //最大字体大小
        int maxFontSize[] = {25, 30, 35};

        return new Font(null, fontStyle[random.nextInt(fontStyle.length)], maxFontSize[random.nextInt(maxFontSize.length)]);
    }

    /**
     * 生成图片
     *
     * @throws IOException
     */
    public static void render(String randomStr, OutputStream out, int width, int height) throws IOException {
        //在内存中创建图像
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        //获取图形上下文
        Graphics2D g = (Graphics2D) bi.getGraphics();
        //话边框
        g.setColor(Color.WHITE);

        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);

        //画认证码，每个认证码在不同的水平位置
        String str1[] = new String[randomStr.length()];
        for (int i = 0; i < str1.length; i++) {
            str1[i] = randomStr.substring(i, i + 1);
            int y;
            //随机生成验证码字符水平偏移量
            if (random.nextInt(2) > 0) {
                y = height - random.nextInt(3);
            } else {
                y = height + random.nextInt(3);
            }
            //随机生成颜色
            g.setColor(getRandColor());
            g.setFont(getFont());
            g.drawString(str1[i], 20 * i + 20, y);
        }
        //随机产生干扰点，并用不同的颜色表示，事图像的认证码不易被其他程序探测到
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            //随机画各种颜色的线
            g.setColor(getRandColor());
            g.drawOval(x, y, 0, 0);
        }
        //画干扰线
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            //随机画各种颜色线
            g.setColor(getRandColor());
            g.drawLine(x, y, x1, y1);
        }
        //图像生效
        g.dispose();
        //输出页面
        ImageIO.write(bi, "jpeg", out);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //获取随机字符串
        String randomStr = random(4);
        System.out.println(randomStr);
        //生成图片
        render(randomStr, new FileOutputStream("D:\\test.jpg"), 120, 30);
    }

}
