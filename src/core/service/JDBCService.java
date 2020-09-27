package core.service;

import core.dao.*;
import core.model.*;
import core.utils.Utils;
import core.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCService {

    private CompanyDAO companyDAO;
    private ProjectOwnerDAO ownerDao;
    private ProjectDAO projectDAO;
    private StudentDAO studentDAO;
    private StudentPreferenceDAO preferenceDAO;
    private Validator validator;

    public JDBCService() {
        this.companyDAO = new CompanyDAO();
        this.ownerDao = new ProjectOwnerDAO();
        this.projectDAO = new ProjectDAO();
        this.studentDAO = new StudentDAO();
        this.preferenceDAO = new StudentPreferenceDAO();
        this.validator = new Validator(companyDAO, ownerDao,
                projectDAO, studentDAO, preferenceDAO);
    }

    public List<Project> findAllActiveProjects() {
        return projectDAO.findAllActive();
    }

    public boolean addCompany(Company company) {
        return companyDAO.insert(company);
    }

    public boolean addProjectOwner(ProjectOwner owner) {
        return ownerDao.insert(owner);
    }

    public boolean addProject(Project project) {
        return projectDAO.insert(project);
    }

    public boolean addStudent(Student student) {
        return studentDAO.insert(student);
    }

    public boolean updateStudent(Student student) {
        return studentDAO.update(student);
    }

    public boolean addPreference(StudentPreference preference) {

        int activeRows = preferenceDAO.countActive(preference.getsId());

        // deactivate all previous rows
        if (activeRows > 0) {
            preferenceDAO.deactiveRows(preference.getsId());
        }

        // insert new ones
        return preferenceDAO.insert(preference);
    }

    public boolean shortlistProjects(List<String> projectIDs) {
        return projectDAO.deactiveRows(projectIDs);
    }

    public List<StudentPreference> extractPreferenceList() {

        return preferenceDAO.findAllActive();
    }

    public Validator getValidator() {
        return validator;
    }
}
