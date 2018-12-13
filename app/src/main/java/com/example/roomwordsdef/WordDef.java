package com.example.roomwordsdef;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Created by salil on 12/3/18.
 */

@Entity(tableName = "wordDef_table")
// wordDef class has 2 attributes (word and definition)
// database table is called wordDef_table with 2 columns word and definition
// primary key is word
// has 2 getter methods for word and definition
public class WordDef {
    // make word column the primary key
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    // make second column as definition column
    @ColumnInfo(name = "definition")
    private String mDefinition;

    //constructor with 2 parameters
    public WordDef(@NonNull String word, String definition)
    {
        this.mWord = word;
        this.mDefinition = definition;
    }
    // getter method for word
    public String getWord(){

        return this.mWord;
    }
    // getter method for definition
    public String getDefinition(){

        return this.mDefinition;
    }


}
