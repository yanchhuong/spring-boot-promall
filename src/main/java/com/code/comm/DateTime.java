package com.code.comm;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;


public class DateTime
{
  private static Map<Locale, DateTime> _instance = new ConcurrentHashMap();
  private Map<String, BlockingQueue<DateFormat>> _format = new ConcurrentHashMap();
  private Locale _locale = null;
  private int _nYear = -1;
  private int _nMonth = -1;
  private int _nDay = -1;
  private boolean _bUserTransDate = false;
  

 
  public Calendar getCalendar(String input)
    throws IllegalArgumentException
  {
    if (input == null) {
      throw new IllegalArgumentException(input);
    }
    String strInput = input.trim();
    Calendar calendar = Calendar.getInstance();
    switch (strInput.length())
    {
    case 8: 
      calendar.set(Integer.parseInt(strInput.substring(0, 4)), 
        Integer.parseInt(strInput.substring(4, 6)) - 1, 
        Integer.parseInt(strInput.substring(6)));
      break;
    case 14: 
      calendar.set(
        Integer.parseInt(strInput.substring(0, 4)), 
        Integer.parseInt(strInput.substring(4, 6)) - 1, 
        Integer.parseInt(strInput.substring(6, 8)), 
        Integer.parseInt(strInput.substring(8, 10)), 
        Integer.parseInt(strInput.substring(10, 12)), 
        Integer.parseInt(strInput.substring(12)));
      break;
    case 6: 
      String strTmp = getDate("yyyymmdd") + strInput;
      calendar.set(
        Integer.parseInt(strTmp.substring(0, 4)), 
        Integer.parseInt(strTmp.substring(4, 6)) - 1, 
        Integer.parseInt(strTmp.substring(6, 8)), 
        Integer.parseInt(strTmp.substring(8, 10)), 
        Integer.parseInt(strTmp.substring(10, 12)), 
        Integer.parseInt(strTmp.substring(12)));
      break;
    default: 
      throw new IllegalArgumentException(input);
    }
    return calendar;
  }
  

  
  private Locale _getLocale()
  {
    return this._locale;
  }
  
  private String _getDateFormatString(String pattern)
  {
    String strFormat = pattern.toLowerCase();
    int i;
    if ((i = strFormat.indexOf("mmm")) != -1) {
      strFormat = strFormat.substring(0, i).concat("MMM").concat(strFormat.substring(i + 3));
    }
    if ((i = strFormat.indexOf("eee")) != -1) {
      strFormat = strFormat.substring(0, i).concat("EEE").concat(strFormat.substring(i + 3));
    }
    if ((i = strFormat.indexOf("g")) != -1) {
      strFormat = strFormat.substring(0, i).concat("G").concat(strFormat.substring(i + 1));
    }
    if ((i = strFormat.indexOf("hh24")) != -1) {
      strFormat = strFormat.substring(0, i).concat("HH").concat(strFormat.substring(i + 4));
    }
    if ((i = strFormat.indexOf("ms")) != -1) {
      strFormat = strFormat.substring(0, i).concat("SSS").concat(strFormat.substring(i + 2));
    }
    if ((i = strFormat.indexOf("mm")) != -1) {
      strFormat = strFormat.substring(0, i).concat("MM").concat(strFormat.substring(i + 2));
    }
    if ((i = strFormat.indexOf("mi")) != -1) {
      strFormat = strFormat.substring(0, i).concat("mm").concat(strFormat.substring(i + 2));
    }
    if ((i = strFormat.indexOf("ttt")) != -1) {
      strFormat = strFormat.substring(0, i).concat("SSS").concat(strFormat.substring(i + 3));
    }
    return strFormat;
  }
  
  private DateFormat _getDateFormat(String key, String pattern)
  {
    if (pattern == null) {
      return null;
    }
    BlockingQueue<DateFormat> queue = getFormatQueue(key);
    DateFormat result = null;
    if (queue != null) {
      result = (DateFormat)queue.poll();
    }
    if (result == null) {
      result = new SimpleDateFormat(_getDateFormatString(pattern), _getLocale());
    }
    return result;
  }
  
  private void _addDateFormat(String key, DateFormat format)
  {
    BlockingQueue<DateFormat> queue = getFormatQueue(key);
    if (queue == null) {
      return;
    }
    try
    {
      queue.add(format);
    }
    catch (Exception localException) {}
  }
  
  private final BlockingQueue<DateFormat> getFormatQueue(String key)
  {
    BlockingQueue<DateFormat> result = (BlockingQueue)this._format.get(key);
    if (result == null) {
      synchronized (this)
      {
        if (this._format.size() == 99)
        {
          Exception exception = new Exception("To many create DateFormat");
          exception.printStackTrace();
          exception.printStackTrace(System.out);
        }
        else if (this._format.size() > 100)
        {
          return null;
        }
        result = new ArrayBlockingQueue(1000);
        this._format.put(key, result);
      }
    }
    return result;
  }
  
  public Calendar getTransactionDay()
  {
    Calendar calendar = Calendar.getInstance();
    if (this._bUserTransDate) {
      calendar.set(this._nYear, this._nMonth, this._nDay);
    }
    return calendar;
  }
  
  public String getSysDate(String format)
  {
    return getDate(Calendar.getInstance().getTime(), format);
  }
  
  public String getSysDate(String format, char c, int i)
  {
    return getDate(Calendar.getInstance().getTime(), format, c, i);
  }
  
  public String getDate(String format)
  {
    return getDate(getTransactionDay().getTime(), format);
  }
  
  public String getDate(String format, char c, int i)
  {
    return getDate(getTransactionDay().getTime(), format, c, i);
  }
  
  public String getDate(String inputDate, String format)
  {
    try
    {
      return getDate(getCalendar(inputDate).getTime(), format);
    }
    catch (IllegalArgumentException ie) {}
    return "";
  }
  
  public String getDate(long date, String format)
  {
    return getDate(new Date(date), format);
  }
  
  public String getDate(String inputDate, String format, char c, int i)
  {
    try
    {
      return getDate(getCalendar(inputDate).getTime(), format, c, i);
    }
    catch (IllegalArgumentException ie) {}
    return "";
  }
  
  public String getDate(long date, String format, char c, int i)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(date);
    switch (c)
    {
    case 'Y': 
      calendar.add(1, i);
      break;
    case 'M': 
      calendar.add(2, i);
      break;
    case 'W': 
      calendar.add(4, i);
      break;
    case 'D': 
      calendar.add(5, i);
      break;
    case 'H': 
      calendar.add(10, i);
      break;
    case 'I': 
      calendar.add(12, i);
      break;
    case 'S': 
      calendar.add(13, i);
    }
    return getDate(calendar.getTime(), format);
  }
  
  public long getDate(long date, char c, int i)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(date);
    switch (c)
    {
    case 'Y': 
      calendar.add(1, i);
      break;
    case 'M': 
      calendar.add(2, i);
      break;
    case 'W': 
      calendar.add(4, i);
      break;
    case 'D': 
      calendar.add(5, i);
      break;
    case 'H': 
      calendar.add(10, i);
      break;
    case 'I': 
      calendar.add(12, i);
      break;
    case 'S': 
      calendar.add(13, i);
    }
    return calendar.getTime().getTime();
  }
  
  public String getDate(Date date, String format, char c, int i)
  {
    return getDate(date.getTime(), format, c, i);
  }
  
  public int getDayBetween(String fromDate, String toDate)
  {
    return getDayBetween(getCalendar(fromDate).getTime(), getCalendar(toDate).getTime());
  }
  
  public int getDayBetween(Date fromDate, Date toDate)
  {
    Calendar fromCal = Calendar.getInstance();
    Calendar toCal = Calendar.getInstance();
    fromCal.setTime(fromDate);
    toCal.setTime(toDate);
    return getDayBetween(fromCal, toCal);
  }
  
  public int getDayBetween(Calendar fromDate, Calendar toDate)
  {
    Calendar tmpCal = Calendar.getInstance();
    int nFromYear = fromDate.get(1);
    int nToYear = toDate.get(1);
    int nFromDate = fromDate.get(6);
    int nToDate = toDate.get(6);
    int nCheckDate = 0;
    if (nFromYear < nToYear)
    {
      for (int i = nFromYear; i < nToYear; i++)
      {
        tmpCal.set(i, 11, 31);
        nCheckDate += tmpCal.get(6);
      }
      return nCheckDate + nToDate - nFromDate;
    }
    for (int i = nToYear; i < nFromYear; i++)
    {
      tmpCal.set(i, 11, 31);
      nCheckDate += tmpCal.get(6);
    }
    return (nCheckDate + nFromDate - nToDate) * -1;
  }
  
  public String getTimeBetween(String hhmmss1, String hhmmss2)
  {
    int ss = getSecondBetween(hhmmss1, hhmmss2);
    
    int mm = ss / 60;
    ss %= 60;
    
    int hh = mm / 60;
    mm %= 60;
    
    return ""+hh + mm + ss;
  }
  
  private int getSecondBetween(String hhmmss1, String hhmmss2)
  {
    return Math.abs(toSecond(hhmmss2) - toSecond(hhmmss1));
  }
  
  private int toSecond(String hhmmss)
  {
    try
    {
      int hh = Integer.parseInt(hhmmss.substring(0, 2));
      int mm = Integer.parseInt(hhmmss.substring(2, 4));
      int ss = Integer.parseInt(hhmmss.substring(4, 6));
      
      return (hh * 60 + mm) * 60 + ss;
    }
    catch (Exception e)
    {
      e.printStackTrace(System.out);
    }
    return -1;
  }
  
  public String getDate(Date date, String format)
  {
    String strKey = format.toLowerCase();
    DateFormat dateFromat = null;
    try
    {
      dateFromat = _getDateFormat(strKey, format);
      return dateFromat != null ? dateFromat.format(date) : "";
    }
    finally
    {
      if (dateFromat != null) {
        _addDateFormat(strKey, dateFromat);
      }
    }
  }
  
  public boolean isLastDay(String format)
  {
    return isLastDay(getCalendar(format).getTimeInMillis());
  }
  
  public boolean isLastDay(Date date)
  {
    return isLastDay(date.getTime());
  }
  
  public boolean isLastDay(long date)
  {
    String strMM = getDate(date, "MM");
    String strMM2 = getDate(date, "MM", 'D', 1);
    
    return !strMM.equals(strMM2);
  }
}
