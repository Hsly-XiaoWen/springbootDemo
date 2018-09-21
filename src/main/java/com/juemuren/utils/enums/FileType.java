package com.juemuren.utils.enums;

/**
 * Created by 肖文 on 2018/7/2
 */
public enum FileType {
    JPEG("FFD8FF"),//.jpg
    PNG("89504E47"),//.png
    GIF("47494638"),//.gif
    TIFF("49492A00"),//.tiff
    RTF("7B5C727466"),//.rtf
    DOC("D0CF11E0"),//.doc
    XLS("D0CF11E0"),//.xls
    MDB("5374616E64617264204A"),//mdb
    BMP("424D"),//.bmp
    DWG("41433130"),//.dwg
    PSD("38425053"),//.psd
    XML("3C3F786D6C"),//.xml
    HTML("68746D6C3E"),//.html
    PDF("255044462D312E"),//.pdf
    ZIP("504B0304"),//.zip
    RAR("52617221"),//.rar
    WAV("57415645"),//.wav
    AVI("41564920");//.avi

    private String value;

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
