package top.bitplanet.devops.support.core.enums;

/**
 * Created by le on 2016/9/16.
 */
public enum GenderEnum {
    Male(0, "男"),
    Female(1, "女");

    private Integer value;
    private String name;

    GenderEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static GenderEnum getType(Integer value) {

        GenderEnum[] values = values();
        for (GenderEnum item : values) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        throw new UnsupportedOperationException("不支持的类型，value=" + value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
