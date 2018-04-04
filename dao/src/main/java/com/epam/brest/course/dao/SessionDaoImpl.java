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

public class SessionDaoImpl implements SessionDao {

    private static final Logger LOGGER
            = LogManager.getLogger(MovieDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SESSION_ID = "sessionId";
    private static final String SESSION_DATE = "sessionDate";
    private static final String SESSION_TIME = "sessionTime";
    private static final String SESSION_COST = "sessionCost";
    private static final String SESSION_SOLD = "sessionSold";
    private static final String SESSION_ACTIVE = "sessionActive";
    private static final String MOVIE_ID = "movieId";
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "toDate";

    @Value("${session.select}")
    private String sessionsSelect;
    @Value("${session.selectById}")
    private String sessionSelectById;
    @Value("${session.check}")
    private String checkSession;
    @Value("${session.filter}")
    private String sessionFilter;
    @Value("${session.insert}")
    private String insert;
    @Value("${session.update}")
    private String update;
    @Value("${session.delete}")
    private String delete;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
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
        LOGGER.debug("addSession({})", session);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(SESSION_DATE, session.getSessionDate());
        namedParameters.addValue(SESSION_TIME, session.getSessionTime());
        Integer result = namedParameterJdbcTemplate
                .queryForObject(checkSession, namedParameters, Integer.class);
        if (result == 0) {
            namedParameters = new MapSqlParameterSource()
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
        } else {
            throw new IllegalArgumentException(
                    "Session with the same date and time already exists in DB.");
        }
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
    public Collection<Session> filterSessionByDate(final Date fromDate,
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
