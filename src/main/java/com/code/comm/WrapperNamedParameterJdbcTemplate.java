package com.code.comm;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
public class WrapperNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate {
    public WrapperNamedParameterJdbcTemplate(DataSource dataSource) {
        super(dataSource);
        // TODO Auto-generated constructor stub
    }
    public WrapperNamedParameterJdbcTemplate(JdbcOperations classicJdbcTemplate) {
        super(classicJdbcTemplate);
        // TODO Auto-generated constructor stub
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#execute(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, org.springframework.jdbc.core.PreparedStatementCallback)
     */
    @Override
    public <T> T execute(String sql, SqlParameterSource paramSource, PreparedStatementCallback<T> action)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.execute(sql, paramSource, action);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#execute(java.lang.String, java.util.Map, org.springframework.jdbc.core.PreparedStatementCallback)
     */
    @Override
    public <T> T execute(String sql, Map<String, ?> paramMap, PreparedStatementCallback<T> action)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.execute(sql, paramMap, action);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#execute(java.lang.String, org.springframework.jdbc.core.PreparedStatementCallback)
     */
    @Override
    public <T> T execute(String sql, PreparedStatementCallback<T> action) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql);
        return super.execute(sql, action);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, org.springframework.jdbc.core.ResultSetExtractor)
     */
    @Override
    public <T> T query(String sql, SqlParameterSource paramSource, ResultSetExtractor<T> rse)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.query(sql, paramSource, rse);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, java.util.Map, org.springframework.jdbc.core.ResultSetExtractor)
     */
    @Override
    public <T> T query(String sql, Map<String, ?> paramMap, ResultSetExtractor<T> rse) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.query(sql, paramMap, rse);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, org.springframework.jdbc.core.ResultSetExtractor)
     */
    @Override
    public <T> T query(String sql, ResultSetExtractor<T> rse) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql);
        return super.query(sql, rse);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, org.springframework.jdbc.core.RowCallbackHandler)
     */
    @Override
    public void query(String sql, SqlParameterSource paramSource, RowCallbackHandler rch) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        super.query(sql, paramSource, rch);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, java.util.Map, org.springframework.jdbc.core.RowCallbackHandler)
     */
    @Override
    public void query(String sql, Map<String, ?> paramMap, RowCallbackHandler rch) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        super.query(sql, paramMap, rch);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, org.springframework.jdbc.core.RowCallbackHandler)
     */
    @Override
    public void query(String sql, RowCallbackHandler rch) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql);
        super.query(sql, rch);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, org.springframework.jdbc.core.RowMapper)
     */
    @Override
    public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.query(sql, paramSource, rowMapper);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, java.util.Map, org.springframework.jdbc.core.RowMapper)
     */
    @Override
    public <T> List<T> query(String sql, Map<String, ?> paramMap, RowMapper<T> rowMapper) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.query(sql, paramMap, rowMapper);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#query(java.lang.String, org.springframework.jdbc.core.RowMapper)
     */
    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql);
        return super.query(sql, rowMapper);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForObject(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, org.springframework.jdbc.core.RowMapper)
     */
    @Override
    public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.queryForObject(sql, paramSource, rowMapper);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForObject(java.lang.String, java.util.Map, org.springframework.jdbc.core.RowMapper)
     */
    @Override
    public <T> T queryForObject(String sql, Map<String, ?> paramMap, RowMapper<T> rowMapper) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.queryForObject(sql, paramMap, rowMapper);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForObject(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, java.lang.Class)
     */
    @Override
    public <T> T queryForObject(String sql, SqlParameterSource paramSource, Class<T> requiredType)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.queryForObject(sql, paramSource, requiredType);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForObject(java.lang.String, java.util.Map, java.lang.Class)
     */
    @Override
    public <T> T queryForObject(String sql, Map<String, ?> paramMap, Class<T> requiredType) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.queryForObject(sql, paramMap, requiredType);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForMap(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource)
     */
    @Override
    public Map<String, Object> queryForMap(String sql, SqlParameterSource paramSource) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.queryForMap(sql, paramSource);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForMap(java.lang.String, java.util.Map)
     */
    @Override
    public Map<String, Object> queryForMap(String sql, Map<String, ?> paramMap) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.queryForMap(sql, paramMap);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForList(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, java.lang.Class)
     */
    @Override
    public <T> List<T> queryForList(String sql, SqlParameterSource paramSource, Class<T> elementType)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.queryForList(sql, paramSource, elementType);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForList(java.lang.String, java.util.Map, java.lang.Class)
     */
    @Override
    public <T> List<T> queryForList(String sql, Map<String, ?> paramMap, Class<T> elementType)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.queryForList(sql, paramMap, elementType);
    }
    
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForList(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource)
     */
    @Override
    public List<Map<String, Object>> queryForList(String sql, SqlParameterSource paramSource)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.queryForList(sql, paramSource);
    }
    
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForList(java.lang.String, java.util.Map)
     */
    @Override
    public List<Map<String, Object>> queryForList(String sql, Map<String, ?> paramMap) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.queryForList(sql, paramMap);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForRowSet(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource)
     */
    @Override
    public SqlRowSet queryForRowSet(String sql, SqlParameterSource paramSource) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.queryForRowSet(sql, paramSource);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#queryForRowSet(java.lang.String, java.util.Map)
     */
    @Override
    public SqlRowSet queryForRowSet(String sql, Map<String, ?> paramMap) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.queryForRowSet(sql, paramMap);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#update(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource)
     */
    @Override
    public int update(String sql, SqlParameterSource paramSource) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.update(sql, paramSource);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#update(java.lang.String, java.util.Map)
     */
    @Override
    public int update(String sql, Map<String, ?> paramMap) throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramMap);
        return super.update(sql, paramMap);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#update(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, org.springframework.jdbc.support.KeyHolder)
     */
    @Override
    public int update(String sql, SqlParameterSource paramSource, KeyHolder generatedKeyHolder)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.update(sql, paramSource, generatedKeyHolder);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#update(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource, org.springframework.jdbc.support.KeyHolder, java.lang.String[])
     */
    @Override
    public int update(String sql, SqlParameterSource paramSource, KeyHolder generatedKeyHolder, String[] keyColumnNames)
            throws DataAccessException {
        SqlQueriesUtil.debugSQL(sql, paramSource);
        return super.update(sql, paramSource, generatedKeyHolder, keyColumnNames);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#batchUpdate(java.lang.String, java.util.Map[])
     */
    @Override
    public int[] batchUpdate(String sql, Map<String, ?>[] batchValues) {
        SqlQueriesUtil.debugSQL(sql, batchValues);
        return super.batchUpdate(sql, batchValues);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#batchUpdate(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource[])
     */
    @Override
    public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) {
        SqlQueriesUtil.debugSQL(sql, batchArgs);
        return super.batchUpdate(sql, batchArgs);
    }
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#getPreparedStatementCreator(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource)
     */
//    @Override
//    protected PreparedStatementCreator getPreparedStatementCreator(String sql, SqlParameterSource paramSource) {
//        DaoUtil.debugSQL(sql, paramSource);
//        return super.getPreparedStatementCreator(sql, paramSource);
//    }
//
//    /* (non-Javadoc)
//     * @see org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate#getParsedSql(java.lang.String)
//     */
//    @Override
//    protected ParsedSql getParsedSql(String sql) {
//        DaoUtil.debugSQL(sql);
//        return super.getParsedSql(sql);
//    }
}