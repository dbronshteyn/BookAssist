import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;


public class Main {

    public static final String AUTHOR = "a";
    public static final String BOOK = "b";
    public static final String CITE = "c";

    public static final String EXIT = "x";


    public static String deleteChar(String str) {
        String fixStr;
        fixStr = StringUtils.replaceEach(str, new String[]{"\"", "(", ")", ":", "A", " "}, new String[]{"", "", "", "", "", ""});
        return fixStr.toLowerCase();
    }

    public static boolean containStr(String[] arr, String str) {
        boolean conStr = true;
        for(String cont : arr) {
            if(str.contains(cont)) {
                conStr = false;
            }
        }
        return conStr;
    }

    public static List<String> removeDuplicates(List<String> strList) {
        Map<String, String> map = new LinkedHashMap<>();
        strList.forEach(item -> map.put(item.toLowerCase(), item));
        return new ArrayList<>(map.values());
    }


    public static void findSelection(int csvIndex, String author) throws IOException {
        String books = "src/books.csv";
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(books));

        ArrayList<String> booksFound = new ArrayList<>();
        String title = "";

        System.out.println("Reading CSV and sorting data...");
        while((line = br.readLine()) != null) {
            String[] contents = line.split("\";\"");
            if (contents[csvIndex].toLowerCase().contains(author.toLowerCase())) {
                title = contents[1];
                title = StringUtils.replaceEach(title, new String[]{"  ", "   ", "    ", ".", "&amp", ";"}, new String[]{" ", " ", "", " ", "and", ""});
                title = title.replaceAll(" \\(.*\\)", "");

                booksFound.add(title);
            }
        }

//        Set<String> set = new HashSet<>(booksFound);
//        booksFound.clear();
//        booksFound.addAll(set);

        booksFound = (ArrayList<String>) removeDuplicates(booksFound);


        if (booksFound.isEmpty()) {
            System.out.println("No books were found");
        }
        else {
            System.out.println("Here are the books that have been found:");


            for (String book : booksFound) {
                System.out.println(book);
            }
        }
    }



    public static void findAuthor() throws IOException {
        System.out.println("Please enter the author's name");
        Scanner input = new Scanner(System.in);
        String author = input.nextLine();

        findSelection(2, author);
    }

    public static void findBook() {

    }

    public static void getCitation() {
        // Ask for publisher location and date
    }

    public static void getExit() {
        System.out.println("Thank you for using the application");
        System.exit(0);
    }




    public static void main(String[] args) throws IOException {


        Scanner scan = new Scanner(System.in);

        System.out.println("Hello and welcome BookAssist");
        System.out.println("With this application you can search for books, authors, and create citations");
        System.out.println("There are over 200,000 books to chose from!");

        boolean properEnter = false;
        do {
            System.out.printf("What would you like to search for [%s] author, [%s] book, [%s] create citation, [%s] to exit.\n", AUTHOR, BOOK, CITE, EXIT);
            String decision = scan.nextLine().toLowerCase();
            if (decision.equals(AUTHOR)) {
                findAuthor();
            }
            else if (decision.equals(BOOK)) {

            }
            else if (decision.equals(CITE)) {

            }
            else if (decision.equals(EXIT)) {
                getExit();
            }
            else System.out.println("Please enter either your selection based on the text inside the brackets");
        } while(properEnter == false);



        }
    }