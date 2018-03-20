package com.guessMovie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static Scanner scanner;
    private static File file = new File("src/com/guessMovie/List_of_movies.txt");

    public static void main(String[] args) {
        String randomMovieHidden;
        int numOfMovies, randomNum;
        String randomMovie;
        List<Character> wrongInputArray = new ArrayList<Character>();
        List<String> moviesTitle = split(file);
        numOfMovies = moviesTitle.size();
        randomNum = (int) (Math.random() * numOfMovies);


        //selecting randomly a movie title in the list

        randomMovie = moviesTitle.get(randomNum);
        System.out.println(randomMovie);
        randomMovieHidden = randomMovie.replaceAll("[a-zA-Z]", "*");
        StringBuilder randomMovieDisplay = new StringBuilder(randomMovieHidden);

        System.out.println("I have randomly choose a Movie. Try to guess it...\n\nThis is the movie title: " + randomMovieHidden + "\n");
        int wrongGuess = 0;
        while (wrongGuess < 10) {
            System.out.print("\nYou have guessed " + wrongGuess + " wrong letter(s)\nGuess a letter:");
            scanner = new Scanner(System.in);
            try {
                Character letterInput = scanner.next(".").charAt(0);
                if (randomMovie.contains(letterInput.toString().toLowerCase())) {
                    System.out.println("yep it exist in the string");


                    System.out.println(repeatedCharIndex(randomMovie));
                    List<Integer> charIndexRep = repeatedCharIndex(randomMovie);
                    if (charIndexRep.isEmpty()) {
                        System.out.println("No duplicate");
                        int index = randomMovie.indexOf(String.valueOf(letterInput));
                        randomMovieDisplay.setCharAt(index, letterInput);
                        System.out.println("\nThe movie title is: " + randomMovieDisplay);
                        String randomMovieDisplayString = randomMovieDisplay.toString();
                        for(int i=0;i<randomMovieDisplayString.length();i++){
                            if(!randomMovieDisplayString.contains("*")){
                                System.out.println("\nCongratulation...you win the game");
                                return;
                            }
                        }

                    }
                    else {
                        System.out.println("There is duplicated");

                        //things to be done
                    }

                } else {
                    System.out.println("nope");


                    if (!wrongInputArray.contains(letterInput)) {
                        wrongInputArray.add(letterInput);
                        wrongGuess++;

                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a character");
            }
        }
        System.out.println("Sorry you have lost the game.");
    }

    private static List<String> split(File file) {
        List<String> moviesTitle = new ArrayList<String>();


        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        }

        while (scanner.hasNextLine()) {
            //adding each line to the list
            moviesTitle.add(scanner.nextLine());
        }
        Iterator<String> iterator = moviesTitle.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("\n====================");
        //returning the list with movies added
        return moviesTitle;
    }

    private static List<Integer> repeatedCharIndex(String movie) {
        List<Integer> charIndexRepeated = new ArrayList<Integer>();
        int countRepeated = 0;
        char[] movieCharArray = movie.toCharArray();
        for (int i = 0; i < movie.length(); i++) {
            for (int j = i + 1; j < movie.length(); j++) {
                if (movieCharArray[i] == movieCharArray[j] && movieCharArray[i] != 32) {
                    countRepeated++;
                    charIndexRepeated.add(movie.indexOf(movie.charAt(j)));
                    break;
                }
            }
        }
        Set<Integer> charIndexRepeatedNoDuplicate = new LinkedHashSet<Integer>(charIndexRepeated);
        charIndexRepeated.clear();
        charIndexRepeated.addAll(charIndexRepeatedNoDuplicate);
        return charIndexRepeated;


    }
}

