//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MovieTree
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
 * This class creates a binary search tree using movie objects. Must contain BSTNodes with instances
 * of Movie as data
 * 
 * @author Ethan
 *
 */
public class MovieTree {
  private BSTNode<Movie> root; // root of this movie BST
  private int size; // size of this movie tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this MovieTree is empty, false otherwise
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * Returns the number of movies stored in this BST.
   * 
   * @return the size of this MovieTree
   */
  public int size() {
    return size;
  }


  /**
   * Adds a new movie to this MovieTree
   * 
   * @param newMovie a new movie to add to this BST.
   * @return true if the newMovie was successfully added to this BST, and returns false if there is
   *         a match with this movie already stored in this BST.
   */
  public boolean addMovie(Movie newMovie) {
    if (isEmpty()) { // Add newMovie to an empty MovieTree
      root = new BSTNode<Movie>(newMovie);
      size++;
      return true;
    } else {
      boolean success = addMovieHelper(newMovie, root);
      if (success) {
        size++;
      }
      return success;
    }
  }

  /**
   * Recursive helper method to add a new movie to a MovieTree rooted at current.
   * 
   * @param current  The "root" of the subtree we are inserting new movie into.
   * @param newMovie The movie to be added to a BST rooted at current.
   * @return true if the newMovie was successfully added to this MovieTree, false otherwise
   */
  protected static boolean addMovieHelper(Movie newMovie, BSTNode<Movie> current) {
    boolean right = false; // Not necessary but easier for me to work with to determine whether 
                           //left or right should be searched

    if (newMovie.compareTo(current.getData()) > 0) {
      right = true;//If newMovie is greater than current, search the right child node
    } else if (newMovie.compareTo(current.getData()) == 0) {
      return false;//Duplicate movie check
    }

    if (right) {
      if (current.getRight() == null) {
        current.setRight(new BSTNode<Movie>(newMovie));//Adds movie in location if empty
        return true;
      } else {//Else uses right node as current for recursive call to search for empty nodes
        return addMovieHelper(newMovie, current.getRight());
      }
    } else {
      if (current.getLeft() == null) {
        current.setLeft(new BSTNode<Movie>(newMovie));//Adds movie in location if empty
        return true;
      } else {//Else uses legt node as current for recursive call to search for empty nodes
        return addMovieHelper(newMovie, current.getLeft());
      }
    }

  }

  /**
   * Returns a String representation of all the movies stored within this BST in the increasing
   * order, separated by a newline "\n". For instance
   * 
   * "[(Year: 1988) (Rate: 7.0) (Name: Light Years)]" + "\n" + "[(Year: 2015) (Rate: 6.5) (Name:
   * Cinderella)]" + "\n"
   * 
   * @return a String representation of all the movies stored within this BST sorted in an
   *         increasing order with respect to the result of Movie.compareTo() method (year, rating,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a MovieTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current movie within this BST (root of a subtree)
   * @return a String representation of all the movies stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Movie.compareTo() method (year, rating,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Movie> current) {
    String newStr = "";
    if (current == null) {
      return "";
    }

    if (current.getLeft() != null) {//Goes to the bottom leftmost leaf node for smallest value
      newStr += toStringHelper(current.getLeft());
    }
    newStr += current.getData().toString() + "\n";
    //Adds parent after left child/Adds current if leaf node
    if (current.getRight() != null) {//Searches right child values
      newStr += toStringHelper(current.getRight());
    }
    return newStr;

  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a MovieTree (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Movie> current) {
    if (current == null) {
      return 0;
    }
    int leftHeight = heightHelper(current.getLeft());
    int rightHeight = heightHelper(current.getRight());
    int max;
    //Compares different accumulated heights in all levels until children of the root node reached
    //for final comparison
    if (leftHeight >= rightHeight) {
      max = leftHeight;
    } else {
      max = rightHeight;
    }
    return 1 + max;
  }

  /**
   * Checks whether this MovieTree contains a movie given its name, production year, and rating.
   * 
   * @param year   year of production of the movie to search
   * @param rating rating of the movie to search
   * @param name   name of the movie to search
   * @return true if there is a match with this movie in this BST, and false otherwise
   */
  public boolean contains(int year, double rating, String name) {
    return containsHelper(new Movie(year, rating, name), root);
  }

  /**
   * Recursive helper method to search whether there is a match with a given movie in the subtree
   * rooted at current
   * 
   * @param target  a reference to a movie we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected boolean containsHelper(Movie target, BSTNode<Movie> current) {
    if (current == null) {
      return false;
    }
    //Searches specifically for target using BST properties
    if (current.getData().equals(target)) {
      return true;

    } else if (target.compareTo(current.getData()) < 0) {
      return containsHelper(target, current.getLeft());

    } else {

      return containsHelper(target, current.getRight());
    }
  }


  /**
   * Gets the best (maximum) movie in this BST
   * 
   * @return the best (largest) movie (the most recent, highest rated, and having the largest name)
   *         in this MovieTree, and null if this tree is empty.
   */
  public Movie getBestMovie() {
    BSTNode<Movie> current = root;
    if (isEmpty()) {//The only method where isEmpty can be implemented as its not recursive
      return null;
    }
    //Iterative method is way simpler and more concise compared to recursive, which is why I used it
    while (current.getRight() != null) {
      current = current.getRight();//Simply searches for rightmost leaf node
    }
    return current.getData();
  }


  /**
   * Search for movies given the year and minimum rating as lookup key.
   * 
   * @param year   production year of a movie
   * @param rating rating value of a movie
   * @return a list of movies whose year equals our lookup year key and having a rating greater or
   *         equal to a given rating.
   * @throws a NoSuchElementException with a descriptive error message if there is no Movie found in
   *           this BST having the provided year and a rating greater or equal to the provided
   *           rating
   */
  public ArrayList<Movie> lookup(int year, double rating) {
    ArrayList<Movie> movies = new ArrayList<>();

    lookupHelper(year, rating, root, movies);

    if (movies.size() == 0) {
      throw new NoSuchElementException("No results found.");
    }
    return movies;
  }

  /**
   * Recursive helper method to lookup the list of movies given their year of production and a
   * minimum value of rating
   * 
   * @param year      the year we would like to search for a movie
   * @param rating    the minimum rating we would like to search for a movie
   * @param movieList an arraylist which stores the list of movies matching our search criteria
   *                  (exact year of production and having at least the provided rating) within the
   *                  subtree rooted at current
   * @param current   "root" of the subtree we are looking for a match to find within it.
   */
  protected void lookupHelper(int year, double rating, BSTNode<Movie> current,
      ArrayList<Movie> movieList) {
    if (current == null) {//base case to end search
      return;
    }
    if (current.getData().getYear() == year && current.getData().getRating() >= rating) {
      movieList.add(current.getData());//Adds movie if fits requirements
    }
    //Searches through all of the list from the root and down
    lookupHelper(year, rating, current.getLeft(), movieList);
    lookupHelper(year, rating, current.getRight(), movieList);
  }
}
