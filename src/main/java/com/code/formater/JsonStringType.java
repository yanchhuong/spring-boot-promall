package com.code.formater;

import java.util.Properties;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

public class JsonStringType
extends AbstractSingleColumnStandardBasicType<Object> 
implements DynamicParameterizedType {

public JsonStringType() {
super( 
    JsonStringSqlTypeDescriptor.INSTANCE, 
    new JsonTypeDescriptor(null) 
);
}

public String getName() {
return "json";
}

@Override
protected boolean registerUnderJavaType() {
return true;
}

@Override
public void setParameterValues(Properties arg0) {
	// TODO Auto-generated method stub
	
}


}