package com.yw.sports.enums;

/**
 * @author liangwei
 * 表示用户发表的作品的状态
 */

public enum TrendEnum implements BaseEnum<TrendEnum, Integer>{
    /**
     * 未知的类型
     */
    UN_KNOW(-1, "未知类型"),

    FAT(0,"导致肥胖"),

    NORMAL(1, "正常水平"),

    SLIM(2,"减肥"),

    OVER(3,"运动过度");



    private final Integer value;
    private final String message;


    private TrendEnum(Integer value, String message){
        this.value = value;
        this.message = message;
    }

    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public static TrendEnum valueOf(Integer value){
        for(TrendEnum typeEnum: TrendEnum.values()){

            if(typeEnum.getValue().equals(value)){
                return typeEnum;
            }
        }
        return UN_KNOW;
    }

    public static String getName(Integer value){
        for(TrendEnum typeEnum: TrendEnum.values()){
            if(typeEnum.getValue().equals(value)){
                return typeEnum.getMessage();
            }
        }
        return UN_KNOW.getMessage();
    }

}
