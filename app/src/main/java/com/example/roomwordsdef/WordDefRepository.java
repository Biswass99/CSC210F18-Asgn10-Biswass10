package com.example.roomwordsdef;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by salil on 12/4/18.
 */

// Repository manages query threads

// Repository implements the logic for deciding whether to fetch data from a network
// or use results cached in the local database.

public class WordDefRepository {
    private WordDefDao mWordDefDao;
    private LiveData<List<WordDef>> mAllWordDefs;

    // constructor
    WordDefRepository(Application application) {
        WordDefRoomDatabase db = WordDefRoomDatabase.getDatabase(application);
        mWordDefDao = db.wordDefDao();
        mAllWordDefs = mWordDefDao.getAllWordDefs();
    }

    LiveData<List<WordDef>> getAllWordDefs() {

        return mAllWordDefs;
    }

    public void insert (WordDef wordDef) {

        new insertAsyncTask(mWordDefDao).execute(wordDef);
    }

    private static class insertAsyncTask extends AsyncTask<WordDef, Void, Void> {

        private WordDefDao mAsyncTaskDao;

        insertAsyncTask(WordDefDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final WordDef... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }// end class insertAsyncTask
}// end class WordDefRepository
