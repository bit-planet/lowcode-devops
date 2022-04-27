package top.bitplanet.devops.support.core.helper;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;

import java.io.File;
import java.util.*;

/**
 * **************************************************************
 * @描述        : 文件处理帮助类
 * @作者        : 乐胜
 * @版本        : v1.0
 * @日期        : 2021/11/22
 */
public class FileHelper {

    /**
     * 创建多级目录
     * @param dirPath 路径
     */
    public static File mkdirs(String dirPath) {
        if (StringHelper.isEmpty(dirPath)) {
            return null;
        }
        File dir = new File(dirPath);
        return mkdirs(dir);
    }

    /**
     * 创建多级目录
     * @param dir 文件对象
     */
    public static File mkdirs(File dir) {
        if (dir == null) {
            return null;
        }
        if(!dir.exists()) {
            /*
             * mkdirs方法在创建当前目录时，会自动创建所有不存在的父目录
             */
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 列出文件目录
     * @param rootFile 根文件夹
     * @param dirs 目录
     * @param stratum 层级，默认：0
     * @param maxStratum 最大层级，遍历深度
     */
    public static void listDirs(File rootFile,List<Map<String,List>> dirs,int stratum,int maxStratum) {
        if (rootFile.isDirectory()) {
            Map<String,List> map = new HashMap();
            List<Map<String,List>> objects = new ArrayList<>();
            map.put(rootFile.getName(),objects);
            // 添加到parent
            dirs.add(map);
            File[] files = rootFile.listFiles();
            if (files == null || files.length == 0 || stratum >= maxStratum) {
                return;
            }
            stratum++;
            int finalStratum = stratum;
            Arrays.stream(files).filter(f -> {
                if (!f.isDirectory()) {
                    return false;
                }
                return true;
            }).forEach(f -> {
                listDirs(f,objects, finalStratum,maxStratum);
            });
        }
    }

    /**
     * 复制文件或目录<br>
     * 如果目标文件为目录，则将源文件以相同文件名拷贝到目标目录
     *
     * @param src    源文件或目录
     * @param dest   目标文件或目录，目标不存在会自动创建（目录、文件都创建）
     * @param isOverride 是否覆盖目标文件
     * @return 目标目录或文件
     * @throws IORuntimeException IO异常
     */
    public static String copyFileTo(String src,String dest,boolean isOverride) {
        FileUtil.copy(src,dest,isOverride);
        return dest;
    }

    /**
     * 复制文件或目录<br>
     * 情况如下：
     *
     * <pre>
     * 1、src和dest都为目录，则讲src下所有文件（包括子目录）拷贝到dest下
     * 2、src和dest都为文件，直接复制，名字为dest
     * 3、src为文件，dest为目录，将src拷贝到dest目录下
     * </pre>
     *
     * @param src        源文件
     * @param dest       目标文件或目录，目标不存在会自动创建（目录、文件都创建）
     * @param isOverride 是否覆盖目标文件
     * @return 目标目录或文件
     * @throws IORuntimeException IO异常
     */
    public static String copyContent(String src,String dest,boolean isOverride) {
        FileUtil.copyContent(new File(src),new File(dest),isOverride);
        return dest;
    }

    /**
     * 复制文件
     * @param src
     * @return
     */
    public static String copyFile(String src) {
        String dest = src + "-" + System.nanoTime();
        copyContent(src,dest,false);
        return dest;
    }





}
