package com.juemuren.utils.enums;

/**
 * Created by 肖文 on 2018/9/21
 */
public enum CipherDicTestEnum {
    DIC_a('a', 'h'),
    DIC_b('b', 'e'),
    DIC_c('c', 'q'),
    DIC_d('d', 'k'),
    DIC_e('e', 'p'),
    DIC_f('f', 'v'),
    DIC_g('g', 'c'),
    DIC_h('h', 'g'),
    DIC_i('i', 'j'),
    DIC_j('j', 'f'),
    DIC_k('k', 't'),
    DIC_l('l', 's'),
    DIC_m('m', 'a'),
    DIC_n('n', 'z'),
    DIC_o('o', 'w'),
    DIC_p('p', 'r'),
    DIC_q('q', 'n'),
    DIC_r('r', 'b'),
    DIC_s('s', 'o'),
    DIC_t('t', 'i'),
    DIC_u('u', 'y'),
    DIC_v('v', 'l'),
    DIC_w('w', 'x'),
    DIC_x('x', 'd'),
    DIC_y('y', 'u'),
    DIC_z('z', 'm'),
    DIC_1('1', '8'),
    DIC_2('2', '9'),
    DIC_3('3', '0'),
    DIC_4('4', '1'),
    DIC_5('5', '2'),
    DIC_6('6', '3'),
    DIC_7('7', '4'),
    DIC_8('8', '5'),
    DIC_9('9', '6'),
    DIC_0('0', '7');

    CipherDicTestEnum(char oldChar, char newChar) {
        this.newChar = newChar;
        this.oldChar = oldChar;
    }
    private char oldChar;
    private char newChar;

    public char getOldChar() {
        return oldChar;
    }

    public void setOldChar(char oldChar) {
        this.oldChar = oldChar;
    }

    public char getNewChar() {
        return newChar;
    }

    public void setNewChar(char newChar) {
        this.newChar = newChar;
    }

    public static char getDic(char i, int type) {
        CipherDicTestEnum[] enums = CipherDicTestEnum.values();
        for (CipherDicTestEnum cipherDicEnum : enums) {
            if (type == 0) {
                if (cipherDicEnum.getNewChar() == i) {
                    return cipherDicEnum.getOldChar();
                }
            } else {
                if (cipherDicEnum.getOldChar() == i) {
                    return cipherDicEnum.getNewChar();
                }
            }
        }
        return i;
    }

    /**
     *
     * @param data 需要替换的文本
     * @param type 替换的类型 0解密的时候讲新的char替换成原本的char 1 加密的时候讲原本的char 替换成新的char
     * @return
     */
    public static String replaceDic(String data, int type) {
        String dicArray = "abcdefghijklmnopqrstuvwxyz1234567890";

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (dicArray.indexOf(c) > -1) {
                stringBuilder.append(getDic(data.charAt(i), type));
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}