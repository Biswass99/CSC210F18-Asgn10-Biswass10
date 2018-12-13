package com.example.roomwordsdef;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by salil on 12/3/18.
 */

@Dao
public interface WordDefDao {
    // onConflict strategy prevents crashing of App if a word is repeated
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(WordDef wordDef);

    // deletes all entries from wordDef_table.
    @Query("DELETE FROM wordDef_table")
    void deleteAll();

    // gets all words from wordDef_table. Words are listed alphabetically.
    // LiveData helps the app respond to data changes
    @Query("SELECT * from wordDef_table ORDER BY word ASC")
    LiveData<List<WordDef>> getAllWordDefs();
}
