package selection_committee.service;

import selection_committee.model.Application;
import selection_committee.model.Faculty;

import java.util.List;

public interface StatementService {

    void changeApplicationStatus(List<Application> applicationList, Faculty faculty);

    void rollbackApplicationStatus(List<Application> applicationsList);

    boolean changeApplicationStatusByQTY(List<Application> applicationsList);

    boolean isExist(List<Application> applicationsList);

    void sendToEmail(List<Application> applicationsList);

    boolean isSent(List<Application> applicationsList);
}
