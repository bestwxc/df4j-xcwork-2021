package com.df4j.xcwork.base.jdbc;

import com.df4j.xcwork.base.exception.XcworkException;
import com.df4j.xcwork.base.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * JDBC操作工具类
 */
public class JdbcUtils {

    private static Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    /**
     * 获取连接
     *
     * @param dataSource
     * @return
     */
    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 执行并返回执行结果
     *
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    public static Boolean execute(Connection connection, String sql, Object[] args) {
        return executePreparedStatement(connection, sql, args, ps -> {
            try {
                return ps.execute();
            } catch (SQLException e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 执行更新操作
     *
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    public static int executeUpdate(Connection connection, String sql, Object[] args) {
        return executePreparedStatement(connection, sql, args, ps -> {
            try {
                return ps.executeUpdate();
            } catch (SQLException e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 执行查询操作
     *
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    public static ResultSet executeQuery(Connection connection, String sql, Object[] args) {
        return executePreparedStatement(connection, sql, args, ps -> {
            try {
                return ps.executeQuery();
            } catch (SQLException e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 使用PreparedStatement执行逻辑
     *
     * @param connection
     * @param sql
     * @param args
     * @param function
     * @param <R>
     * @return
     */
    public static <R> R executePreparedStatement(Connection connection, String sql, Object[] args, Function<PreparedStatement, R> function) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i, args[i]);
                }
            }
            return function.apply(ps);
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 使用statement 执行sql
     *
     * @param connection
     * @param sql
     * @param function
     * @param <R>
     * @return
     */
    public static <R> R executeStatement(Connection connection, String sql, BiFunction<Statement, String, R> function) {
        try (Statement statement = connection.createStatement()) {
            return function.apply(statement, sql);
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 执行SQL
     *
     * @param connection
     * @param sql
     * @return
     */
    public static Boolean execute(Connection connection, String sql) {
        return executeStatement(connection, sql, (stmt, s) -> {
            try {
                return stmt.execute(s);
            } catch (SQLException e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 执行更新语句
     *
     * @param connection
     * @param sql
     * @return
     */
    public static int executeUpdate(Connection connection, String sql) {
        return executeStatement(connection, sql, (stmt, s) -> {
            try {
                return stmt.executeUpdate(s);
            } catch (SQLException e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 执行查询语句
     *
     * @param connection
     * @param sql
     * @return
     */
    public static ResultSet executeQuery(Connection connection, String sql) {
        return executeStatement(connection, sql, (statement, s) -> {
            try {
                return statement.executeQuery(s);
            } catch (SQLException e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 在CallableStatement中执行语句
     *
     * @param connection
     * @param sql
     * @param args
     * @param function
     * @param <R>
     * @return
     */
    public static <R> R executeCall(Connection connection, String sql, Object[] args, Function<CallableStatement, R> function) {
        try (CallableStatement cs = connection.prepareCall(sql);) {
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    cs.setObject(i, args[i]);
                }
            }
            return function.apply(cs);
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 在调用过程中执行
     *
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    public static Boolean executeCall(Connection connection, String sql, Object[] args) {
        return executeCall(connection, sql, args, cs -> {
            try {
                return cs.execute();
            } catch (Exception e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 在调用过程中执行查询
     *
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    public static ResultSet executeCallQuery(Connection connection, String sql, Object[] args) {
        return executeCall(connection, sql, args, cs -> {
            try {
                return cs.executeQuery();
            } catch (Exception e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 在调用过程中执行更新
     *
     * @param connection
     * @param sql
     * @param args
     * @return
     */
    public static int executeCallUpdate(Connection connection, String sql, Object[] args) {
        return executeCall(connection, sql, args, cs -> {
            try {
                return cs.executeUpdate();
            } catch (Exception e) {
                throw XcworkException.repack(e);
            }
        });
    }

    /**
     * 创建数据库连接
     *
     * @param url
     * @param driverClassName
     * @param userName
     * @param pass
     * @return
     */
    public static Connection createConnection(String url, String driverClassName, String userName, String pass) {
        try {
            Class.forName(driverClassName);
            Connection connection = DriverManager.getConnection(url, userName, pass);
            return connection;
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 关闭连接
     *
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        IOUtils.close(connection);
    }

    /**
     * 读取ResultSet中数据，返回元素为Map的List
     *
     * @param rs
     * @return
     */
    public static List<Map> extract(ResultSet rs) {
        try {
            String[] keys = getResultKeys(rs);
            return extract(rs, (resultSet, n) -> {
                Map map = new LinkedCaseInsensitiveMap();
                for (int i = 0; i < keys.length; i++) {
                    map.put(keys[i], resultSet.getObject(keys[i]));
                }
                return map;
            });
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 获取ResultSet结果的列key
     *
     * @param rs
     * @return
     */
    public static String[] getResultKeys(ResultSet rs) {
        try {
            ResultSetMetaData md = rs.getMetaData();
            int count = md.getColumnCount();
            String[] keys = new String[count];
            if (count > 0) {
                // 解析出包含的列
                for (int i = 1; i <= count; i++) {
                    keys[i - 1] = md.getColumnName(i);
                }
            }
            return keys;
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 读取ResultSet中数据，使用传入的rowMapper处理记录上的字段映射
     *
     * @param rs
     * @param rowMapper
     * @param <T>
     * @return
     */
    public static <T> List<T> extract(ResultSet rs, RowMapper<T> rowMapper) {
        try {
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rowMapper.mapRow(rs, rs.getRow()));
            }
            return list;
        } catch (Exception e) {
            throw XcworkException.repack(e);
        } finally {
            IOUtils.close(rs);
        }
    }

    /**
     * 在一个会话里面进行多个JDBC操作
     *
     * @param dataSource
     * @param consumers
     */
    public static void execute(DataSource dataSource, boolean ignoreException, Consumer<Connection>... consumers) {
        if (consumers == null || consumers.length == 0) {
            return;
        }
        Connection connection = null;
        try {
            connection = getConnection(dataSource);
            connection.setAutoCommit(false);
            for (int i = 0; i < consumers.length; i++) {
                try {
                    consumers[i].accept(connection);
                } catch (Exception e) {
                    if (ignoreException) {
                        logger.warn("批量执行Jdbc操作出现异常, 异常被忽略, index: " + i, e);
                    } else {
                        throw XcworkException.repack(e);
                    }
                }
            }
            commit(connection);
        } catch (Exception e) {
            rollback(connection);
            throw XcworkException.repack(e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 提交当前事务
     *
     * @param connection
     */
    public static void commit(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
            } catch (Exception e) {
                throw XcworkException.repack(e);
            }
        }
    }

    /**
     * 回滚当前事务
     *
     * @param connection
     */
    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (Exception e) {
                throw XcworkException.repack(e);
            }
        }
    }
}
