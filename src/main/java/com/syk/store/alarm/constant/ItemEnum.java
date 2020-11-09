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
    ITEM11("MGLF3CH/A", "iPhone 12 Pro 256GB 银色"), ITEM12("MGLK3CH/A", "iPhone 12 Pro 512GB 银色"),
    ITEM13("MGC53CH/A", "iPhone 12 Pro Max 256GB 银色"), ITEM14("MGC13CH/A", "iPhone 12 Pro Max 128GB 银色"),
    ITEM15("MGCA3CH/A", "iPhone 12 Pro Max 512GB 银色"), ITEM16("MGC03CH/A", "iPhone 12 Pro Max 128GB 石墨色"),
    ITEM17("MGC43CH/A", "iPhone 12 Pro Max 256GB 石墨色"), ITEM18("MGC93CH/A", "iPhone 12 Pro Max 512GB 石墨色"),
    ITEM19("MGC23CH/A", "iPhone 12 Pro Max 128GB 金色"), ITEM20("MGC63CH/A", "iPhone 12 Pro Max 256GB 金色"),
    ITEM21("MGCC3CH/A", "iPhone 12 Pro Max 512GB 金色"), ITEM22("MGC33CH/A", "iPhone 12 Pro Max 128GB 海蓝色"),
    ITEM23("MGC73CH/A", "iPhone 12 Pro Max 256GB 海蓝色"), ITEM24("MGCE3CH/A", "iPhone 12 Pro Max 512GB 海蓝色");

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
