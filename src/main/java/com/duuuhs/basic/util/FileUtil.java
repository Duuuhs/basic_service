package com.duuuhs.basic.util;

import com.duuuhs.basic.model.FileType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: DMY
 * @Date: 2019/11/6 22:06
 * @Description: 文件的魔数工具类
 */
public class FileUtil {
    /**
     * 获取文件投
     *
     * @param filePath 文件路径
     * @return 16 进制的文件投信息
     * @throws IOException
     */
    private static String getFileHeader(String filePath) throws IOException {
        byte[] b = new byte[28];
        InputStream inputStream = new FileInputStream(filePath);
        inputStream.read(b, 0, 28);
        inputStream.close();
        return bytes2hex(b);
    }

    /**
     * 将字节数组转换成16进制字符串
     */
    private static String bytes2hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据文件路径获取文件类型
     *
     * @param filePath 文件路径
     * @return 文件类型
     * @throws IOException
     */
    public static FileType getFileType(String filePath) throws IOException {
        String fileHead = getFileHeader(filePath);
        if (null == fileHead || fileHead.length() == 0) {
            return null;
        }
        fileHead = fileHead.toUpperCase();
        FileType[] fileTypes = FileType.values();
        for (FileType type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }
        return null;
    }

    private FileUtil(){
    }

//    public static void main(String[] args) throws IOException {
//        String file = "C:/Users/Duuuhs/Desktop/image/mypay.jpg";
//        String file = "C:/Users/Duuuhs/Desktop/image/mypay.pdf";
//        FileType fileType = getFileType(file);
//        System.out.println(fileType.getKey());
//    }
}
