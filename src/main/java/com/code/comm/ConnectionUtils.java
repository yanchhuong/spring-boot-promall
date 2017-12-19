package com.code.comm;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.jdbc.core.simple.SimpleJdbcTemplate; //spring-jdbc   <version>5.0.2.RELEASE</version>

public class ConnectionUtils {
	 /** The simple jdbc insert. */
    private static SimpleJdbcInsert simpleJdbcInsert;   
    /** The jdbc template. */
    private  static JdbcTemplate jdbcTemplate;
    /** The simple jdbc template. */
  //  private SimpleJdbcTemplate simpleJdbcTemplate;
    /** The named parameter jdbc template. */
    private  static WrapperNamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /** Logger para trazar la informacion */
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    /**
     * Sets the data source.
     *
     * @param dataSource the new data source
     */
    public static void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
  //      simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new WrapperNamedParameterJdbcTemplate(dataSource);
    }
    public static JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		ConnectionUtils.jdbcTemplate = jdbcTemplate;
	}
	/**
     * Gets the named parameter jdbc template.
     *
     * @return the named parameter jdbc template
     */
    public static WrapperNamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
    /**
     * Sets the named parameter jdbc template.
     *
     * @param namedParameterJdbcTemplate the new named parameter jdbc template
     */
    public void setNamedParameterJdbcTemplate(WrapperNamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

}
