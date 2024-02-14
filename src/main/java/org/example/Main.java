package org.example;

import org.example.config.H2Database;
import org.example.config.MysqlDatabase;
import org.example.config.PostgresDatabase;
import org.example.elements.TestData;
import org.example.users.User;
import org.example.users.UserWorker;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main (String[] args) throws SQLException, IOException {
        PostgresDatabase postgresDatabase = PostgresDatabase.getInstance();
        postgresDatabase.execute("src/main/resources/file.sql");
//        postgresDatabase.executeUpdate("INSERT INTO test_table VALUES (7, 'Oleg')");
//        postgresDatabase.executeResult("SELECT * FROM test_table");
//        System.out.println("=============");
//        postgresDatabase.executeUpdate("DELETE FROM test_table WHERE id = 7");
//        postgresDatabase.executeResult("SELECT * FROM test_table");
//        System.out.println("+++++++++++++++++");
//
//        MysqlDatabase mysqlDatabase = MysqlDatabase.getInstance();
//        mysqlDatabase.executeUpdate("INSERT INTO test_table VALUES (7, 'Oleg')");
//        mysqlDatabase.executeResult("SELECT * FROM test_table");
//        System.out.println("===============");
//        mysqlDatabase.executeUpdate("DELETE FROM test_table WHERE id = 7");
//        mysqlDatabase.executeResult("SELECT * FROM test_table");
//        System.out.println("+++++++++++++++++");
//
//        H2Database h2Database = H2Database.getInstance();
////        h2Database.aaa();
//        h2Database.executeUpdate("INSERT INTO test_table VALUES (7, 'Oleg')");
//        h2Database.executeResult("SELECT * FROM test_table");
//        System.out.println("===============");
//        h2Database.executeUpdate("DELETE FROM test_table WHERE id = 7");
//        h2Database.executeResult("SELECT * FROM test_table");







//        UserWorker worker = new UserWorker(connection);

        //TODO: use select without parameters (select all users)
//        List<User> allUsers = worker.findAllUser();
//        System.out.println("Users in list -> " + allUsers.size());
//        allUsers.forEach(user -> System.out.println("User -->> " + user.toString()));








        //TODO: use select by id method
//        Optional<User> optionalUser = worker.findUserById(-1L);
//        if (optionalUser.isPresent()) {
//            System.out.println("User -> " + optionalUser.get().toString());
//        } else {
//            System.out.println("No user in db with given id.");
//        }






        //TODO generate random user and add to db
        // 100 user insert vs 100 user as batch update
//        long startSimpleInsert = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            worker.saveUser( "Mark Simple " + i, LocalDate.now().minusYears(20));
//        }
//        long finishSimpleInsert = System.currentTimeMillis();
//        long resultSimpleInsert = finishSimpleInsert - startSimpleInsert;
//        System.out.println("Start single insert ===> " + startSimpleInsert);
//        System.out.println("Finish single insert ===> " + finishSimpleInsert);
//        System.out.println("Result single insert ===> " + resultSimpleInsert);
//
//        List<User> users = new ArrayList<>();
//        long startBatchInsert = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            users.add(new User( "Mark Batch " + i, LocalDate.now().minusYears(25)));
//        }
//        worker.saveAllUsers(users);
//        long finishBatchInsert = System.currentTimeMillis();
//        long resultBatchInsert = finishBatchInsert - startBatchInsert;
//        System.out.println("Start Batch insert ===> " + startBatchInsert);
//        System.out.println("Finish Batch insert ===> " + finishBatchInsert);
//        System.out.println("Result Batch insert ===> " + resultBatchInsert);
//        long result = resultSimpleInsert - resultBatchInsert;
//        System.out.println("RESULT --------->>> " + result);































        //TODO generate random 100 000 users and add to db
        // 10, 100, 1000, 10 000, 100 000 users batches and check batch speed
//        long countUsersForButches = 1000000;
//        long startId = 1L;
//        List<User> users10Batch = worker.generateTestUsers(countUsersForButches,
//                startId, "Alisa 10 batches  ", 25);
//        System.out.println("Created 1000000 for 10 batch");
//        startId = startId + 1000000L;
//        List<User> users100Batch = worker.generateTestUsers(countUsersForButches,
//                startId, "Milla 100 batches  ", 15);
//        System.out.println("Created 1000000 for 100 batch");
//        startId = startId + 1000000L;
//        List<User> users1000Batch = worker.generateTestUsers(countUsersForButches,
//                startId, "Lyda 1000 batches  ", 20);
//        System.out.println("Created 1000000 for 1000 batch");
//        startId = startId + 1000000L;
//        List<User> users10000Batch = worker.generateTestUsers(countUsersForButches,
//                startId, "Inna 10000 batches  ", 30);
//        System.out.println("Created 1000000 for 10000 batch");
//        startId = startId + 1000000L;
//        List<User> users100000Batch = worker.generateTestUsers(countUsersForButches,
//                startId, "Olga 100000 batches  ", 40);
//        System.out.println("Created 1000000 for 100000 batch");
//        startId = startId + 1000000L;
//        List<User> users1000000Batch = worker.generateTestUsers(countUsersForButches,
//                startId, "Anna 1000000 batches  ", 50);
//        System.out.println("Created 1000000 for 1000000 batch");
//        startId = startId + 1000000L;
//
//        System.out.println("Start insert 1000000 by 10 in batch");
//        long start10 = System.currentTimeMillis();
//        worker.saveAllUsersWithBatchSize(users10Batch, 10);
//        long finish10 = System.currentTimeMillis();
//        long result10 = finish10 - start10;
//        System.out.println("10 batch result ==>> " + result10);
//
//        System.out.println("Start insert 1000000 by 100 in batch");
//        long start100 = System.currentTimeMillis();
//        worker.saveAllUsersWithBatchSize(users100Batch, 100);
//        long finish100 = System.currentTimeMillis();
//        long result100 = finish100 - start100;
//        System.out.println("100 batch result ==>> " + result100);
//
//        System.out.println("Start insert 1000000 by 1000 in batch");
//        long start1000 = System.currentTimeMillis();
//        worker.saveAllUsersWithBatchSize(users1000Batch, 1000);
//        long finish1000 = System.currentTimeMillis();
//        long result1000 = finish1000 - start1000;
//        System.out.println("1000 batch result ==>> " + result1000);
//
//        System.out.println("Start insert 1000000 by 10000 in batch");
//        long start10000 = System.currentTimeMillis();
//        worker.saveAllUsersWithBatchSize(users10000Batch, 10000);
//        long finish10000 = System.currentTimeMillis();
//        long result10000 = finish10000 - start10000;
//        System.out.println("10000 batch result ==>> " + result10000);
//
//        System.out.println("Start insert 1000000 by 100000 in batch");
//        long start100000 = System.currentTimeMillis();
//        worker.saveAllUsersWithBatchSize(users100000Batch, 100000);
//        long finish100000 = System.currentTimeMillis();
//        long result100000 = finish100000 - start100000;
//        System.out.println("100000 batch result ==>> " + result100000);
//
//        System.out.println("Start insert 1000000 by 1000000 in batch");
//        long start1000000 = System.currentTimeMillis();
//        worker.saveAllUsersWithBatchSize(users1000000Batch, 1000000);
//        long finish1000000 = System.currentTimeMillis();
//        long result1000000 = finish1000000 - start1000000;
//        System.out.println("1000000 batch result ==>> " + result1000000);




















        //TODO: transaction insert 10 users (success transactional insert)
//        long startId = 5;
//        long countUsersForButches = 5;
//        List<User> users1 = worker.generateTestUsers(countUsersForButches, startId,
//                 "Transactional Alisa 10 ", 25);
//        List<User> users2 = worker.generateTestUsers(countUsersForButches, startId + 5,
//                "Transactional Milla 10 ", 15);
//        worker.transactionalInsert(users1, users2);
    }
}