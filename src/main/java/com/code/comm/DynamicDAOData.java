package com.code.comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DynamicDAOData  implements Serializable{
	 private static final long serialVersionUID = -1272633498337933537L;
	  private String _strSQL = null;
	  private List<Object> _listParam = new ArrayList();
	  
	  public void setSQL(String sql)
	  {
	    this._strSQL = sql;
	  }
	  
	  public String getSQL()
	  {
	    return this._strSQL;
	  }
	  
	  public void addParameter(Object o)
	  {
	    this._listParam.add(o);
	  }
	  
	  public int size()
	  {
	    return this._listParam.size();
	  }
	  
	  public Object getParameter(int i)
	  {
	    return this._listParam.get(i);
	  }
	  
	  public Object removeParameter(int i)
	  {
	    return this._listParam.remove(i);
	  }
}
