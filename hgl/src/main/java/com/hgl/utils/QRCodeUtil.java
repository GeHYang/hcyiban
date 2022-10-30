package com.hgl.utils;

import javax.imageio.ImageIO;
import com.google.zxing.*;
import java.awt.image.BufferedImage;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import java.io.File;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import com.google.zxing.common.HybridBinarizer;
import java.util.Map;

/**
 * @author li
 * @date 2021/06/03 15:38
 *
 * 生成二维码，保存图片到指定服务
 */
public class QRCodeUtil {

    //生成二维码颜色
    private static final int BLACK = 0xFF000000;
    //生成二维码颜色
    private static final int WHITE = 0xFFFFFFFF;
    private static final String prePath = "D:\\study\\project\\zhinongyoudao\\hcyb1\\src\\main\\webapp\\qrcodes\\";
    private static final String preUrl = "http://localhost:8080/ht/qrcode/";

    /**
     * @Description:  示例代码
     */

 	public static void main(String[] args) throws Exception {
         String fileName = new Date().getTime() + ".png";
        CodeCreate("http://localhost:8080/ht/qrcode/1653561360000.png", 500, 500, fileName, "png");
    }

    /**
     * 生成二维码
     * @param text    二维码内容
     * @param width    二维码宽
     * @param height    二维码高
     * @param fileName    二维码名(需后缀)
     * @param imageType     二维码生成格式
     */
    public static String CodeCreate(String text, int width, int height, String fileName, String imageType){
        Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();
        //设置编码字符集
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            String outPutPath = prePath + fileName;// 图片输出路径
            //生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);
            //获取到二维码宽高
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();
            //将二维码放入缓冲流中
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    //循环将二维码内容套入图片
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            File outPutImage = new File(outPutPath);
            //如果图片不存在创建图片
            if(!outPutImage.exists()) {
                outPutImage.createNewFile();
            }
            //将二维码写入图片
            ImageIO.write(image, imageType, outPutImage);
            return preUrl + fileName;
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对二维码解析
     * @param analyzePath    二维码路径
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String zxingCodeAnalyze(String analyzePath) throws Exception{
        MultiFormatReader formatReader = new MultiFormatReader();
        String resultStr = null;
        try {
            File file = new File(analyzePath);
            if (!file.exists())
            {
                return "当前二维码不存在。";
            }
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            Result result = formatReader.decode(binaryBitmap, hints);
            resultStr = result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

}