package net.thumbtack.school.elections;

import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class Init {

    private static boolean setUpIsDone = false;

    @BeforeAll()
    public static void setUp() {
        if (!setUpIsDone) {
            boolean initSqlSessionFactory = MyBatisUtils.initSqlSessionFactory();
            if (!initSqlSessionFactory) {
                throw new RuntimeException("Can't create connection, stop");
            }
            setUpIsDone = true;
        }
    }

    @BeforeEach()
    public void startServer() throws IOException {
        Server.startServer(null);
    }

    @AfterEach()
    public void stopServer() throws IOException {
        Server.stopServer(null);
    }
}
