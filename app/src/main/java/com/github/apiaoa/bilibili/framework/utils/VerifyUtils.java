package com.github.apiaoa.bilibili.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by sunny on 17-2-13.
 */
public class VerifyUtils {

    /**
     * Verify the phone No. by Regular expressions
     * @param phoneNo
     * @return
     */
    public static boolean verifyPhoneNo(final String phoneNo) {
        return isChinaPhoneLegal(phoneNo) || isHKPhoneLegal(phoneNo);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    private static boolean isChinaPhoneLegal(String phoneNo) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    private static boolean isHKPhoneLegal(String phoneNo)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }

    /**
     * Verify the mail address by  Regular expressions
     *
     * @param mailAddress
     * @return
     */
    public static boolean verifyMailAddress(final String mailAddress) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|//.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?//.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(mailAddress);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /**
     * Verify the string is all numeric or not
     *
     * @param string
     * @return
     */
    public static boolean verifyNumeric(final String string) {
        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(string).matches();
    }

    /**
     * Verify the bank card No.
     * @param bankCardNo
     * @return
     */
    public static boolean verifyBankCardNo(final String bankCardNo) {
        char bit = getBankCardCheckCode(bankCardNo.substring(0, bankCardNo.length() - 1));
        return bit != 'N' && bankCardNo.charAt(bankCardNo.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int sum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            sum += k;
        }

        //这边+'0'，不是拼接，在Java和C#中是8+0的ASCII码得到8在ASCII中的编码值
        //然后通过(char)转成字符'8'
        return (sum % 10 == 0) ? '0' : (char) ((10 - sum % 10) + '0');
    }
}