//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MovieTreeTester
// Course: CS 300 Spring 2021
//
// Author: Ethan Geoffrey Wijaya
// Email: egwijaya@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * MovieTree.
 *
 */
public class MovieTreeTester {

  /**
   * Checks the correctness of the implementation of both addMovie() and toString() methods
   * implemented in the MovieTree class. This unit test considers at least the following scenarios.
   * (1) Create a new empty MovieTree, and check that its size is 0, it is empty, and that its
   * string representation is an empty string "". (2) try adding one movie and then check that the
   * addMovie() method call returns true, the tree is not empty, its size is 1, and the .toString()
   * called on the tree returns the expected output. (3) Try adding another movie which is smaller
   * that the movie at the root, (4) Try adding a third movie which is greater than the one at the
   * root, (5) Try adding at least two further movies such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * movie with respect to year, rating, and then name. (6) Try adding a movie already stored in the
   * tree. Make sure that the addMovie() method call returned false, and that the size of the tree
   * did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddMovieToStringSize() {
    MovieTree bst = new MovieTree();

    // 1) Empty MovieTree tests
    if (bst.size() != 0) {
      System.out.println("testAddMovieToStringSize error 1.1| Size should be 0 for empty list");
      return false;
    }
    if (!bst.isEmpty()) {
      System.out.println(
          "testAddMovieTOStringSize error 1.2| isEmpty() should return true for " + "empty list");
      return false;
    }
    if (!bst.toString().equals("")) {
      System.out.println("testAddMovieToStringSize error 1.3| toString() should return an empty "
          + "string if list is empty.");
      return false;
    }

    // 2) addMovie test 1
    if (!bst.addMovie(new Movie(2000, 1.0, "1"))) {
      System.out.println("Error 2.1| Movie not added properly or incorrectly returned false.");
      return false;
    }
    if (bst.size() != 1) {
      System.out.println("Error 2.2| Size should be 1 but is " + bst.size());
      return false;
    }
    if (bst.isEmpty()) {
      System.out.println("Error 2.3| isEmpty() should not return true if list has entries.");
      return false;
    }
    if (!bst.toString().equals("[(Year: 2000) (Rate: 1.0) (Name: 1)]\n")) {
      System.out.print("Error 2.4| Movie added incorrectly. Expected: [(Year: 2000) (Rate: 1.0) "
          + "(Name: 1)]\n Actual: " + bst);
      return false;
    }

    // 3) addMovie test 2. Generic left/right test.
    if (!bst.addMovie(new Movie(1999, 1.0, "2"))) {
      System.out.println("Error 3.1| Movie not added properly or incorrectly returned false.");
      return false;
    }
    if (!bst.addMovie(new Movie(2001, 1.0, "3"))) {
      System.out.println("Error 3.2| Movie not added properly or incorrectly returned false.");
      return false;
    }
    if (bst.size() != 3) {
      System.out.println("Error 3.3| Size should be 3 but is " + bst.size());
      return false;
    }
    if (!bst.toString().equals("[(Year: 1999) (Rate: 1.0) (Name: 2)]\n"
        + "[(Year: 2000) (Rate: 1.0) (Name: 1)]\n" + "[(Year: 2001) (Rate: 1.0) (Name: 3)]\n")) {
      System.out.println(
          "Error 3.4| Movie added incorrectly. Expected: [(Year: 1999) (Rate: 1.0) (Name: 2)]\n"
              + "[(Year: 2000) (Rate: 1.0) (Name: 1)]\n"
              + "[(Year: 2001) (Rate: 1.0) (Name: 3)]\n Actual: " + bst);
      return false;
    }

    // 4) addMovie test 3. Left/right test for movies with same year/rating
    if (!bst.addMovie(new Movie(1999, 2.0, "4"))) {
      System.out.println("Error 4.1| Movie not added properly or incorrectly returned false.");
      return false;
    }
    if (!bst.addMovie(new Movie(1999, 2.0, "5"))) {
      System.out.println("Error 4.2| Movie not added properly or incorrectly returned false.");
      return false;
    }
    if (bst.size() != 5) {
      System.out.println("Error 4.3| Size should be 5 but is " + bst.size());
      return false;
    }
    if (!bst.toString()
        .equals("[(Year: 1999) (Rate: 1.0) (Name: 2)]\n" + "[(Year: 1999) (Rate: 2.0) (Name: 4)]\n"
            + "[(Year: 1999) (Rate: 2.0) (Name: 5)]\n" + "[(Year: 2000) (Rate: 1.0) (Name: 1)]\n"
            + "[(Year: 2001) (Rate: 1.0) (Name: 3)]\n")) {
      System.out.println("Error 4.4| Movies added incorrectly. Expected: "
          + "[(Year: 1999) (Rate: 1.0) (Name: 2)]\n" + "[(Year: 1999) (Rate: 2.0) (Name: 4)]\n"
          + "[(Year: 1999) (Rate: 2.0) (Name: 5)]\n" + "[(Year: 2000) (Rate: 1.0) (Name: 1)]\n"
          + "[(Year: 2001) (Rate: 1.0) (Name: 3)]\nActual " + bst);
      return false;
    }

    // 5) duplicate movie test
    if (bst.addMovie(new Movie(2000, 1.0, "1"))) {
      System.out.println("Error 5.1| Duplicate movie should not be succesfully added to list");
      return false;
    }
    if (bst.size() != 5) {
      System.out.println("Error 5.2| Size should not be changed after failed addMovie() call.");
      return false;
    }

    return true;
  }

  /**
   * This method checks mainly for the correctness of the MovieTree.contains() method. It must
   * consider at least the following test scenarios. (1) Create a new MovieTree. Then, check that
   * calling the contains() method on an empty MovieTree returns false. (2) Consider a MovieTree of
   * height 3 which contains at least 5 movies. Then, try to call contains() method to search for
   * the movie having a match at the root of the tree. (3) Then, search for a movie at the right and
   * left subtrees at different levels considering successful and unsuccessful search operations.
   * Make sure that the contains() method returns the expected output for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    MovieTree bst = new MovieTree();

    // 1) empty tree test
    if (bst.contains(2000, 9, "o")) {
      System.out.println("Error 1| contains() should not be true for empty list.");
      return false;
    }

    // 2) contains root test
    bst.addMovie(new Movie(2001, 5, "a"));
    bst.addMovie(new Movie(2002, 5, "b"));
    bst.addMovie(new Movie(1998, 7, "c"));
    bst.addMovie(new Movie(2001, 9, "d"));
    bst.addMovie(new Movie(2004, 8, "e"));
    if (!bst.contains(2001, 5, "a")) {
      System.out.println("Error 2| Root node not detected by contains()");
      return false;
    }

    // 3) True contains tests
    if (!bst.contains(1998, 7, "c")) {
      System.out.println("Error 3.1| contains() method failed for valid node.");
      return false;
    }
    if (!bst.contains(2004, 8, "e")) {
      System.out.println("Error 3.2| contains() method failed for valid node.");
      return false;
    }
    if (!bst.contains(2002, 5, "b")) {
      System.out.println("Error 3.3| contains() method failed for valid node.");
      return false;
    }

    // 4) False contains tests
    if (bst.contains(1998, 7, "lol")) {
      System.out.println("Error 4.1| contains() method returned true for invalid node.");
      return false;
    }
    if (bst.contains(2004, 7, "e")) {
      System.out.println("Error 4.2| contains() method returned true for invalid node.");
      return false;
    }
    if (bst.contains(2015, 7, "a")) {
      System.out.println("Error 4.1| contains() method returned true for invalid node.");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty movie tree is zero. (2) ensures that
   * the height of a tree which consists of only one node is 1. (3) ensures that the height of a
   * MovieTree with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*) (*) /
   * (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    MovieTree bst = new MovieTree();

    // 1) empty tree test
    if (bst.height() != 0) {
      System.out.println("Error 1| Height should be 0 for empty tree.");
      return false;
    }

    // 2) single node test
    bst.addMovie(new Movie(2002, 8, "Spider-man"));
    if (bst.height() != 1) {
      System.out.println("Error 2| Incorrect height. Expected: 1. Actual: " + bst.height());
      return false;
    }

    // 3) general height test 1
    bst.addMovie(new Movie(1998, 8.5, "Die Hard"));
    bst.addMovie(new Movie(2001, 9.0, "The Fellowship of the Ring"));
    bst.addMovie(new Movie(2006, 9, "The Departed"));
    if (bst.height() != 3) {
      System.out.println("Error 3| Incorrect height. Expected: 3. Actual: " + bst.height());
      return false;
    }

    // 4) general height test 2
    bst.addMovie(new Movie(2018, 9.5, "Hereditary"));
    bst.addMovie(new Movie(2008, 9.2, "The Dark Knight"));
    bst.addMovie(new Movie(2003, 10, "The Room"));
    if (bst.height() != 4) {
      System.out.println("Error 4| Incorrect height. Expected: 4. Actual: " + bst.height());
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.getBestMovie() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestMovie() {
    MovieTree bst = new MovieTree();

    // 1) Unexpected exception test
    try {
      bst.getBestMovie();
    } catch (Exception e) {
      System.out.println("Error 1| getBestMovie() should not throw any exception.");
      return false;
    }
    
    // 2) Empty tree test
    if (bst.getBestMovie() != null) {
      System.out.println("Error 2| getBestMovie() should return null for empty list.");
      return false;
    }
    // 3) getBestMovie test 1
    bst.addMovie(new Movie(2000, 8, "Unbreakable"));
    bst.addMovie(new Movie(1974, 9.7, "The Godfather Part 2"));
    bst.addMovie(new Movie(2004, 10, "The SpongeBob SquarePants Movie"));
    if (!bst.getBestMovie().equals(new Movie(2004, 10, "The SpongeBob SquarePants Movie"))) {
      System.out.println("Error 3| getBestMovie() returned incorrect value. Expected: "
          + "[(Year: 2004) (Rate: 10.0) (Name: The SpongeBob SquarePants Movie)]\nActual: "
          + bst.getBestMovie());
      return false;
    }

    // 4) getBestMovie test 2
    bst.addMovie(new Movie(2010, 8.8, "Inception"));
    bst.addMovie(new Movie(1994, 9.5, "The Shawshank Redemption"));
    bst.addMovie(new Movie(2015, 10, "The SpongeBob Movie: Sponge Out of Water"));
    if (!bst.getBestMovie()
        .equals(new Movie(2015, 10, "The SpongeBob Movie: Sponge Out of Water"))) {
      System.out.println("Error 4| getBestMovie() returned incorrect value. Expected: "
          + "[(Year: 2015) (Rate: 10.0) (Name: The SpongeBob Movie: Sponge Out of Water)]\nActual: "
          + bst.getBestMovie());
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.lookup() method. This test must consider at least 3
   * test scenarios. (1) Ensures that the MovieTree.lookup() method throws a NoSuchElementException
   * when called on an empty tree. (2) Ensures that the MovieTree.lookup() method returns an array
   * list which contains all the movies satisfying the search criteria of year and rating, when
   * called on a non empty movie tree with one match, and two matches and more. Vary your search
   * criteria such that the lookup() method must check in left and right subtrees. (3) Ensures that
   * the MovieTree.lookup() method throws a NoSuchElementException when called on a non-empty movie
   * tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    MovieTree bst = new MovieTree();
    ArrayList<Movie> list = new ArrayList<>();

    // 1) empty tree test
    try {
      bst.lookup(2000, 5.0);
      System.out.println("Error 1.1| No exception thrown when lookup() called on empty tree.");
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      System.out.println("Error 1.2| Wrong exception thrown when lookup() called on empty tree. "
          + "Exception details: " + e.getMessage());
      return false;
    }

    // 2) single value tree test
    bst.addMovie(new Movie(2000, 5, "1"));
    list = bst.lookup(2000, 5);
    if (!list.get(0).equals(new Movie(2000, 5, "1"))) {
      System.out.println("Error 2.1| Movies not added properly. Expected: "
          + "[[(Year: 2000) (Rate: 1.0) (Name: 1)]]\nActual: " + list);
      return false;
    }

    // 3) normal scenario lookup test
    bst.addMovie(new Movie(1970, 8, "1"));
    bst.addMovie(new Movie(2003, 4, "1"));
    bst.addMovie(new Movie(2000, 4, "1"));
    bst.addMovie(new Movie(2000, 2, "1"));
    bst.addMovie(new Movie(1988, 7, "1"));
    bst.addMovie(new Movie(2000, 10, "1"));
    bst.addMovie(new Movie(1994, 5, "1"));
    bst.addMovie(new Movie(2000, 1, "1"));
    bst.addMovie(new Movie(2001, 3, "1"));
    list = bst.lookup(2000, 4);
    ArrayList<Movie> expected = new ArrayList<>();
    expected.add(new Movie(2000, 5, "1"));
    expected.add(new Movie(2000, 4, "1"));
    expected.add(new Movie(2000, 10, "1"));
    if (!list.equals(expected)) {
      System.out.println(
          "Error 2.2| Movies not added properly. Expected: " + expected + "\nActual:" + " " + list);
      return false;
    }

    // 4) test for movie requirements not found
    try {
      list = bst.lookup(2001, 8);
      System.out.println("Error 4.1| No exception thrown when movie not found.");
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      System.out.println(
          "Error 4.2| Wrong exception thrown when movie not found. Details: " + e.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddMovieToStringSize(): " + testAddMovieToStringSize());
    System.out.println("testContains(): " + testContains());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetBestMovie(): " + testGetBestMovie());
    System.out.println("testLookup(): " + testLookup());
    /*
     * MovieTree bst = new MovieTree(); System.out.println("Size: " + bst.size() + " Height: " +
     * bst.height() + "\nCatalog:"); System.out.println(bst); bst.addMovie(new Movie(2018, 6.5,
     * "Airplanes")); bst.addMovie(new Movie(1988, 9.5, "Best"));
     * System.out.println("==============================================================");
     * System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
     * System.out.println(bst); bst.addMovie(new Movie(2018, 8.5, "Cats")); bst.addMovie(new
     * Movie(2018, 6.0, "Yes")); bst.addMovie(new Movie(2017, 5.5, "Dogs")); bst.addMovie(new
     * Movie(2018, 7.5, "Earth")); bst.addMovie(new Movie(2018, 6.0, "Flights")); bst.addMovie(new
     * Movie(2015, 8.5, "Grand Parents"));
     * System.out.println("==============================================================");
     * System.out.println("Size: " + bst.size() + " Height: " + bst.height() + "\nCatalog:");
     * System.out.println(bst); try {
     * System.out.println("This catalog contains (2018, 7.5, Earth): " + bst.contains(2018, 7.5,
     * "Earth")); System.out.println("This catalog contains (2016, 8.4, Flowers): " +
     * bst.contains(2018, 7.5, "Flowers")); System.out.println(); System.out.println("Best movie: "
     * + bst.getBestMovie()); System.out.println();
     * System.out.println("Lookup query: search for the movies of 2018 rated 6.5 and higher");
     * System.out.println(bst.lookup(2018, 6.5)); System.out.println();
     * System.out.println("Lookup query: search for the movies of 2018 with rated 8.0 and higher");
     * System.out.println(bst.lookup(2018, 8.0)); System.out.println();
     * System.out.println("Lookup query: search for the movies of 2015 with rated 9.0 and higher");
     * System.out.println(bst.lookup(2015, 9.0)); } catch (NoSuchElementException e) {
     * System.out.println(e.getMessage());
     * 
     * }
     */
  }

}
