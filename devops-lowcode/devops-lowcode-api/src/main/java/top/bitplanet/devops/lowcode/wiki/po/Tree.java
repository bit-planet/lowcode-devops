package top.bitplanet.devops.lowcode.wiki.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Tree implements Serializable {

    private Long id;

    private String label;

    private List<Tree> children;


}
