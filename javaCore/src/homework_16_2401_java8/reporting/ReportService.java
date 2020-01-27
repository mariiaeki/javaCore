package homework_16_2401_java8.reporting;

import homework_16_2401_java8.common.company_exceptions.checked.ReportExceptions;

public interface ReportService {
    void exportData() throws ReportExceptions;
}

