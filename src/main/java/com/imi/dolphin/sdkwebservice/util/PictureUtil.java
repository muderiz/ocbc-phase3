/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.util;

import com.imi.dolphin.sdkwebservice.property.AppProperties;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cokkyturnip
 */
@Component
public class PictureUtil {

    @Autowired
    AppProperties appProp;

//	public String generatePic(String pesan, String contactId) {
//		UUID guid = UUID.randomUUID();
//		String filePath = appProp.OCBC_FOLDER_RESOURCES
//				+ appProp.OCBC_FOLDER_PICTURES
//				+ contactId + "__"
//				+ guid.toString()
//				+ ".png";
//
//		int width = 500;
//		int height = 500;
////		boolean status = true;
//		String text = pesan;
//
////		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
////		Graphics2D g2d = img.createGraphics();
////		Font font = new Font("Arial", Font.PLAIN, 24);
////		g2d.setFont(font);
////
////		FontMetrics fm = g2d.getFontMetrics();
////		int width = fm.stringWidth(text);
////		int height = fm.getHeight();
////		g2d.dispose();
////		System.out.println(width + " === " + height);
//		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2d = img.createGraphics();
//
////		Font font = new Font("Arial", Font.PLAIN, 24);
//		//g2d = img.createGraphics();
//		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
//		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
//		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//
////		g2d.setColor(Color.WHITE);
////		g2d.fillRect(0, 0, width, height);
////		g2d.setColor(Color.RED);
//
//                Color SpesialReq = new Color(0, 162, 163);
//                
//		g2d.setBackground(SpesialReq);
//		g2d.clearRect(0, 0, width, height);
//
////		FontMetrics fm = g2d.getFontMetrics();
////		g2d.setColor(Color.BLACK);
//		int x = 50;
//		int y = 125;
//		int space = 10;
//		String[] arrText = text.split("<br>");
//		for (String txt : arrText) {
//			Font font = new Font("Arial", Font.PLAIN, 28);
//			if (txt.contains("<b>")) {
//				font = new Font("Arial", Font.BOLD, 28);
//				txt = txt.replace("<b>", "").replace("</b>", "");
//			} else if (txt.contains("<i>")) {
//				font = new Font("Arial", Font.ITALIC, 28);
//				txt = txt.replace("<i>", "").replace("</i>", "");
//			}
//
//			g2d.setFont(font);
//			g2d.setColor(Color.WHITE);
//
//			FontMetrics fm = g2d.getFontMetrics();
//			y += fm.getAscent() + space;
//			g2d.drawString(txt, x, y);
//
//		}
//
////		g2d.drawString(text, 0, fm.getAscent());
////		System.out.println(fm.getAscent());
//		g2d.dispose();
////		String id = this.getContactID(extensionRequest);
//		try {
//			ImageIO.write(img, "png",
//					new File(filePath));
//
//			filePath = appProp.OCBC_FOLDER_PICTURES
//					+ contactId + "__"
//					+ guid.toString()
//					+ ".png";
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			filePath = "";
//		} catch (Exception e) {
//			e.printStackTrace();
//			filePath = "";
//		}
//
//		if (filePath.charAt(0) == '.') {
//			filePath = filePath.substring(1, filePath.length());
//		}
//		return filePath;
//	}
    public String generatePic(String pesan, String contactId, String productType, boolean investmentType) {
        UUID guid = UUID.randomUUID();
        String filePath = appProp.OCBC_FOLDER_RESOURCES
                + appProp.OCBC_FOLDER_PICTURES
                + contactId + "__"
                + guid.toString()
                + ".png";

        int width = 500;
        int height = 500;
//		boolean status = true;
        String text = pesan;
        String urlimage = "";

        switch (productType) {
            case "MFA":
            case "MFB":
                if (investmentType == false) {
                    urlimage = appProp.getOCBC_PICTURES_RECOMPRODUCT_MONTHLY();

                } else {
                    urlimage = appProp.getOCBC_PICTURES_RECOMPRODUCT_LUMPSUM();
                }
                break;
            case "TK":
                urlimage = appProp.getOCBC_PICTURES_RECOMPRODUCT_TAKA();

                break;
            case "DP":
                urlimage = appProp.getOCBC_PICTURES_RECOMPRODUCT_DEPOSITO();

                break;
        }

        try {
            URL input = new URL(urlimage);

//        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            BufferedImage img = ImageIO.read(input);
            int imageType = BufferedImage.TYPE_INT_ARGB;
            BufferedImage watermarked = new BufferedImage(img.getWidth(), img.getHeight(), imageType);
//		Font font = new Font("Arial", Font.PLAIN, 24);
            //g2d = img.createGraphics();
            Graphics2D g2d = (Graphics2D) watermarked.createGraphics();
//		g2d.setColor(Color.WHITE);
//		g2d.fillRect(0, 0, width, height);
//		g2d.setColor(Color.RED);
            g2d.drawImage(img, 0, 0, null);
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(alphaChannel);

//		FontMetrics fm = g2d.getFontMetrics();
//		g2d.setColor(Color.BLACK);
            int x = 40;
            int y = 150;
            int space = 10;
            String[] arrText = text.split("<br>");
            for (String txt : arrText) {
                Font font = new Font("Arial", Font.PLAIN, 31);
                if (txt.contains("<b>")) {
                    font = new Font("Arial", Font.BOLD, 31);
                    txt = txt.replace("<b>", "").replace("</b>", "");
                } else if (txt.contains("<i>")) {
                    font = new Font("Arial", Font.ITALIC, 31);
                    txt = txt.replace("<i>", "").replace("</i>", "");
                }

                g2d.setFont(font);
                g2d.setColor(Color.WHITE);

                FontMetrics fm = g2d.getFontMetrics();
                y += fm.getAscent() + space;
                g2d.drawString(txt, x, y);

            }

//		g2d.drawString(text, 0, fm.getAscent());
//		System.out.println(fm.getAscent());
            g2d.dispose();
//		String id = this.getContactID(extensionRequest);
            ImageIO.write(watermarked, "png",
                    new File(filePath));

            filePath = appProp.OCBC_FOLDER_PICTURES
                    + contactId + "__"
                    + guid.toString()
                    + ".png";

        } catch (IOException ex) {
            ex.printStackTrace();
            filePath = "";
        } catch (Exception e) {
            e.printStackTrace();
            filePath = "";
        }

        if (filePath.charAt(0) == '.') {
            filePath = filePath.substring(1, filePath.length());
        }
        return filePath;
    }
}
