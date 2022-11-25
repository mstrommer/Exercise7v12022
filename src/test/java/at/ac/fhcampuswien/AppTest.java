package at.ac.fhcampuswien;

import at.ac.fhcampuswien.generics.Lecturer;
import at.ac.fhcampuswien.generics.Person;
import at.ac.fhcampuswien.generics.Student;
import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(2)
class AppTest {

    @BeforeAll
    public static void init() {
        System.out.println("Testing Exercise 7");
    }

    @AfterAll
    public static void finish() {
        System.out.println("Finished Testing Exercise 7");
    }


    /**
     * Checkss for a specific generic Type given its String representation for convenience.
     * More details may be retrieved: http://tutorials.jenkov.com/java-reflection/generics.html
     * @param genericParameterType The Type Parameter of the generic function.
     * @param typeExpected The expected Type as String.
     * @return true if the types match. false otherwise.
     */
    private boolean checkType(Type genericParameterType, String typeExpected){
        if(genericParameterType instanceof ParameterizedType){
            ParameterizedType aType = (ParameterizedType) genericParameterType;
            return aType.getTypeName().equals(typeExpected);
        }
        return false;
    }

    @Test
    @SuppressWarnings("unchecked")
    public void intersection() {
        try {
            Method m = CollectionHelper.class.getMethod("intersection", List.class, List.class);
            boolean test = Arrays.stream(m.getGenericParameterTypes()).allMatch(type -> checkType(type, "java.util.List<java.lang.String>"));
            assertTrue(test,"Please check your Parameter and Type Parameter(s).");
            Type returnType = m.getGenericReturnType();
            test = checkType(returnType, "java.util.List<java.lang.String>");
            assertTrue(test,"Please check your return Type and its Type Parameter(s).");
            List<String> a = new ArrayList<>();
            Collections.addAll(a, "1", "2", "3");
            List<String> b = new ArrayList<>();
            Collections.addAll(b, "2", "3");
            List<String> intersection = (List<String>) m.invoke(null, a, b);
            Collections.sort(intersection);
            List<String> expected = new ArrayList<>();
            Collections.addAll(expected, "3", "2");
            Collections.sort(expected);
            assertEquals(expected, intersection, "Please check your intersection algorithm.");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There should be a method called intersection.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void symmetricDifference1() {
        try {
            Method m = CollectionHelper.class.getMethod("symmetricDifference", Set.class, Set.class);
            boolean test = Arrays.stream(m.getGenericParameterTypes()).allMatch(type -> checkType(type, "java.util.Set<java.lang.Number>"));
            assertTrue(test,"Please check your Parameter and Type Parameter(s).");
            Type returnType = m.getGenericReturnType();
            test = checkType(returnType, "java.util.Set<java.lang.Number>");
            assertTrue(test,"Please check your return Type and its Type Parameter(s).");
            Set<Number> a = new HashSet<>();
            Collections.addAll(a, 1, 2, 3);
            Set<Number> b = new HashSet<>();
            Collections.addAll(b, 2, 3);
            Set<Number> symDiff = (Set<Number>) m.invoke(null, a, b);
            Set<Number> expected = new HashSet<>();
            Collections.addAll(expected, 1);
            assertEquals(expected, symDiff, "Please check your symmetricDifference algorithm.");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There should be a method called symmetricDifference.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void symmetricDifference2() {
        try {
            Method m = CollectionHelper.class.getMethod("symmetricDifference", Set.class, Set.class);
            boolean test = Arrays.stream(m.getGenericParameterTypes()).allMatch(type -> checkType(type, "java.util.Set<java.lang.Number>"));
            assertTrue(test,"Please check your Parameter and Type Parameter(s).");
            Type returnType = m.getGenericReturnType();
            test = checkType(returnType, "java.util.Set<java.lang.Number>");
            assertTrue(test,"Please check your return Type and its Type Parameter(s).");
            Set<Number> a = new HashSet<>();
            Collections.addAll(a, 1.1, 2.2, 3.3);
            Set<Number> b = new HashSet<>();
            Collections.addAll(b, 2.2, 5.5);
            Set<Number> symDiff = (Set<Number>) m.invoke(null, a, b);
            Set<Number> expected = new HashSet<>();
            Collections.addAll(expected, 1.1, 3.3, 5.5);
            assertEquals(expected, symDiff, "Please check your symmetricDifference algorithm.");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There should be a method called symmetricDifference.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void listOfStudentsToMap() {
        try {
            // Generics Checkings
            Method m = Student.class.getMethod("listOfStudentsToMap", List.class);
            boolean test = Arrays.stream(m.getGenericParameterTypes()).allMatch(type -> checkType(type, "java.util.List<at.ac.fhcampuswien.generics.Student>"));
            assertTrue(test,"Please check your Parameter and Type Parameter(s).");
            Type returnType = m.getGenericReturnType();
            test = checkType(returnType, "java.util.Map<java.lang.Integer, at.ac.fhcampuswien.generics.Student>");
            assertTrue(test,"Please check your return Type and its Type Parameter(s).");
            // Prepare Testdata
            List<Student> students = new ArrayList<>();
            Student aStudent = new Student("Obi-Wan", 1);
            Student bStudent = new Student("Leia", 2);
            Student cStudent = new Student("Wedge", 3);
            Student dStudent = new Student("Rey", 4);
            Collections.addAll(students, aStudent, bStudent, cStudent, dStudent);
            Map<Integer, Student> integerStudentMap = (Map<Integer, Student>) m.invoke(null, students);
            // Assertions
            assertEquals(aStudent, integerStudentMap.get(1), "Please check your listOfStudentsToMap algorithm.");
            assertEquals(bStudent, integerStudentMap.get(2), "Please check your listOfStudentsToMap algorithm.");
            assertEquals(cStudent, integerStudentMap.get(3), "Please check your listOfStudentsToMap algorithm.");
            assertEquals(dStudent, integerStudentMap.get(4), "Please check your listOfStudentsToMap algorithm.");

        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There should be a method called listOfStudentsToMap.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }

    // check deletion of duplicates
    @Test
    @SuppressWarnings("unchecked")
    public void eraseDuplicatesInList1() {
        try {
            // Generics Checkings
            Method m = Student.class.getMethod("eraseDuplicatesInList", List.class);
            boolean test = Arrays.stream(m.getGenericParameterTypes()).allMatch(type -> checkType(type, "java.util.List<at.ac.fhcampuswien.generics.Student>"));
            assertTrue(test,"Please check your Parameter and Type Parameter(s).");
            Type returnType = m.getGenericReturnType();
            test = checkType(returnType, "java.util.List<at.ac.fhcampuswien.generics.Student>");
            assertTrue(test,"Please check your return Type and its Type Parameter(s).");
            // Prepare Testdata
            List<Student> students = new ArrayList<>();
            Student aStudent = new Student("Obi-Wan", 1);
            Student bStudent = new Student("Leia", 2);
            Student cStudent = new Student("Wedge", 3);
            Student dStudent = new Student("Rey", 4);
            Student fStudent = new Student("Leia", 2);
            Collections.addAll(students, bStudent, cStudent, dStudent, aStudent, fStudent);
            m.invoke(null, students);
            students = students.stream().sorted(Comparator.comparingInt(Student::getStudentID)).collect(Collectors.toList());

            // epxected
            List<Student> expected = new ArrayList<>();
            aStudent = new Student("Obi-Wan", 1);
            bStudent = new Student("Leia", 2);
            cStudent = new Student("Wedge", 3);
            dStudent = new Student("Rey", 4);
            Collections.addAll(expected, aStudent, bStudent, cStudent, dStudent);

            // Assertions
            assertEquals(expected, students, "Please check your eraseDuplicatesInList algorithm.");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There should be a method called eraseDuplicatesInList.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }

    // check list of duplicates
    @Test
    @SuppressWarnings("unchecked")
    public void eraseDuplicatesInList2() {
        try {
            // Generics Checkings
            Method m = Student.class.getMethod("eraseDuplicatesInList", List.class);
            boolean test = Arrays.stream(m.getGenericParameterTypes()).allMatch(type -> checkType(type, "java.util.List<at.ac.fhcampuswien.generics.Student>"));
            assertTrue(test,"Please check your Parameter and Type Parameter(s).");
            Type returnType = m.getGenericReturnType();
            test = checkType(returnType, "java.util.List<at.ac.fhcampuswien.generics.Student>");
            assertTrue(test,"Please check your return Type and its Type Parameter(s).");
            // Prepare Testdata
            List<Student> students = new ArrayList<>();
            Student aStudent = new Student("Obi-Wan", 1);
            Student bStudent = new Student("Leia", 2);
            Student cStudent = new Student("Wedge", 3);
            Student dStudent = new Student("Wedge", 3);
            Student fStudent = new Student("Leia", 2);
            Collections.addAll(students, bStudent, cStudent, dStudent, aStudent, fStudent);
            List<Student> duplicates = (List<Student>) m.invoke(null, students);
            duplicates = duplicates.stream().sorted(Comparator.comparingInt(Student::getStudentID)).collect(Collectors.toList());

            // epxected
            List<Student> expected = new ArrayList<>();
            aStudent = new Student("Leia", 2);
            bStudent = new Student("Wedge", 3);
            Collections.addAll(expected, aStudent, bStudent);

            // Assertions
            assertEquals(expected, duplicates, "Please check your eraseDuplicatesInList algorithm.");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There should be a method called eraseDuplicatesInList.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }

    @Test
    public void isPalindrome() {
        try {
            Method m = App.class.getMethod("isPalindrome", StringBuilder.class);
            assertTrue((boolean)m.invoke(null, new StringBuilder("Was it a car or a cat I saw")), "'Was it a car or a cat I saw' is actually a palindrome.");
            assertFalse((boolean)m.invoke(null, new StringBuilder("Baby Elephant")), "'Baby Elephant' is actually not a palindrome. Of course we wish.");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There should be a method called isPalindrome.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Problems might have occurred creating the Object. Also check return types.");
        }
    }

    @Test
    public void isAnagramNote() {
        try {
            Method m = Lecturer.class.getMethod("isAnagramNote", Lecturer.class);
            Constructor<?> co = Person.class.getConstructor(String.class, int.class, String.class);
            Lecturer l1 = (Lecturer) co.newInstance("Michael Strommer", 343423323, "The Millenium Falcon");
            Lecturer l2 = (Lecturer) co.newInstance("Leon Freudenthaler", 21122323, 0, "Me hell I malfunction");
            Lecturer l3 = (Lecturer) co.newInstance("Stefan Schmitzhofer", 642327885, 0, "Fulfill your destiny!");
            assertTrue((boolean)m.invoke(l1, l2), "Actually an anagram.");
            assertFalse((boolean)m.invoke(l1, l3), "Actually not an anagram.");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There should be a method called isAnagramNote.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Problems might have occurred creating the Object. Also check return types.");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void serializePerson() {
        try {
            // Generics Checkings
            Method m = Person.class.getMethod("serializePerson", Person.class, String.class);
            Person p = new Lecturer("Obi-Wan", 1, "");
            m.invoke(null, p, "build/resources/main/obiwan.ser");

            // Test
            Person expected = null;
            FileInputStream fis = null;
            ObjectInputStream in = null;
            try {
                fis = new FileInputStream("build/resources/main/obiwan.ser");
                in = new ObjectInputStream(fis);
                p = (Person)in.readObject();
                in.close();
            } catch(IOException ex) {
                fail("There is a Problem with the definition/implementation of method serializePerson.");
            } catch(ClassNotFoundException ex) {
                fail("There is a Problem with the definition/implementation of method serializePerson.");
            }
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There is a Problem with the definition/implementation of method serializePerson.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserializePerson() {
        try {
            // Generics Checkings
            Method m = Person.class.getMethod("deserializePerson", String.class);
            Person actual = null;
            Person expected = new Student("Luke", 2);

            // Test
            FileOutputStream fos = null;
            ObjectOutputStream out = null;
            try {
                fos = new FileOutputStream("build/resources/main/luke.ser");
                out = new ObjectOutputStream(fos);
                out.writeObject(expected);
                out.close();
            } catch(IOException ex) {
                fail("There is a Problem with the definition/implementation of method deserializePerson.");
            }
            actual = (Person) m.invoke(null, "build/resources/main/luke.ser");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There is a Problem with the definition/implementation of method deserializePerson.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void fileToList() {
        try {
            // Generics Checkings
            Method m = App.class.getMethod("fileToList", String.class);

            List<String> list = (List<String>) m.invoke(null, "build/resources/main/input.txt");

            // Expected
            List<String> expected = new ArrayList<>();
            Collections.addAll(expected, "There's a songbird who sings:",
                    "\"Sometimes all of our thoughts are misgiven.\"", "It makes me wonder",
                    "It makes me wonder", "There's a feeling I get", "When I look to the west,",
                    "And my spirit is crying for leaving.","In my thoughts I have seen",
                    "Rings of smoke through the trees","And the voices of those who stand looking.");

            // Assertions
            assertTrue(expected.equals(list), "There is a Problem with the definition/implementation of method fileToList.");
        } catch (NoSuchMethodException nsme){
            nsme.printStackTrace();
            fail("There is a Problem with the definition/implementation of method fileToList.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Some unexpected error has occurred. Please contact moodle support forum ;)");
        }
    }
}