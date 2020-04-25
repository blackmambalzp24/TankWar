package com.lzp;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ImageTest {

    @Test
    public void testImage() throws IOException {
//        BufferedImage bf = ImageIO.read(new File("F:\\Program Files\\thunder\\Program\\resources\\app\\static\\icon\\address.PNG"));
//        assertNotNull(bf);

        BufferedImage bf = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/0.gif"));
        assertNotNull(bf);
    }
}
