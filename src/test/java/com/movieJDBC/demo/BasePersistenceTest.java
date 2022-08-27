package com.movieJDBC.demo;


import org.junit.ClassRule;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(
        showSql = true
)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = "test")
@Testcontainers
@TestPropertySource(properties = {
       // "spring.jpa.hibernate.ddl-auto=validate",
      //  "spring.jpa.hibernate.ddl-auto=update",
        "spring.datasource.username=user",
        "spring.datasource.password=password",
      //  "spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect",
      //  "spring.jpa.show-sql=true",
      //  "spring.jpa.database=mysql",
        "spring.flyway.enabled=true",
        "spring.flyway.locations=classpath:/db/migration",
        "spring.flyway.baselineOnMigrate=true"
})
public abstract class BasePersistenceTest {
    @ClassRule
    public static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5.7.24")
            .withUsername("user")
            .withPassword("password")
            .withDatabaseName("alicjadb")
            .withUrlParam("useSSL","false")
            .withCommand("mysqld", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci",
                    "--explicit_defaults_for_timestamp","--lower_case_table_names=1","--sql_mode=NO_ENGINE_SUBSTITUTION");

    static {
        mySQLContainer.addFileSystemBind("../etc/mysql-schema/" + File.separator + "startup.sql",
                "/docker-entrypoint-initdb.d/startup.sql",
                BindMode.READ_ONLY);
        mySQLContainer.start();
    }
    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    }

}
