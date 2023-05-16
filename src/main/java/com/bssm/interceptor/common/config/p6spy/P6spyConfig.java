package com.bssm.interceptor.common.config.p6spy;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;


@Configuration
public class P6spyConfig extends JdbcEventListener implements MessageFormattingStrategy {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SEPARATOR = " | ";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    @Override
    public void onAfterGetConnection(ConnectionInformation connectionInformation, SQLException e) {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(getClass().getName());
    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category,
        String prepared, String sql, String url) {
        StringBuilder message = basicInfo(now, elapsed, category, connectionId, url);
        return formatSql(message, sql);
    }

    private String formatSql(StringBuilder message, String sql) {
        if (!StringUtils.hasText(sql)) {
            return message.toString();
        }
        return message.append(highlight(format(sql))).toString();
    }

    private String highlight(String sql) {
        return FormatStyle.HIGHLIGHT.getFormatter().format(sql);
    }

    private String format(String sql) {
        if (isDDL(sql)) {
            return FormatStyle.DDL.getFormatter().format(sql);
        }
        return FormatStyle.BASIC.getFormatter().format(sql);
    }

    private boolean isDDL(String sql) {
        return sql.startsWith("create") || sql.startsWith("alter") || sql.startsWith("comment");
    }

    private StringBuilder basicInfo(String now, long elapsed, String category, int connectionId,
        String url) {
        return new StringBuilder()
            .append(milliSecondToDateTime(now))
            .append(SEPARATOR)
            .append(String.format("Execution Time : %s ms", elapsed))
            .append(SEPARATOR)
            .append(String.format("Category : %s", category))
            .append(SEPARATOR)
            .append(String.format("Connection Id : %s", connectionId))
            .append(SEPARATOR)
            .append(url)
            .append(NEW_LINE);
    }

    private String milliSecondToDateTime(String now) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.KOREA);
        return simpleDateFormat.format(new Date(Long.parseLong(now)));
    }
}