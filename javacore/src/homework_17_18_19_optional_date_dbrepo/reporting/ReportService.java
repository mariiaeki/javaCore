package homework_17_18_19_optional_date_dbrepo.reporting;

import homework_17_18_19_optional_date_dbrepo.common.company_exceptions.checked.ReportExceptions;

public interface ReportService {
    void exportData() throws ReportExceptions;
}

