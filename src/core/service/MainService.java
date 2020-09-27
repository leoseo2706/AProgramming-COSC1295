package core.service;

import core.constant.Constants;
import core.model.*;
import core.utils.Utils;
import core.validator.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainService {

    private JDBCService jdbcService;
    private Validator validator;

    public MainService() {
        jdbcService = new JDBCService();
        validator = jdbcService.getValidator();
    }

    public void addCompany(Scanner sc) {

        System.out.print("Company ID: ");
        String id = validator.validateCompanyID(sc, false);

        System.out.print("Company Name: ");
        String name = sc.nextLine();

        System.out.print("Company ABN: ");
        String abn = sc.nextLine();

        System.out.print("Company URL: ");
        String url = sc.nextLine();

        System.out.print("Company Address: ");
        String address = sc.nextLine();

        Company company = new Company(id, name, abn, url, address);

        Utils.checkAndPrint(jdbcService.addCompany(company));

    }

    public void addProjectOwner(Scanner sc) {

        System.out.print("Project Owner ID: ");
        String id = validator.validateOwnerID(sc, false);

        System.out.print("First Name: ");
        String fName = sc.nextLine();

        System.out.print("Surname: ");
        String sName = sc.nextLine();

        System.out.print("Role: ");
        String role = sc.nextLine();

        System.out.print("Email: ");
        String email = validator.validateEmail(sc);

        System.out.print("Company ID: ");
        String companyId = validator.validateCompanyID(sc, true);

        ProjectOwner owner = new ProjectOwner(id, fName, sName, role, email, companyId);
        Utils.checkAndPrint(jdbcService.addProjectOwner(owner));

    }

    public void addProject(Scanner sc) {

        System.out.print("Project ID: ");
        String id = validator.validateProjectID(sc, false);

        System.out.print("Project Title: ");
        String title = sc.nextLine();

        System.out.print("Project Description: ");
        String description = sc.nextLine();

        System.out.print("Project Owner ID: ");
        String ownerId = validator.validateOwnerID(sc, true);

        System.out.print("Programming & Software Engineering Skill: ");
        int skillP = validator.validatePreferenceSkill(sc);

        System.out.print("Networking & Security Skill: ");
        int skillN = validator.validatePreferenceSkill(sc);

        System.out.print("Analytics & Big Data Skill: ");
        int skillA = validator.validatePreferenceSkill(sc);

        System.out.print("Web & Mobile Application Skill: ");
        int skillW = validator.validatePreferenceSkill(sc);

        Project project = new Project(id, title, description, ownerId, skillP, skillN, skillA, skillW);

        Utils.checkAndPrint(jdbcService.addProject(project));
    }

    public void captureStudentPersonalities(Scanner sc) {

        System.out.print("Student ID: ");
        String id = validator.validateStudentID(sc, true);

        System.out.print("Personality: ");
        String personality = validator.validatePersonality(sc);

        System.out.print("Conflicted Student 1: ");
        String cStudent1 = validator.validateStudentIDSelf(sc, true, id);

        System.out.print("Conflicted Student 2: ");
        String cStudent2 = validator.validateStudentIDSelf(sc, true, id);

        Student student = new Student(id, personality, cStudent1, cStudent2);
        Utils.checkAndPrint(jdbcService.updateStudent(student));
    }

    public void addStudentPreference(Scanner sc) {

        System.out.print("Student ID: ");
        String sid = validator.validateStudentID(sc, true);

        StudentPreference preference = new StudentPreference(sid);
        Map<String, Integer> scores = preference.getPreferences();

        int preferenceSize = 4;
        System.out.println(Utils.format("Enter {0} project preference below",
                String.valueOf(preferenceSize)));
        for (int i = 0 ; i< preferenceSize; i++) {

            System.out.print("Project ID: ");
            String pid = validator.validatePreferenceID(scores, sc, true);

            System.out.print("Preference Score: ");
            int score = validator.validatePreferenceSkill(sc);

            scores.put(pid, score);
        }

        Utils.checkAndPrint(jdbcService.addPreference(preference));
    }

    public void shortlistProject() {

        int shortListThreshold = 5;
        List<Project> projects = jdbcService.findAllActiveProjects();
        int projectSize = projects.size();
        if (projectSize < shortListThreshold) {
            System.out.println(Utils.format("There are only {0} projects, less than {1}!",
                    String.valueOf(projectSize), String.valueOf(shortListThreshold)));
            return;
        }

        // extract and convert to map
        Map<String, Integer> preferenceMap = jdbcService.extractPreferenceList().stream()
                .collect(Collectors.groupingBy(StudentPreference::getpId,
                        Collectors.summingInt(StudentPreference::getScore)));

        // update summed preference info and sort
        List<Project> updated = projects.stream().map(p -> {
            String projectID = p.getId();
            Integer sumPreference = preferenceMap.get(projectID);
            p.setSumPreference(sumPreference != null ? sumPreference : 0);
            return p;
        }).sorted(Comparator.comparingInt(Project::getSumPreference))
                .collect(Collectors.toList());
        System.out.println("Shortlist project list information:");
        updated.forEach(u -> System.out.println(u.toString()));

        List<String> leastProjectIds = IntStream.range(0, projects.size())
                .filter(idx -> idx < shortListThreshold)
                .mapToObj(idx -> updated.get(idx).getId())
                .collect(Collectors.toList());

        System.out.println("The following project shall be removed based on the info above");
        leastProjectIds.forEach(p -> {
            System.out.println(p);
        });

        Utils.checkAndPrint(jdbcService.shortlistProjects(leastProjectIds));

        // sorting in desc
//        preferenceMap = preferenceMap.entrySet().stream()
//                .sorted(Map.Entry.<String, Integer>comparingByValue())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (o1, o2) -> o1, LinkedHashMap::new));
//
//        AtomicInteger index = new AtomicInteger(0);
//        preferenceMap.entrySet().removeIf(e -> index.getAndIncrement() < shortListSize);

    }

    public void showMenu() {
        System.out.println("====== Welcome To The Program ======");
        System.out.println("Please select options from menu below.");
        System.out.println("A. Add Company");
        System.out.println("B. Add Project Owner");
        System.out.println("C. Add Project");
        System.out.println("D. Capture Student Personalitites");
        System.out.println("E. Add Student Preferences");
        System.out.println("F. Shortlist Projects");
        System.out.println("G. Forms Teams");
        System.out.println("H. Display Team Fitness Metrics");
        System.out.println("Q: Exit");
        System.out.print("Your choice is: ");
    }
}
