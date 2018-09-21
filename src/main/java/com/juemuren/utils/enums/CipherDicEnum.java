package com.juemuren.utils.enums;

/**
 * Created by 肖文 on 2018/9/21
 */
public enum CipherDicEnum  {
    DIC_a('a', 'g'),
    DIC_b('b', 'd'),
    DIC_c('c', 't'),
    DIC_d('d', 'n'),
    DIC_e('e', 'u'),
    DIC_f('f', 'a'),
    DIC_g('g', 'y'),
    DIC_h('h', 'q'),
    DIC_i('i', 'w'),
    DIC_j('j', 'v'),
    DIC_k('k', 'z'),
    DIC_l('l', 'x'),
    DIC_m('m', 'o'),
    DIC_n('n', 'e'),
    DIC_o('o', 's'),
    DIC_p('p', 'r'),
    DIC_q('q', 'm'),
    DIC_r('r', 'j'),
    DIC_s('s', 'c'),
    DIC_t('t', 'p'),
    DIC_u('u', 'k'),
    DIC_v('v', 'f'),
    DIC_w('w', 'h'),
    DIC_x('x', 'l'),
    DIC_y('y', 'i'),
    DIC_z('z', 'b'),
    DIC_1('1', '0'),
    DIC_2('2', '1'),
    DIC_3('3', '2'),
    DIC_4('4', '3'),
    DIC_5('5', '4'),
    DIC_6('6', '5'),
    DIC_7('7', '6'),
    DIC_8('8', '7'),
    DIC_9('9', '8'),
    DIC_0('0', '9');

    CipherDicEnum(char oldChar, char newChar) {
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
        CipherDicEnum [] enums = CipherDicEnum.values();
        for (CipherDicEnum cipherDicEnum : enums) {
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

