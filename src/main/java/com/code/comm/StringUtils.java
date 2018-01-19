package com.code.comm;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class StringUtils
{
  public static final String MASK_ZERO_STRING = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
  public static final String MASK_SPACE_STRING = "                                                                                                    ";
  public static final byte[] MASK_ZERO = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".getBytes();
  public static final byte[] MASK_SPACE = "                                                                                                    ".getBytes();
  
  public static int toInt(Object s)
  {
    return toInt(s, 0);
  }
  
  public static int toInt(Object s, int def)
  {
    return WeUtil.toInt(s, def);
  }
  
  public static char toChar(Object o)
  {
    return WeUtil.toChar(o);
  }
  
  public static boolean toBoolean(Object o)
  {
    return WeUtil.toBoolean(o);
  }
  
  public static boolean toBoolean(Object o, boolean def)
  {
    return WeUtil.toBoolean(o, def);
  }
  
  public static final String makeCamel(String src)
  {
    int length = src.length();
    StringBuilder camelCase = new StringBuilder(length);
    StringBuilder tmp = new StringBuilder(length);
    
    boolean isFirst = true;
    for (int idx = 0; idx < length; idx++)
    {
      char ch = src.charAt(idx);
      if (ch == '_')
      {
        isFirst = true;
        camelCase.append(tmp.toString());
        tmp.setLength(0);
      }
      else if ((idx == 0) && (isFirst))
      {
        tmp.append(Character.toUpperCase(ch));
        isFirst = false;
      }
      else if (isFirst)
      {
        tmp.append(Character.toUpperCase(ch));
        isFirst = false;
      }
      else
      {
        tmp.append(Character.toLowerCase(ch));
      }
    }
    camelCase.append(tmp.toString());
    return camelCase.toString();
  }
  
  public static final String makeUnCamel(String src)
  {
    int length = src.length();
    StringBuilder camelCase = new StringBuilder();
    for (int idx = 0; idx < length; idx++)
    {
      char ch = src.charAt(idx);
      if ((idx != 0) && (ch >= 'A') && (ch <= 'Z')) {
        camelCase.append('_');
      }
      camelCase.append(Character.toUpperCase(ch));
    }
    return camelCase.toString();
  }
  
  public static String null2void(String s)
  {
    return null2void(s, true);
  }
  
  public static String null2void(String s, String def)
  {
    return null2void(s, def, true);
  }
  
  public static String null2void(String s, boolean trim)
  {
    return null2void(s, "", trim);
  }
  
  public static String null2void(String s, String def, boolean trim)
  {
    return s == null ? def : trim ? s : isBlank(s) ? def : s;
  }
  
  public static boolean isBlank(Object s)
  {
    return (s == null) || (((s instanceof String)) && (((String)s).trim().length() < 1));
  }
  
  public static boolean isNull(Object s)
  {
    return (WeUtil.isNull(s)) || (((s instanceof String)) && (((String)s).length() < 1));
  }
  
  public static String replace(String src, String org, String tar)
  {
    return replace(src, org, tar, true);
  }
  
  public static String replace(String src, String org, String tar, boolean all)
  {
    if (src == null) {
      return "";
    }
    if ((org == null) || (tar == null) || (src.indexOf(org) == -1)) {
      return src;
    }
    String tmp1 = src;
    StringBuilder sbResult = new StringBuilder();
    if (all)
    {
      int nIndex = 0;
      while ((nIndex = tmp1.indexOf(org)) > -1)
      {
        sbResult.append(tmp1.substring(0, nIndex)).append(tar);
        tmp1 = tmp1.substring(nIndex + org.length());
      }
      sbResult.append(tmp1);
    }
    else
    {
      int nIndex = tmp1.indexOf(org);
      sbResult.append(tmp1.substring(0, nIndex)).append(tar).append(tmp1.substring(nIndex + org.length()));
    }
    return sbResult.toString();
  }
  
  public static char toFullChar(char src)
  {
    if ((src >= '!') && (src <= '~')) {
      src = (char)(src + 65248);
    } else if (src == ' ') {
      src = ' ';
    }
    return src;
  }
  
  public static boolean isFullChar(char c)
  {
    return ((c < '!') || (c > '~')) && (c != ' ');
  }
  
  public static String toFullChar(String src)
  {
    return toFullChar(src, 0);
  }
  
  public static String toFullChar(String src, int size)
  {
    if (src == null) {
      return null;
    }
    StringBuffer strBuf = new StringBuffer();
    char c = '\000';
    int nSize = src.length();
    for (int i = 0; i < nSize; i++)
    {
      c = src.charAt(i);
      if ((c >= '!') && (c <= '~')) {
        c = (char)(c + 65248);
      } else if (c == ' ') {
        c = ' ';
      }
      strBuf.append(c);
    }
    if (size > 0) {
      for (int i = nSize * 2; i < size; i += 2) {
        strBuf.append(' ');
      }
    }
    return strBuf.toString();
  }
  
  public static String toHalfChar(String src)
  {
    if (src == null) {
      return null;
    }
    StringBuffer strBuf = new StringBuffer();
    char c = '\000';
    for (int i = 0; i < src.length(); i++)
    {
      c = src.charAt(i);
      if ((c >= 65281) && (c <= 65374)) {
        c = (char)(c - 65248);
      } else if (c == ' ') {
        c = ' ';
      }
      strBuf.append(c);
    }
    return strBuf.toString();
  }
  
  public static String setMaskString(String v, int length, char padding, char aligntype)
  {
    return new String(setMaskString(byteSubString2(v, length).getBytes(), length, (byte)padding, aligntype));
  }
  
  public static byte[] setMaskString(byte[] input, int length, byte padding, char aligntype)
  {
    byte[] buffInput = input != null ? input : new byte[0];
    byte[] buffPadding = getPaddingValue(padding, length - buffInput.length);
    byte[] buffResult = new byte[length];
    if (buffPadding == null)
    {
      System.arraycopy(buffInput, 0, buffResult, 0, length);
      return buffResult;
    }
    if (aligntype == 'C')
    {
      int nCount = buffPadding.length / 2;
      System.arraycopy(buffPadding, 0, buffResult, 0, nCount);
      System.arraycopy(buffInput, 0, buffResult, nCount, buffInput.length);
      System.arraycopy(buffPadding, 0, buffResult, nCount + buffInput.length, buffPadding.length - nCount);
    }
    else if (aligntype == 'L')
    {
      System.arraycopy(buffInput, 0, buffResult, 0, buffInput.length);
      System.arraycopy(buffPadding, 0, buffResult, buffInput.length, buffPadding.length);
    }
    else
    {
      System.arraycopy(buffPadding, 0, buffResult, 0, buffPadding.length);
      System.arraycopy(buffInput, 0, buffResult, buffPadding.length, buffInput.length);
    }
    return buffResult;
  }
  
  public static byte[] getPaddingValue(byte padding, int length)
  {
    if (length < 1) {
      return null;
    }
    byte[] buffResult = new byte[length];
    byte[] buffMask = null;
    if (padding == 48) {
      buffMask = MASK_ZERO;
    } else if (padding == 32) {
      buffMask = MASK_SPACE;
    }
    if (buffMask != null)
    {
      int nPos = 0;int nCount = 0;
      while (nPos < length)
      {
        nCount = Math.min(length - nPos, buffMask.length);
        System.arraycopy(buffMask, 0, buffResult, nPos, nCount);
        nPos += nCount;
      }
    }
    else
    {
      for (int i = 0; i < length; i++) {
        buffResult[i] = padding;
      }
    }
    return buffResult;
  }
  
  public static String setMessageDump(String title, byte[] data, Date logTime)
  {
    return setMessageDump(title, data, logTime, true);
  }
  
  public static String setMessageDump(String title, byte[] data, Date logTime, boolean footer)
  {
    StringBuffer sbLog = new StringBuffer();

    try
    {
      int nCount = 0;
      int nDumpPoint = 0;
      byte[] byteValue = data;
      
      sbLog.append("-------------------------------------------------------------------------------\n");
      if (title != null) {
        sbLog.append(setMask(title, 79, ' ', 'C').substring(0, 60) + "Length = " + setMask(String.valueOf(byteValue.length), 10, '0', 'R') + "\n");
      }
      sbLog.append("-------------------------------------------------------------------------------\n");
      
      byte[] byteTmp = new byte[16];
      for (int i = 0; i < byteValue.length; i++)
      {
        if (nCount == 16)
        {
          System.arraycopy(byteValue, nDumpPoint, byteTmp, 0, 16);
          sbLog.append("|" + new String(ByteUtilApp.byteReplace(byteTmp, (byte)32, (byte)46, '<')) + "\n");
          nDumpPoint += 16;
          nCount = 0;
        }
        if (nCount == 0)
        {
          sbLog.append(setMask(String.valueOf(i), 5, '0', 'R') + "(" + setMask(Integer.toHexString(i), 4, '0', 'R') + ") ");
          nCount = 0;
        }
        String strHexString = Integer.toHexString(byteValue[i] & 0xFF);
        
        sbLog.append(setMask(strHexString, 2, '0', 'R') + " ");
        if ((i + 1) % 8 == 0) {
          sbLog.append(" ");
        }
        nCount++;
      }
      int length = 16 - nCount;
      length *= 3;
      if (length > 8) {
        length += 3;
      } else {
        length += 2;
      }
      if (nCount == 16) {
        length--;
      }
      nCount = byteValue.length - nDumpPoint;
      System.arraycopy(byteValue, nDumpPoint, byteTmp, 0, nCount);
      
      sbLog.append(setMask("|", length, ' ', 'R') + new String(ByteUtilApp.byteReplace(byteTmp, (byte)32, (byte)46, '<'), 0, nCount) + "\n");
      sbLog.append("-------------------------------------------------------------------------------\n");
      if (footer)
      {
       
        sbLog.append("[" + new String(data) + "]" + "\n");
        sbLog.append("-------------------------------------------------------------------------------\n\n");
      }
    }
    catch (Exception e)
    {
      sbLog.append("\n-------------------------------------------------------------------------------\n");
      sbLog.append("Exception :" + toString(e) + "\n");
      sbLog.append("\n-------------------------------------------------------------------------------\n");
    }
    return sbLog.toString();
  }
  
  public static String setMask(String v, int length, char padding, char aligntype)
  {
    String value = v == null ? "null" : v;
    if (value.getBytes().length < length)
    {
      StringBuffer sbValue = new StringBuffer(length);
      int nValueLength = value.getBytes().length;
      int nCenter = length / 2;
      int nCount = 0;
      int nMaxCount = 0;
      if (aligntype == 'L')
      {
        sbValue.append(value);
        nCount = nValueLength;
      }
      else if (aligntype == 'R')
      {
        for (nMaxCount = length - nValueLength; nCount < nMaxCount; nCount++) {
          sbValue.append(padding);
        }
        sbValue.append(value);
        nCount += nValueLength;
      }
      else
      {
        for (nMaxCount = nCenter - nValueLength / 2; nCount < nMaxCount; nCount++) {
          sbValue.append(padding);
        }
        sbValue.append(value);
        nCount += nValueLength;
      }
      for (; nCount < length; nCount++) {
        sbValue.append(padding);
      }
      return sbValue.toString();
    }
    return value;
  }
  
  public static String toCRLF(String src)
  {
    if (src == null) {
      return src;
    }
    char[] chars = src.toCharArray();
    
    StringBuilder buff = new StringBuilder();
    boolean bCR = false;
    boolean bLF = false;
    for (int i = 0; i < chars.length; i++)
    {
      char c = chars[i];
      if (c == '\n')
      {
        if (bCR)
        {
          buff.append("\n\r");
          bLF = false;
        }
        else if (bLF)
        {
          buff.append("\n\r");
          bCR = false;
          bLF = false;
        }
        else
        {
          bCR = true;
        }
      }
      else if (c == '\r')
      {
        bLF = true;
        if (bCR)
        {
          buff.append("\n\r");
          bCR = false;
          bLF = false;
        }
      }
      else
      {
        if ((bCR) || (bLF)) {
          buff.append("\n\r");
        }
        buff.append(c);
        bCR = false;
        bLF = false;
      }
    }
    if ((bCR) || (bLF)) {
      buff.append("\n\r");
    }
    return buff.toString();
  }
  
  public static void sort(List<String> list)
  {
    if (list == null) {
      return;
    }
    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
  }
  
  public static Iterator<String> sort(Iterator<String> ite)
  {
    if (ite == null) {
      return null;
    }
    List<String> list = new LinkedList();
    while (ite.hasNext()) {
      list.add((String)ite.next());
    }
    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
    return list.iterator();
  }
  
  public static String[] sort(String[] data)
  {
    if (data == null) {
      return null;
    }
    List<String> list = new LinkedList();
    String[] arrayOfString = data;int j = data.length;
    for (int i = 0; i < j; i++)
    {
      String s = arrayOfString[i];
      if (s != null) {
        list.add(s);
      }
    }
    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
    return (String[])list.toArray(new String[list.size()]);
  }
  
  public static Enumeration<String> sort(Enumeration<String> ite)
  {
    if (ite == null) {
      return null;
    }
    Vector<String> list = new Vector();
    while (ite.hasMoreElements()) {
      list.add((String)ite.nextElement());
    }
    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
    return list.elements();
  }
  
  public static String toString(Throwable t)
  {
    return toString(t, false);
  }
  
  public static String toString(Throwable t, boolean fullTrace)
  {
    if (t == null) {
      return null;
    }
    PrintWriter pw = null;
    StringWriter out = null;
    try
    {
      out = new StringWriter();
      pw = new PrintWriter(out);
     
      return out.toString();
    }
    catch (Exception localException2) {}finally
    {
      if (out != null) {
        try
        {
          out.close();
        }
        catch (Exception localException5) {}
      }
      if (pw != null) {
        try
        {
          pw.close();
        }
        catch (Exception localException6) {}
      }
    }
    return null;
  }
  
  
 
  private static void printStackTraceElement(PrintWriter out, StackTraceElement[] elements)
  {
    if (elements != null) {
      if (elements.length > 300)
      {
        for (int i = 0; i < 100; i++) {
          out.println("\tat " + elements[i]);
        }
        out.println("\t... " + (elements.length - 200) + " more ");
        for (int i = elements.length - 100; i < elements.length; i++) {
          out.println("\tat " + elements[i]);
        }
      }
      else
      {
        StackTraceElement[] arrayOfStackTraceElement;
        int j = (arrayOfStackTraceElement = elements).length;
        for (int i = 0; i < j; i++)
        {
          StackTraceElement trace = arrayOfStackTraceElement[i];
          
          out.println("\tat " + trace);
        }
      }
    }
  }
  
  public static Object jsonNull2Def(Object obj, Object def)
  {
    if (obj == null) {
      return def;
    }
    return obj;
  }
  
  public static String byteSubString2(String s, int end_index)
  {
    if (s == null) {
      return "";
    }
    char[] b = s.toCharArray();
    
    StringBuilder sb = new StringBuilder();
    int nMax = b.length;
    
    int i = 0;
    for (int index = 1; (index <= end_index) && (nMax > i); index++)
    {
      char c = b[i];
      if (isFullChar(c)) {
        index++;
      }
      sb.append(c);i++;
    }
    return sb.toString();
  }
  
  public static String byteSubString(String s, int start_index, int end_index)
  {
    byte[] b = s.getBytes();
    int nPreCnt = 0;
    int nPostCnt = 0;
    if (start_index < 0) {
      return null;
    }
    if (end_index < start_index) {
      return null;
    }
    if (end_index == start_index) {
      return null;
    }
    end_index = Math.min(b.length, end_index);
    for (int i = 0; i < start_index; i++) {
      if ((b[i] & 0x80) != 0) {
        nPreCnt++;
      }
    }
    if (nPreCnt % 2 != 0) {
      start_index++;
    }
    for (int i = start_index; i < end_index; i++) {
      if ((b[i] & 0x80) != 0) {
        nPostCnt++;
      }
    }
    if (nPostCnt % 2 != 0) {
      end_index--;
    }
    System.out.println(new String(b, start_index, end_index - start_index));
    return new String(b, start_index, end_index - start_index);
  }
  
  public static String trim(String s)
  {
    if (s == null) {
      return "";
    }
    int st = 0;
    char[] c = s.toCharArray();
    int count = c.length;
    int len = count;
    do
    {
      st++;
      if (st >= len) {
        break;
      }
    } while ((c[st] <= ' ') || (c[st] == ' '));
    while ((st < len) && ((c[(len - 1)] <= ' ') || (c[(len - 1)] == ' '))) {
      len--;
    }
    return (st > 0) || (len < count) ? s.substring(st, len) : s;
  }
  
  public static String getString(String src, String from, String to)
  {
    return getString(src, 0, from, to);
  }
  
  public static String getString(String src, int startIndex, String from, String to)
  {
    if ((src == null) || (src.length() < startIndex)) {
      return null;
    }
    if (!isNull(from))
    {
      int nStart = src.indexOf(from, startIndex);
      int nEnd = -1;
      if (nStart == -1) {
        return null;
      }
      nStart += from.length();
      if (!isNull(to))
      {
        nEnd = src.indexOf(to, nStart);
        if (nEnd == -1) {
          return null;
        }
      }
      if (nEnd != -1) {
        return src.substring(nStart, nEnd);
      }
      return src.substring(nStart);
    }
    if (!isNull(to))
    {
      int nEnd = src.indexOf(to, startIndex);
      if (nEnd == -1) {
        return null;
      }
      return src.substring(0, nEnd);
    }
    return null;
  }
  
  public static String toScriptValue(String s)
  {
    if (s == null) {
      return "";
    }
    return replace(s, "\"", "\\\"");
  }
}
