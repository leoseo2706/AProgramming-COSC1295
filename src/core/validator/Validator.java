package core.validator;

import core.constant.Constants;
import core.dao.*;
import core.exception.CustomException;
import core.model.CharacteristicEnum;
import core.model.GradeEnum;
import core.model.PreferenceSkillEnum;
import core.utils.Utils;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private CompanyDAO companyDAO;
    private ProjectOwnerDAO ownerDao;
    private ProjectDAO projectDAO;
    private StudentDAO studentDAO;
    private StudentPreferenceDAO preferenceDAO;

    public Validator(CompanyDAO companyDAO, ProjectOwnerDAO ownerDao,
                     ProjectDAO projectDAO, StudentDAO studentDAO,
                     StudentPreferenceDAO preferenceDAO) {
        this.companyDAO = companyDAO;
        this.ownerDao = ownerDao;
        this.projectDAO = projectDAO;
        this.studentDAO = studentDAO;
        this.preferenceDAO = preferenceDAO;
    }

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean isValidEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public String validateCompanyID(Scanner sc, boolean expectedExistence) {

        String companyID = sc.nextLine();

        // validate blank
        if (Utils.isBlank(companyID)) {
            System.out.print("Empty company ID. Please re-enter: ");
            return validateCompanyID(sc, expectedExistence);
        }

        // validate existence
        if (companyDAO.isExisted(companyID) != expectedExistence) {
            System.out.print(Utils.format("Company ID {0} {1}existed. Please re-enter: ",
                    companyID, expectedExistence ? "non-" : Constants.EMPTY));
            return validateCompanyID(sc, expectedExistence);
        }

        return companyID;

    }

    public String validateOwnerID(Scanner sc, boolean expectedExistence) {

        String ownerID = sc.nextLine();

        // validate blank
        if (Utils.isBlank(ownerID)) {
            System.out.print("Empty project owner ID. Please re-enter: ");
            return validateOwnerID(sc, expectedExistence);
        }

        // validate existence
        if (ownerDao.isExisted(ownerID) != expectedExistence) {
            System.out.print(Utils.format("Project owner ID {0} {1}existed. Please re-enter: ",
                    ownerID, expectedExistence ? "non-" : Constants.EMPTY));
            return validateOwnerID(sc, expectedExistence);
        }

        return ownerID;

    }

    public String validateProjectID(Scanner sc, boolean expectedExistence) {

        String projectID = sc.nextLine();

        // validate blank
        if (Utils.isBlank(projectID)) {
            System.out.print("Empty project ID. Please re-enter: ");
            return validateProjectID(sc, expectedExistence);
        }

        // validate existence
        if (projectDAO.isExisted(projectID) != expectedExistence) {
            System.out.print(Utils.format("Project ID {0} {1}existed. Please re-enter: ",
                    projectID, expectedExistence ? "non-" : Constants.EMPTY));
            return validateProjectID(sc, expectedExistence);
        }

        return projectID;

    }

    public String validatePreferenceID(Map<String, Integer> preferenceIDs,
                                       Scanner sc, boolean expectedExistence) {

        String projectID = validateProjectID(sc, expectedExistence);

        // validate duplicate
        if (preferenceIDs.keySet().contains(projectID)) {
            System.out.print(Utils.format("You have entered this project ID {0} already." +
                    " Please re-enter:", projectID));
            return validatePreferenceID(preferenceIDs, sc, expectedExistence);
        }

        return projectID;

    }

    public String validateStudentID(Scanner sc, boolean expectedExistence) {

        String studentID = sc.nextLine();

        // validate blank
        if (Utils.isBlank(studentID)) {
            System.out.print("Empty student ID. Please re-enter: ");
            return validateStudentID(sc, expectedExistence);
        }

        // validate existence
        if (studentDAO.isExisted(studentID) != expectedExistence) {
            System.out.print(Utils.format("Student ID {0} {1}existed. Please re-enter: ",
                    studentID, expectedExistence ? "non-" : Constants.EMPTY));
            return validateStudentID(sc, expectedExistence);
        }

        return studentID;

    }

    public String validateStudentIDSelf(Scanner sc, boolean expectedExistence, String selfID) {
        String studentID = validateStudentID(sc, expectedExistence);

        // validate if duplicated with self id
        if (studentID.equals(selfID)) {
            System.out.print(Utils.format("Student ID {0} cannot be conflicted with him/herself." +
                    " Please re-enter: ", studentID));
            return validateStudentIDSelf(sc, expectedExistence, selfID);
        }

        return studentID;
    }

    public String validateEmail(Scanner sc) {

        String email = sc.nextLine();

        // validate blank
        if (Utils.isBlank(email)) {
            System.out.print("Empty email. Please re-enter: ");
            return validateEmail(sc);
        }

        // validate email format
        if (!isValidEmail(email)) {
            System.out.print("Incorrect email format. Please re-enter: ");
            return validateEmail(sc);
        }

        return email;

    }

    public int validateGrade(Scanner sc) {
        int grade = Utils.getNumber(sc);
        if (GradeEnum.lookup(grade) == null) {
            System.out.println("Incorrect range. Please check valid values below.");
            GradeEnum.getMap().forEach((k, v) -> {
                System.out.println(Utils.format("{0}: {1}",
                        v.getDescription(), String.valueOf(v.getScore())));
            });
            System.out.print("Enter: ");
            return validateGrade(sc);
        }

        return grade;
    }

    public int validatePreferenceSkill(Scanner sc) {
        int preferenceSkill = Utils.getNumber(sc);
        if (PreferenceSkillEnum.lookup(preferenceSkill) == null) {
            System.out.println("Incorrect range. Please check valid values below.");
            PreferenceSkillEnum.getMap().forEach((k, v) -> {
                System.out.println(Utils.format("{0}: {1}",
                        v.name(), String.valueOf(v.getPreference())));
            });
            System.out.print("Enter: ");
            return validatePreferenceSkill(sc);
        }

        return preferenceSkill;
    }

    public String validatePersonality(Scanner sc) {
        String personality = sc.nextLine();
        if (CharacteristicEnum.lookup(personality.toUpperCase()) == null) {
            System.out.println("Incorrect range. Please check valid values below.");
            CharacteristicEnum.getMap().forEach((k, v) -> {
                System.out.println(Utils.format("{0} ({1}): {2}",
                        v.getDescription(), v.name(), v.getCh()));
            });
            System.out.print("Enter: ");
            return validatePersonality(sc);
        }

        int allowableThreshold = getAllowableThreshold();
        if (studentDAO.countByPersonality(personality) >= allowableThreshold) {
            System.out.print(Utils.format("Maximum threshold {0} for {1} reached. Please re-enter: ",
                    String.valueOf(allowableThreshold), personality));
            return validatePersonality(sc);
        }

        return personality;
    }

    private int getAllowableThreshold() {

        try {
            int maxStudent = Utils.getIntProp(Constants.MAX_STUDENT_KEY);
            int maxPersonality = Utils.getIntProp(Constants.MAX_PERSONALITY_KEY);
            return Math.round(maxStudent/maxPersonality);
        } catch (CustomException e) {
            // ignore
        }

        return Constants.INVALID;
    }
}
