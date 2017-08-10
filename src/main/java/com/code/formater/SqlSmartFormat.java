package com.code.formater;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public interface SqlSmartFormat {
	public SqlParameterSource getSqlParameterByModel(Object input) throws IllegalArgumentException, IllegalAccessException;
}
