package com.ghgande.j2mod.modbus.util;

public class ByteFormatter {

  public static String formatAsHex(int b) {
    StringBuffer sb = new StringBuffer(2);
    leftPadWithZeros(sb, Integer.toHexString(b & 0xFF), 2);
    return sb.toString();
  }

  public static void leftPadWithZeros(StringBuffer sb, String str, int minSize) {
    leftPad(sb, str, minSize, '0');
  }

  private static void leftPad(StringBuffer sb, String str, int minSize, char padChar) {
    int numOfCharsToPad = minSize - str.length();
    for (int i = 0; i < numOfCharsToPad; i++) {
      sb.append(padChar);
    }
    sb.append(str);
  }

}
