package com.code.comm;


import java.io.PrintStream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ByteUtilApp
{
  public static byte[] appendByte(byte[] src, byte[] dst)
  {
    if (src == null) {
      return dst;
    }
    if (dst == null) {
      return src;
    }
    byte[] bTmp = new byte[src.length + dst.length];
    System.arraycopy(src, 0, bTmp, 0, src.length);
    System.arraycopy(dst, 0, bTmp, src.length, dst.length);
    return bTmp;
  }
  
  public static byte[] trim(byte[] buff)
  {
    if ((buff == null) || (buff.length < 1)) {
      return buff;
    }
    int nStartIndex = -1;
    int nEndIndex = -1;
    for (int i = 0; i < buff.length; i++) {
      if ((buff[i] != 32) && (buff[i] != 10) && (buff[i] != 13))
      {
        nStartIndex = i;
        break;
      }
    }
    if (nStartIndex == -1) {
      return new byte[0];
    }
    for (int i = buff.length - 1; i >= 0; i--) {
      if ((buff[i] != 32) && (buff[i] != 13) && (buff[i] != 10))
      {
        nEndIndex = i + 1;
        break;
      }
    }
    byte[] result = new byte[nEndIndex - nStartIndex];
    
    System.arraycopy(buff, nStartIndex, result, 0, result.length);
    
    return result;
  }
  
  public static byte[] byteReplace(byte[] b, byte oldbyte, byte newbyte, char type)
  {
    if (b == null) {
      return b;
    }
    int nLenght = b.length;
    switch (type)
    {
    case '>': 
      for (int i = 0; i < nLenght; i++) {
        if (b[i] > oldbyte) {
          b[i] = newbyte;
        }
      }
      break;
    case '<': 
      for (int i = 0; i < nLenght; i++) {
        if ((b[i] >= 0) && (b[i] < oldbyte)) {
          b[i] = newbyte;
        }
      }
      break;
    case '=': 
      for (int i = 0; i < nLenght; i++) {
        if (b[i] == oldbyte) {
          b[i] = newbyte;
        }
      }
    }
    return b;
  }
  
  public static byte[] getByte(byte[] b, int start)
  {
    if (b == null) {
      return null;
    }
    byte[] b3 = new byte[b.length - start];
    System.arraycopy(b, start, b3, 0, b3.length);
    return b3;
  }
  
  public static byte[] getByte(byte[] b, int start, int len)
  {
    if (b == null) {
      return null;
    }
    int length = Math.min(len, b.length);
    
    byte[] b3 = new byte[length];
    System.arraycopy(b, start, b3, 0, length);
    return b3;
  }
  
  public static byte[] mergeByte(byte[] b, byte[] b2)
  {
    if (b == null) {
      return b2;
    }
    if (b2 == null) {
      return b;
    }
    byte[] b3 = new byte[b.length + b2.length];
    System.arraycopy(b, 0, b3, 0, b.length);
    System.arraycopy(b2, 0, b3, b.length, b2.length);
    return b3;
  }
  
  public static int toInt(byte[] src, int srcPos)
  {
    int dword = 0;
    for (int i = 0; i < 4; i++) {
      dword = (dword << 8) + (src[(i + srcPos)] & 0xFF);
    }
    return dword;
  }
  
  public static long toLong(byte[] src, int srcPos)
  {
    long qword = 0L;
    for (int i = 0; i < 8; i++) {
      qword = (qword << 8) + (src[(i + srcPos)] & 0xFF);
    }
    return qword;
  }
  
  public static void toBytes(int src, byte[] dest, int destPos)
  {
    for (int i = 0; i < 4; i++) {
      dest[(i + destPos)] = ((byte)(src >> (7 - i) * 8));
    }
  }
  
  public static void toBytes(long value, byte[] dest, int destPos)
  {
    for (int i = 0; i < 8; i++) {
      dest[(i + destPos)] = ((byte)(int)(value >> (7 - i) * 8));
    }
  }
  
  public static String toHexaString(byte b)
  {
    StringBuffer result = new StringBuffer(3);
    result.append(Integer.toString((b & 0xF0) >> 4, 16));
    result.append(Integer.toString(b & 0xF, 16));
    return result.toString();
  }
  
  public static String toHexaString(byte[] bytes)
  {
    if (bytes == null) {
      return null;
    }
    StringBuffer result = new StringBuffer();
    byte[] arrayOfByte = bytes;int j = bytes.length;
    for (int i = 0; i < j; i++)
    {
      byte b = arrayOfByte[i];
      
      result.append(Integer.toString((b & 0xF0) >> 4, 16));
      result.append(Integer.toString(b & 0xF, 16));
    }
    return result.toString();
  }
  
  public static byte[] toBytesFromHexaString(String digits)
    throws IllegalArgumentException, NumberFormatException
  {
    if (digits == null) {
      return null;
    }
    int length = digits.length();
    if (length % 2 == 1) {
      throw new IllegalArgumentException("For input string: \"" + digits + "\"");
    }
    length /= 2;
    byte[] bytes = new byte[length];
    for (int i = 0; i < length; i++)
    {
      int index = i * 2;
      bytes[i] = ((byte)Short.parseShort(digits.substring(index, index + 2), 16));
    }
    return bytes;
  }
  
  public static byte[] toBytes(String digits, int radix)
    throws IllegalArgumentException, NumberFormatException
  {
    if (digits == null) {
      return null;
    }
    if ((radix != 16) && (radix != 10) && (radix != 8)) {
      throw new IllegalArgumentException("For input radix: \"" + radix + "\"");
    }
    int divLen = radix == 16 ? 2 : 3;
    int length = digits.length();
    if (length % divLen == 1) {
      throw new IllegalArgumentException("For input string: \"" + digits + "\"");
    }
    length /= divLen;
    byte[] bytes = new byte[length];
    for (int i = 0; i < length; i++)
    {
      int index = i * divLen;
      bytes[i] = ((byte)Short.parseShort(digits.substring(index, index + divLen), radix));
    }
    return bytes;
  }
  public static String encrypePassword(String input){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(input);
		return hashedPassword;
  }
  
}
