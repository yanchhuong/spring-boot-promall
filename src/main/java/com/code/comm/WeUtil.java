package com.code.comm;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.StringTokenizer;

public class WeUtil
{
  private static int VALUE_NIO_READ_COUNT = 1000;
  public static final String VALUE_TIMEOUT_CODE = "JEXS3000008";
  public static final String[] STRING_ARRAY_ZERO = new String[0];
  
 
  
  public static boolean isNull(Object s)
  {
    return s == null;
  }
  
  public static int toInt(Object s)
  {
    return toInt(s, 0);
  }
  
  public static int toInt(Object s, int def)
  {
    if (s == null) {
      return def;
    }
    if ((s instanceof Number)) {
      return ((Number)s).intValue();
    }
    String num = s.toString();
    try
    {
      return Integer.parseInt(num);
    }
    catch (Exception localException)
    {
      if (num.startsWith("0x")) {
        try
        {
          return Integer.decode(num).intValue();
        }
        catch (Exception localException1) {}
      }
    }
    return def;
  }
  
  public static long toLong(Object o)
  {
    return toLong(o, 0L);
  }
  
  public static long toLong(Object o, long def)
  {
    if (o == null) {
      return def;
    }
    if ((o instanceof Number)) {
      return ((Number)o).longValue();
    }
    String num = o.toString();
    try
    {
      return Long.parseLong(num);
    }
    catch (Exception localException)
    {
      if (num.startsWith("0x")) {
        try
        {
          return Long.decode(num).longValue();
        }
        catch (Exception localException1) {}
      }
    }
    return def;
  }
  
  public static double toDouble(Object o)
  {
    return toDouble(o, 0.0D);
  }
  
  public static double toDouble(Object o, double def)
  {
    if (o == null) {
      return def;
    }
    if ((o instanceof Number)) {
      return ((Number)o).doubleValue();
    }
    String num = o.toString();
    try
    {
      return Double.parseDouble(num);
    }
    catch (Exception localException)
    {
      if (num.startsWith("0x")) {
        try
        {
          return Double.valueOf(o.toString()).doubleValue();
        }
        catch (Exception localException1) {}
      }
    }
    return def;
  }
  
  public static float toFloat(Object o)
  {
    return toFloat(o, 0.0F);
  }
  
  public static float toFloat(Object o, float def)
  {
    if (o == null) {
      return def;
    }
    if ((o instanceof Number)) {
      return ((Number)o).floatValue();
    }
    String num = o.toString();
    try
    {
      return Float.parseFloat(num);
    }
    catch (Exception localException)
    {
      if (num.startsWith("0x")) {
        try
        {
          return Float.valueOf(o.toString()).floatValue();
        }
        catch (Exception localException1) {}
      }
    }
    return def;
  }
  
  public static char toChar(Object o)
  {
    if (o == null) {
      return '\000';
    }
    if ((o instanceof Character)) {
      return ((Character)o).charValue();
    }
    String s = o.toString();
    if (s.startsWith("0x")) {
      try
      {
        return (char)Integer.decode(s).intValue();
      }
      catch (Exception localException) {}
    }
    return StringUtils.isBlank(s) ? '\000' : s.charAt(0);
  }
  
  public static boolean toBoolean(Object o)
  {
    return toBoolean(o, false);
  }
  
  public static boolean toBoolean(Object o, boolean def)
  {
    if (StringUtils.isBlank(o)) {
      return def;
    }
    if ((o instanceof Boolean)) {
      return ((Boolean)o).booleanValue();
    }
    String s = o.toString().toLowerCase();
    if (s.length() == 1)
    {
      char c = s.charAt(0);
      if ((c == 'n') || (c == '0')) {
        return false;
      }
      if ((c == 'y') || (c == '1')) {
        return true;
      }
    }
    else
    {
      if ("yes".equals(s)) {
        return true;
      }
      if ("no".equals(s)) {
        return false;
      }
      try
      {
        return s.equalsIgnoreCase("false") ? false : s.equalsIgnoreCase("true") ? true : def;
      }
      catch (Throwable t)
      {
        try
        {
          return Integer.parseInt(s) != 0;
        }
        catch (NumberFormatException localNumberFormatException) {}
      }
    }
    return def;
  }
  
  public static boolean isArray(Class<?> clazz)
  {
    return (clazz != null) && ((clazz.isArray()) || (Collection.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isArray(Object obj)
  {
    return (obj != null) && (isArray(obj.getClass()));
  }
  
  public static boolean isBoolean(Class<?> clazz)
  {
    return (clazz != null) && ((Boolean.TYPE.isAssignableFrom(clazz)) || (Boolean.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isBoolean(Object obj)
  {
    return ((obj instanceof Boolean)) || ((obj != null) && (isBoolean(obj.getClass())));
  }
  
  public static boolean isDouble(Class<?> clazz)
  {
    return (clazz != null) && ((Double.TYPE.isAssignableFrom(clazz)) || (Double.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isDouble(Object obj)
  {
    return ((obj instanceof Double)) || ((obj != null) && (isDouble(obj.getClass())));
  }
  
  public static boolean isLong(Class<?> clazz)
  {
    return (clazz != null) && ((Long.TYPE.isAssignableFrom(clazz)) || (Long.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isLong(Object obj)
  {
    return ((obj instanceof Long)) || ((obj != null) && (isLong(obj.getClass())));
  }
  
  public static boolean isFloat(Class<?> clazz)
  {
    return (clazz != null) && ((Float.TYPE.isAssignableFrom(clazz)) || (Float.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isFloat(Object obj)
  {
    return ((obj instanceof Float)) || ((obj != null) && (isFloat(obj.getClass())));
  }
  
  public static boolean isInteger(Class<?> clazz)
  {
    return (clazz != null) && ((Integer.TYPE.isAssignableFrom(clazz)) || (Integer.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isInteger(Object obj)
  {
    return ((obj instanceof Integer)) || ((obj != null) && (isInteger(obj.getClass())));
  }
  
  public static boolean isShort(Class<?> clazz)
  {
    return (clazz != null) && ((Short.TYPE.isAssignableFrom(clazz)) || (Short.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isShort(Object obj)
  {
    return ((obj instanceof Short)) || ((obj != null) && (isShort(obj.getClass())));
  }
  
  public static boolean isByte(Class<?> clazz)
  {
    return (clazz != null) && ((Byte.TYPE.isAssignableFrom(clazz)) || (Byte.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isByte(Object obj)
  {
    return ((obj instanceof Byte)) || ((obj != null) && (isByte(obj.getClass())));
  }
  
  public static boolean isNumber(Class<?> clazz)
  {
    return (clazz != null) && (
      (Byte.TYPE.isAssignableFrom(clazz)) || (Short.TYPE.isAssignableFrom(clazz)) || 
      (Integer.TYPE.isAssignableFrom(clazz)) || (Long.TYPE.isAssignableFrom(clazz)) || 
      (Float.TYPE.isAssignableFrom(clazz)) || (Double.TYPE.isAssignableFrom(clazz)) || 
      (Number.class.isAssignableFrom(clazz)));
  }
  
  public static boolean isNumber(Object obj)
  {
    if (obj != null)
    {
      if (((obj instanceof Number)) || 
        (obj.getClass() == Byte.TYPE) || 
        (obj.getClass() == Byte.TYPE) || 
        (obj.getClass() == Short.TYPE) || 
        (obj.getClass() == Integer.TYPE) || 
        (obj.getClass() == Long.TYPE) || 
        (obj.getClass() == Float.TYPE) || 
        (obj.getClass() == Double.TYPE)) {
        return true;
      }
      if ((obj instanceof String)) {
        try
        {
          Double.parseDouble(obj.toString());
          return true;
        }
        catch (Throwable localThrowable) {}
      }
    }
    return false;
  }
  
  public static List<Object> changeList(Object[] items)
  {
    List<Object> result = new ArrayList();
    copyList(result, items);
    return result;
  }
  
  public static void copyList(List list, Object[] items)
  {
    Object[] arrayOfObject;
    int j = (arrayOfObject = items).length;
    for (int i = 0; i < j; i++)
    {
      Object item = arrayOfObject[i];
      if (item != null) {
        list.add(item);
      }
    }
  }
  
  public static byte[] readFile(File f)
    throws IOException
  {
    InputStream in = null;
    try
    {
      in = new FileInputStream(f);
      return loadStream(in, -1);
    }
    finally
    {
      close(in);
    }
  }
  
  public static byte[] readFile(String f)
    throws IOException
  {
    InputStream in = null;
    try
    {
      in = new FileInputStream(f);
      return loadStream(in, -1);
    }
    finally
    {
      close(in);
    }
  }
  
  
  public static byte[] loadByte(InputStream in, int size)
    throws IOException
  {
    return loadStream(in, size);
  }
  
  public static byte[] loadByte(ByteChannel channel, int size)
    throws IOException
  {
    return loadChannel(channel, size);
  }
  
  public static byte[] loadStream(InputStream in, int size)
    throws IOException
  {
    if (size == 0) {
      return new byte[0];
    }
    byte[] buff = null;
    int nDataLength = size;
    int nTmp;
    ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(size, 256));
    if (nDataLength > 0)
    {
    	
      int nReadSize = Math.min(1024, nDataLength);
      buff = new byte[nReadSize];
      while (nDataLength > 0)
      {
        nReadSize = Math.min(1024, nDataLength);
        try
        {
          nTmp = in.read(buff, 0, nReadSize);
        }
        catch (SocketException se)
        {
         
           nTmp = -1;
          se.printStackTrace();
        }
        catch (IOException ie)
        { throw ie;
        }
      
        if (nTmp == -1)
        {
          String strMsg = "[]";
          if (out.size() > 0) {
            try
            {
              strMsg = "[" + new String(out.toByteArray()) + "]";
            }
            catch (Throwable t)
            {
              t.printStackTrace();
              strMsg = "E[]";
            }
          }
          throw new IOException("InputStream close -1 (user size:" + size + ", read size:" + out.size() + ")" + strMsg);
        }
        out.write(buff, 0, nTmp);
        nDataLength -= nTmp;
      }
    }
    else
    {
      buff = new byte['?'];
      int nReadLength = 0;
      while ((nReadLength = in.read(buff)) != -1) {
        out.write(buff, 0, nReadLength);
      }
    }
    return out.toByteArray();
  }
  
  public static void skipChannel(ByteChannel channel, int size)
    throws IOException
  {
    if (size == 0) {
      return;
    }
    if (size > 0)
    {
      ByteBuffer buff = ByteBuffer.allocate(1);
      int nSize = 0;
      for (int i = 0; i < size; i++)
      {
        nSize = channel.read(buff);
        if (nSize == -1) {
          throw new IOException("channel close -1");
        }
        buff.clear();
      }
      buff = null;
    }
  }
  
  public static void skipStream(InputStream in, int size)
    throws IOException
  {
    if (size == 0) {
      return;
    }
    if (size > 0)
    {
      int nSize = 0;
      for (int i = 0; i < size; i++)
      {
        nSize = in.read();
        if (nSize == -1) {
          throw new IOException("channel close -1");
        }
      }
    }
  }
  
  public static byte[] loadChannel(ByteChannel channel, int size)
    throws IOException
  {
    if (size == 0) {
      return new byte[0];
    }
    byte[] resultBuff = null;
    if (size > 0)
    {
      ByteBuffer buff = ByteBuffer.allocate(size);
      
      int nSize = 0;
      int nErrorCount = 0;
      while (buff.remaining() > 0)
      {
        nSize = channel.read(buff);
        if (nSize == 0)
        {
          nErrorCount++;
          if (nErrorCount > VALUE_NIO_READ_COUNT)
          {
            String strMsg = "[]";
            if (buff.position() > 0) {
              try
              {
                strMsg = "[" + new String(buff.array()) + "]";
              }
              catch (Throwable t)
              {
                t.printStackTrace();
                strMsg = "E[]";
              }
            }
            throw new IOException("channel read error init size (" + size + ") read size(" + buff.position() + ")" + strMsg);
          }
          synchronized (channel)
          {
            try
            {
              channel.wait(10L);
            }
            catch (Throwable localThrowable1) {}
          }
        }
        if ((nSize == -1) || (!channel.isOpen())) {
          throw new IOException("channel close -1");
        }
        nErrorCount = 0;
      }
      resultBuff = buff.array();
      buff.clear();
      buff = null;
    }
    else
    {
      ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(size, 256));
      ByteBuffer buff = ByteBuffer.allocate(1024);
      int nErrorCount = 0;
      int nSize = 0;
      while ((nSize = channel.read(buff)) != -1)
      {
        out.write(buff.array());
        buff.clear();
        if (nSize == 0)
        {
          nErrorCount++;
          if (nErrorCount > VALUE_NIO_READ_COUNT)
          {
            String strMsg = "[]";
            if (buff.position() > 0) {
              try
              {
                strMsg = "[" + new String(buff.array()) + "]";
              }
              catch (Throwable t)
              {
                t.printStackTrace();
                strMsg = "E[]";
              }
            }
            throw new IOException("channel read error init size (" + size + ") read size(" + buff.position() + ")" + strMsg);
          }
          synchronized (channel)
          {
            try
            {
              channel.wait(10L);
            }
            catch (Throwable localThrowable2) {}
          }
        }
        if (!channel.isOpen()) {
          break;
        }
        nErrorCount = 0;
      }
      out.write(buff.array());
      buff.clear();
      buff = null;
      resultBuff = out.toByteArray();
    }
    return resultBuff;
  }
  
  public static void writeStream(OutputStream out, byte[] buff)
    throws IOException
  {
    writeStream(out, buff, 0, buff.length);
  }
  
  public static void writeStream(OutputStream out, byte[] buff, int off, int len)
    throws IOException
  {
    int nSize = len > 0 ? len : buff.length - off;
    out.write(buff, off, nSize);
    out.flush();
  }
  
  public static void writeChannel(ByteChannel channel, byte[] buff)
    throws IOException
  {
    writeChannel(channel, buff, 0, buff.length);
  }
  
  public static void writeChannel(ByteChannel channel, byte[] buff, int off, int len)
    throws IOException
  {
    int nIndex = Math.max(off, 0);
    int nSize = len > 0 ? len : buff.length - nIndex;
    byte[] buffTmp = null;
    ByteBuffer buff2 = null;
    if (buff.length == nSize)
    {
      buffTmp = buff;
    }
    else
    {
      buffTmp = new byte[nSize];
      System.arraycopy(buff, off, buffTmp, 0, buffTmp.length);
    }
    buff2 = ByteBuffer.wrap(buffTmp);
    
    buff2.clear();
    while (buff2.remaining() > 0) {
      channel.write(buff2);
    }
  }
  
  public static void copyChannel(ByteChannel channel, InputStream in, int size)
    throws IOException
  {
    byte[] buff = new byte['?'];
    int nDataLength = size;
    if (nDataLength > 0)
    {
      int nReadSize = Math.min(buff.length, size);
      while (nDataLength > 0)
      {
        nReadSize = Math.min(buff.length, nDataLength);
        int nTmp = in.read(buff, 0, nReadSize);
        if (nTmp == -1) {
          break;
        }
        writeChannel(channel, buff, 0, nTmp);
        nDataLength -= nTmp;
      }
    }
    else
    {
      int nReadLength = 0;
      while ((nReadLength = in.read(buff)) != -1) {
        writeChannel(channel, buff, 0, nReadLength);
      }
    }
  }
  
  public static void copyStream(InputStream in, OutputStream out, int size)
    throws IOException
  {
    byte[] buff = new byte['?'];
    int nDataLength = size;
    if (nDataLength > 0)
    {
      int nReadSize = Math.min(buff.length, size);
      while (nDataLength > 0)
      {
        nReadSize = Math.min(buff.length, nDataLength);
        int nTmp = in.read(buff, 0, nReadSize);
        if (nTmp == -1) {
          break;
        }
        out.write(buff, 0, nTmp);
        nDataLength -= nTmp;
      }
    }
    else
    {
      int nReadLength = 0;
      while ((nReadLength = in.read(buff)) != -1) {
        out.write(buff, 0, nReadLength);
      }
    }
  }
  
  public static List<String> toList(String[] strings)
  {
    LinkedList<String> list = new LinkedList();
    if (strings != null)
    {
      String[] arrayOfString = strings;int j = strings.length;
      for (int i = 0; i < j; i++)
      {
        String s = arrayOfString[i];
        
        list.add(s);
      }
    }
    return list;
  }
  
  public static void close(Closeable closeable)
  {
    if (closeable != null) {
      try
      {
        closeable.close();
      }
      catch (Throwable localThrowable) {}
    }
  }
 
  
  public static boolean checkIP(String target, String check)
  {
    if ((target == null) || (check == null)) {
      return false;
    }
    if (target.equals(check)) {
      return true;
    }
    if (target.indexOf(".") != -1)
    {
      if (check.indexOf(".") == -1) {
        return false;
      }
      StringTokenizer st1 = new StringTokenizer(target, ".");
      StringTokenizer st2 = new StringTokenizer(check, ".");
      if (st1.countTokens() != st2.countTokens()) {
        return false;
      }
      while (st1.hasMoreTokens()) {
        if (!checkIPToken(st1.nextToken(), st2.nextToken())) {
          return false;
        }
      }
    }
    else if (target.indexOf(":") != -1)
    {
      if (check.indexOf(":") == -1) {
        return false;
      }
      StringTokenizer st1 = new StringTokenizer(target, ":");
      StringTokenizer st2 = new StringTokenizer(check, ":");
      if (st1.countTokens() != st2.countTokens()) {
        return false;
      }
      while (st1.hasMoreTokens()) {
        if (!checkIPToken(st1.nextToken(), st2.nextToken())) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean checkIPToken(String target, String check)
  {
    if ((target.equals(check)) || ("*".equals(target))) {
      return true;
    }
    if (target.indexOf("~") != -1)
    {
      int nIndex = target.indexOf("~");
      int nOrgStart = toInt(target.substring(0, nIndex), -1);
      int nOrgEnd = toInt(target.substring(nIndex + 1), -1);
      int nSrc = toInt(check, -1);
      
      return (nSrc != -1) && (nSrc >= nOrgStart) && (nSrc <= nOrgEnd);
    }
    return false;
  }
  
  public static void closePool(Map<?, Closeable> pool)
  {
    Map<Object, Closeable> tmpPool = new HashMap();
    
    tmpPool.putAll(pool);
    
    Iterator<?> ite = tmpPool.keySet().iterator();
    if (ite == null) {
      return;
    }
    Closeable closeable = null;
    while (ite.hasNext()) {
      try
      {
        Object obj = ite.next();
        closeable = (Closeable)tmpPool.get(obj);
        if (closeable != null) {
          closeable.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    tmpPool.clear();
  }
  

}
