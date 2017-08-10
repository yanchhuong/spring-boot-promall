package com.code.formater;

import java.lang.reflect.Field;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SqlSmartFormatFunctions implements SqlSmartFormat {

	@Override
	public SqlParameterSource getSqlParameterByModel(Object input) throws IllegalArgumentException, IllegalAccessException {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		for (Field field : input.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(input); 
			paramSource.addValue(field.getName(),value);
		}
		// join String
		return paramSource;
	}

}
