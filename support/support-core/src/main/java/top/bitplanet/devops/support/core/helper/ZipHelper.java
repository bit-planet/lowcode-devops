package top.bitplanet.devops.support.core.helper;

import cn.hutool.core.util.ZipUtil;

import java.io.File;


public class ZipHelper {

    public static File zip(String srcPath) {
        File zip = ZipUtil.zip(srcPath);
        return zip;
    }
}
