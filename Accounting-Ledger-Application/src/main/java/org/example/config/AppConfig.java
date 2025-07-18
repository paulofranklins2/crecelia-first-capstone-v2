package org.example.config;

import org.example.controller.ConsoleUtils;
import org.example.controller.HomeController;
import org.example.controller.LedgerController;
import org.example.controller.ReportController;
import org.example.repository.TransactionRepository;
import org.example.service.TransactionService;

import java.util.Scanner;

/**
 * Simple configuration class that wires application components together.
 */
public class AppConfig {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        ConsoleUtils consoleUtils = new ConsoleUtils(scanner);
        TransactionRepository repository = new TransactionRepository();
        TransactionService transactionService = new TransactionService(repository, consoleUtils, scanner);

        HomeController homeController = new HomeController(consoleUtils, transactionService, scanner);
        LedgerController ledgerController = new LedgerController(consoleUtils, transactionService, scanner);
        ReportController reportController = new ReportController(consoleUtils, transactionService, scanner);

        homeController.setLedgerController(ledgerController);
        ledgerController.setHomeController(homeController);
        ledgerController.setReportController(reportController);
        reportController.setLedgerController(ledgerController);
        reportController.setHomeController(homeController);

        homeController.start();
    }
}