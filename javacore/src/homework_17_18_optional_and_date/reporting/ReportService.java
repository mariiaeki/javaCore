package homework_17_18_optional_and_date.reporting;

import homework_17_18_optional_and_date.common.company_exceptions.checked.ReportExceptions;

public interface ReportService {
    void exportData() throws ReportExceptions;
}

