package com.mathias.jabuti.seeder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final DataSource dataSource;

  private static final List<String> SQL_FILES = Arrays.asList(
    "seeds/user.sql", 
    "seeds/goal.sql",
    "seeds/permissions.sql",
    "seeds/groups.sql",
    "seeds/group_permission.sql",
    "seeds/user_group.sql"
    );

  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";

  public DatabaseSeeder(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  // mvn spring-boot:run -Dspring-boot.run.arguments="seed"
  @Override
  public void run(String... args) throws Exception {
    if (args.length > 0 && args[0].equals("seed")) {

      info("Starting database seeding...");
      executeSqlFiles();
    }
  }

  private void executeSqlFiles() throws Exception, SQLException {
    try (Connection connection = dataSource.getConnection()) {
      for (String sqlFile : SQL_FILES) {
        try {
          ClassPathResource resource = new ClassPathResource(sqlFile);
          if (!resource.exists()) {
            throw new RuntimeException("SQL file not found: " + sqlFile);
          }
          ScriptUtils.executeSqlScript(connection, resource);
          System.out.println(colorized("Sucessfully executed: ", ANSI_GREEN) + sqlFile);
        } catch (Exception e) {
          error("Error executing " + sqlFile + ": " + e.getMessage());
          throw e;
        }
      }
      success("Database seeding completed successfully!");
    }
  }

  public static void info(String message) {
    System.out.println(ANSI_BLUE + "[INFO] " + ANSI_RESET + message);
  }

  public static void success(String message) {
    System.out.println(ANSI_GREEN + "[SUCCESS] " + ANSI_RESET + message);
  }

  public static void error(String message) {
    System.out.println(ANSI_RED + "[ERROR] " + ANSI_RESET + message);
  }

  public static String colorized(String messagem, String ansiColor) {
    return ansiColor + messagem + ANSI_RESET;
  }
}
