package com.mf4z.moviekeeper;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<GenreInfo> mGenre = new ArrayList<>();
    private List<MovieInfo> mMovies = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            ourInstance.initializeGenre();
            ourInstance.initializeExampleMovies();
        }
        return ourInstance;
    }

    public String getCurrentUserName() {
        return "Jim Wilson";
    }

    public String getCurrentUserEmail() {
        return "jimw@jwhh.com";
    }

    public List<MovieInfo> getMovies() {
        return mMovies;
    }

    public int createNewMovie() {
        MovieInfo note = new MovieInfo(null, null, null);
        mMovies.add(note);
        return mMovies.size() - 1;
    }

    public int findMovie(MovieInfo movie) {
        for(int index = 0; index < mMovies.size(); index++) {
            if(movie.equals(mMovies.get(index)))
                return index;
        }

        return -1;
    }

    public void removeMovie(int index) {
        mMovies.remove(index);
    }

    public List<GenreInfo> getGenres() {
        return mGenre;
    }

    public GenreInfo getGenre(String id) {
        for (GenreInfo genre : mGenre) {
            if (id.equals(genre.getGenreId()))
                return genre;
        }
        return null;
    }

    public List<MovieInfo> getNotes(GenreInfo course) {
        ArrayList<MovieInfo> notes = new ArrayList<>();
        for(MovieInfo note: mMovies) {
            if(course.equals(note.getGenre()))
                notes.add(note);
        }
        return notes;
    }

    public int getMovieCount(GenreInfo course) {
        int count = 0;
        for(MovieInfo note: mMovies) {
            if(course.equals(note.getGenre()))
                count++;
        }
        return count;
    }

    private DataManager() {
    }

    //region Initialization code

    private void initializeGenre() {
        mGenre.add(initializeCourse1());
        mGenre.add(initializeCourse2());
        mGenre.add(initializeCourse3());
        mGenre.add(initializeCourse4());
    }

    public void initializeExampleMovies() {
        final DataManager dm = getInstance();

        GenreInfo course = dm.getGenre("android_intents");
        course.getModule("android_intents_m01").setComplete(true);
        course.getModule("android_intents_m02").setComplete(true);
        course.getModule("android_intents_m03").setComplete(true);
        mMovies.add(new MovieInfo(course, "Dynamic intent resolution",
                "Wow, intents allow components to be resolved at runtime"));
        mMovies.add(new MovieInfo(course, "Delegating intents",
                "PendingIntents are powerful; they delegate much more than just a component invocation"));

        course = dm.getGenre("android_async");
        course.getModule("android_async_m01").setComplete(true);
        course.getModule("android_async_m02").setComplete(true);
        mMovies.add(new MovieInfo(course, "Service default threads",
                "Did you know that by default an Android Service will tie up the UI thread?"));
        mMovies.add(new MovieInfo(course, "Long running operations",
                "Foreground Services can be tied to a notification icon"));

        course = dm.getGenre("java_lang");
        course.getModule("java_lang_m01").setComplete(true);
        course.getModule("java_lang_m02").setComplete(true);
        course.getModule("java_lang_m03").setComplete(true);
        course.getModule("java_lang_m04").setComplete(true);
        course.getModule("java_lang_m05").setComplete(true);
        course.getModule("java_lang_m06").setComplete(true);
        course.getModule("java_lang_m07").setComplete(true);
        mMovies.add(new MovieInfo(course, "Parameters",
                "Leverage variable-length parameter lists"));
        mMovies.add(new MovieInfo(course, "Anonymous classes",
                "Anonymous classes simplify implementing one-use types"));

        course = dm.getGenre("java_core");
        course.getModule("java_core_m01").setComplete(true);
        course.getModule("java_core_m02").setComplete(true);
        course.getModule("java_core_m03").setComplete(true);
        mMovies.add(new MovieInfo(course, "Compiler options",
                "The -jar option isn't compatible with with the -cp option"));
        mMovies.add(new MovieInfo(course, "Serialization",
                "Remember to include SerialVersionUID to assure version compatibility"));
    }

    private GenreInfo initializeCourse1() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("android_intents_m01", "Android Late Binding and Intents"));
        modules.add(new ModuleInfo("android_intents_m02", "Component activation with intents"));
        modules.add(new ModuleInfo("android_intents_m03", "Delegation and Callbacks through PendingIntents"));
        modules.add(new ModuleInfo("android_intents_m04", "IntentFilter data tests"));
        modules.add(new ModuleInfo("android_intents_m05", "Working with Platform Features Through Intents"));

        return new GenreInfo("android_intents", "Android Programming with Intents", modules);
    }

    private GenreInfo initializeCourse2() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("android_async_m01", "Challenges to a responsive user experience"));
        modules.add(new ModuleInfo("android_async_m02", "Implementing long-running operations as a service"));
        modules.add(new ModuleInfo("android_async_m03", "Service lifecycle management"));
        modules.add(new ModuleInfo("android_async_m04", "Interacting with services"));

        return new GenreInfo("android_async", "Android Async Programming and Services", modules);
    }

    private GenreInfo initializeCourse3() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("java_lang_m01", "Introduction and Setting up Your Environment"));
        modules.add(new ModuleInfo("java_lang_m02", "Creating a Simple App"));
        modules.add(new ModuleInfo("java_lang_m03", "Variables, Data Types, and Math Operators"));
        modules.add(new ModuleInfo("java_lang_m04", "Conditional Logic, Looping, and Arrays"));
        modules.add(new ModuleInfo("java_lang_m05", "Representing Complex Types with Classes"));
        modules.add(new ModuleInfo("java_lang_m06", "Class Initializers and Constructors"));
        modules.add(new ModuleInfo("java_lang_m07", "A Closer Look at Parameters"));
        modules.add(new ModuleInfo("java_lang_m08", "Class Inheritance"));
        modules.add(new ModuleInfo("java_lang_m09", "More About Data Types"));
        modules.add(new ModuleInfo("java_lang_m10", "Exceptions and Error Handling"));
        modules.add(new ModuleInfo("java_lang_m11", "Working with Packages"));
        modules.add(new ModuleInfo("java_lang_m12", "Creating Abstract Relationships with Interfaces"));
        modules.add(new ModuleInfo("java_lang_m13", "Static Members, Nested Types, and Anonymous Classes"));

        return new GenreInfo("java_lang", "Java Fundamentals: The Java Language", modules);
    }

    private GenreInfo initializeCourse4() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("java_core_m01", "Introduction"));
        modules.add(new ModuleInfo("java_core_m02", "Input and Output with Streams and Files"));
        modules.add(new ModuleInfo("java_core_m03", "String Formatting and Regular Expressions"));
        modules.add(new ModuleInfo("java_core_m04", "Working with Collections"));
        modules.add(new ModuleInfo("java_core_m05", "Controlling App Execution and Environment"));
        modules.add(new ModuleInfo("java_core_m06", "Capturing Application Activity with the Java Log System"));
        modules.add(new ModuleInfo("java_core_m07", "Multithreading and Concurrency"));
        modules.add(new ModuleInfo("java_core_m08", "Runtime Type Information and Reflection"));
        modules.add(new ModuleInfo("java_core_m09", "Adding Type Metadata with Annotations"));
        modules.add(new ModuleInfo("java_core_m10", "Persisting Objects with Serialization"));

        return new GenreInfo("java_core", "Java Fundamentals: The Core Platform", modules);
    }
    //endregion

}
