package com.code.comm;

import java.lang.reflect.Field;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SqlSmartFormatFunctions implements SqlSmartFormat {

	//Dynamic param
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

	  //Static param
    /*	private SqlParameterSource getSqlParameterByModel(ProductBeanIn_U001 input) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("enabled", input.getEnabled());
		paramSource.addValue("prcd", input.getPrcd());
		paramSource.addValue("username", input.getUsername());
		// join String
		return paramSource;
	}*/
}
