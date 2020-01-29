package homework_17_2701_optional.reporting;

import homework_17_2701_optional.common.company_exceptions.checked.ReportExceptions;

public interface ReportService {
    void exportData() throws ReportExceptions;
}

