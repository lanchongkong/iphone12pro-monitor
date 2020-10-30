package com.syk.store.alarm.constant;

/**
 * @author sunyukun
 * @since 2020/10/30 10:15 上午
 */
public enum ItemEnum {

    ITEM1("MGL93CH/A", "iPhone 12 Pro 128GB 石墨色"), ITEM2("MGLE3CH/A", "iPhone 12 Pro 256GB 石墨色"),
    ITEM3("MGLJ3CH/A", "iPhone 12 Pro 512GB 石墨色"), ITEM4("MGLC3CH/A", "iPhone 12 Pro 128GB 金色"),
    ITEM5("MGLG3CH/A", "iPhone 12 Pro 256GB 金色"), ITEM6("MGLL3CH/A", "iPhone 12 Pro 512GB 金色"),
    ITEM7("MGLD3CH/A", "iPhone 12 Pro 128GB 海蓝色"), ITEM8("MGLH3CH/A", "iPhone 12 Pro 256GB 海蓝色"),
    ITEM9("MGLM3CH/A", "iPhone 12 Pro 512GB 海蓝色"), ITEM10("MGLA3CH/A", "iPhone 12 Pro 128GB 银色"),
    ITEM11("MGLF3CH/A", "iPhone 12 Pro 256GB 银色"), ITEM12("MGLK3CH/A", "iPhone 12 Pro 512GB 银色");

    private final String code;
    private final String name;

    ItemEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
