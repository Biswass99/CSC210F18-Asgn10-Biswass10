package com.example.roomwordsdef;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by salil on 12/5/18.
 */

// WordDefViewModel provides data to the user interface
// A ViewModel holds the UI data in a way that is not destroyed by configuration changes.
// WordDefViewModel is responsible for holding and processing all the data needed for the UI.

public class WordDefViewModel extends AndroidViewModel {
    private WordDefRepository mRepository;

    private LiveData<List<WordDef>> mAllWordDefs;

    // constructor
    public WordDefViewModel (Application application) {
        super(application);
        mRepository = new WordDefRepository(application);
        mAllWordDefs = mRepository.getAllWordDefs();
    }

    // getter method gets all the words
    LiveData<List<WordDef>> getAllWordDefs() {

        return mAllWordDefs;
    }
    // insert() method calls the insert() method of WordDefRepository class
    public void insert(WordDef wordDef) {

        mRepository.insert(wordDef);
    }
}
