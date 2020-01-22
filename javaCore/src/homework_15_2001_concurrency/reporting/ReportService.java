package homework_15_2001_concurrency.reporting;

import homework_15_2001_concurrency.common.company_exceptions.checked.ReportExceptions;

public interface ReportService {
    void exportData() throws ReportExceptions;
}

