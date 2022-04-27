package top.bitplanet.devops.lowcode.dto;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * 文件目录信息
 */
@Data
public class FileDirInfo {

    private String name;

    private File file;

    private int stratum;

    private List<FileDirInfo> children;

}
