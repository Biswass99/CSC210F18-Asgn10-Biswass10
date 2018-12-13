package com.example.roomwordsdef;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by salil on 12/3/18.
 */

// this project has only one entity called WordDef.

@Database(entities = {WordDef.class}, version = 1, exportSchema = false)
public abstract class WordDefRoomDatabase extends RoomDatabase {
    //
    public abstract WordDefDao wordDefDao();
    private static WordDefRoomDatabase INSTANCE;

    // To delete all content and repopulate the database whenever the app is started,
    // we create a RoomDatabase.Callback, and it is used when the database is created.

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    public static WordDefRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordDefRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database called wordDef_database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDefRoomDatabase.class, "wordDef_database")
                              // Wipes and rebuilds instead of migrating
                              // if no Migration object.
                              // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    /**
     * Populate the database in the background using AsyncTask.
     */

    // deletes the contents of the database, then populates it with an initial list of words.
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDefDao mDao;
        // database starts with 2 words and 2 definitions
        String[] words = {"dolphin", "crocodile"};
        String[] definitions = {"marine mammal", "thick-skinned reptile"};

        PopulateDbAsync(WordDefRoomDatabase db) {
            mDao = db.wordDefDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created

            mDao.deleteAll(); // deletes all entries in the database

            // fill database with initial entries
            for (int i = 0; i <= words.length - 1; i++) {
                WordDef wordDef = new WordDef(words[i], definitions[i]);
                mDao.insert(wordDef);
            }
            return null;
        }
    }// end class PopulateDbAsync
}// end class WordDefRoomDatabase
