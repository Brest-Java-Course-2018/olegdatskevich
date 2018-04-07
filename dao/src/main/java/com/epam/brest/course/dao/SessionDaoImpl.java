package com.epam.brest.course.dao;

import com.epam.brest.course.model.dao.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collection;
import java.util.Date;

/**
 * Implementation of DAO layer for session.
 */
public class SessionDaoImpl implements SessionDao {

    /**
     * Logger for SessionDaoImpl.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SessionDaoImpl.class);

    /**
     * NamedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Column sessionId in session table DB.
     */
    private static final String SESSION_ID = "sessionId";
    /**
     * Column sessionDate in session table DB.
     */
    private static final String SESSION_DATE = "sessionDate";
    /**
     * Column sessionTime in session table DB.
     */
    private static final String SESSION_TIME = "sessionTime";
    /**
     * Column sessionCost in session table DB.
     */
    private static final String SESSION_COST = "sessionCost";
    /**
     * Column sessionSold in session table DB.
     */
    private static final String SESSION_SOLD = "sessionSold";
    /**
     * Column sessionActive in session table DB.
     */
    private static final String SESSION_ACTIVE = "sessionActive";
    /**
     * Column movieId in session table DB.
     */
    private static final String MOVIE_ID = "movieId";
    /**
     * fromDate for SQL query.
     */
    private static final String FROM_DATE = "fromDate";
    /**
     * toDate for SQL query.
     */
    private static final String TO_DATE = "toDate";

    /**
     * SQL query for select all sessions.
     */
    @Value("${session.select}")
    private String sessionsSelect;
    /**
     * SQL query for select session by id.
     */
    @Value("${session.selectById}")
    private String sessionSelectById;
    /**
     * SQL query for filter sessions by date.
     */
    @Value("${session.filter}")
    private String sessionFilter;
    /**
     * SQL query for insert session.
     */
    @Value("${session.insert}")
    private String insert;
    /**
     * SQL query for update session.
     */
    @Value("${session.update}")
    private String update;
    /**
     * SQL query for delete session.
     */
    @Value("${session.delete}")
    private String delete;

    /**
     * Setter for NamedParameterJdbcTemplate.
     * @param namedParameterJdbcTemplate - namedParam.
     */
    public final void setNamedParameterJdbcTemplate(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final Collection<Session> getSessions() {
        LOGGER.debug("getSessions()");
        Collection<Session> sessions = namedParameterJdbcTemplate
                .getJdbcOperations()
                .query(sessionsSelect,
                        BeanPropertyRowMapper.newInstance(Session.class));
        return sessions;
    }

    @Override
    public final Session getSessionById(final int sessionId) {
        LOGGER.debug("getSessionById()");
        SqlParameterSource namedParameters
                = new MapSqlParameterSource(SESSION_ID, sessionId);
        Session session = namedParameterJdbcTemplate.queryForObject(
                sessionSelectById,
                namedParameters,
                BeanPropertyRowMapper.newInstance(Session.class));
        return session;
    }

    @Override
    public final Session addSession(final Session session) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue(SESSION_DATE, session.getSessionDate())
                .addValue(SESSION_TIME, session.getSessionTime())
                .addValue(SESSION_COST, session.getSessionCost())
                .addValue(SESSION_SOLD, session.getSessionSold())
                .addValue(SESSION_ACTIVE, session.isSessionActive())
                .addValue(MOVIE_ID, session.getMovieId());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insert, namedParameters,
                generatedKeyHolder);
        session.setSessionId(generatedKeyHolder.getKey().intValue());
        return session;
    }

    @Override
    public final void updateSession(final Session session) {
        LOGGER.debug("updateSession({})", session);
        SqlParameterSource namedParameter
                = new BeanPropertySqlParameterSource(session);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    @Override
    public final void deleteSession(final int sessionId) {
        LOGGER.debug("deleteSession({})", sessionId);
        namedParameterJdbcTemplate.
                getJdbcOperations().update(delete, sessionId);
    }

    @Override
    public final Collection<Session> filterSessionByDate(final Date fromDate,
                                                         final Date toDate) {
        LOGGER.debug("filterSessionByDate({}, {})", fromDate, toDate);

        SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue(FROM_DATE, fromDate)
        .addValue(TO_DATE, toDate);

        Collection<Session> sessions = namedParameterJdbcTemplate.query(
                sessionFilter,
                namedParameters,
                BeanPropertyRowMapper.newInstance(Session.class));
        return sessions;
    }
}
