/**
 * @program: suanfa
 * @description: LeetCode
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {


    private static final char[] DIGITS_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String fromHex(String hex) {
        /*兼容带有\x的十六进制串*/
        hex = hex.replace("\\x", "");
        char[] data = hex.toCharArray();
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("字符个数应该为偶数");
        }
        byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f |= toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return new String(out);
    }


    private static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }


    public static String toHex(String str) {
        byte[] data = str.getBytes();
        int outLength = data.length;
        char[] out = new char[outLength << 1];
        for (int i = 0, j = 0; i < outLength; i++) {
            out[j++] = DIGITS_HEX[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_HEX[0x0F & data[i]];
        }
        return new String(out);
    }


    public static void main(String[] args) {
        
    }
}
