package com.example.roomwordsdef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordDefActivity extends AppCompatActivity {

    public static final String WORD_REPLY =
            "com.example.android.roomwordsdef.WORDREPLY";

    public static final String DEFINITION_REPLY =
            "com.example.android.roomwordsdef.DEFINITIONREPLY";

    private EditText mEditWordView;
    private EditText mEditDefinitionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word_def);

        // connect 2 EditText to word and definition
        mEditWordView = findViewById(R.id.edit_word);
        mEditDefinitionView = findViewById(R.id.edit_definition);

        final Button button = findViewById(R.id.button_save);

        // button click listener
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent replyIntent = new Intent();

                // checks if EditText for word or definition is empty.
                // If either or both are empty, display message on toast about entry not saved.
                if ((TextUtils.isEmpty(mEditWordView.getText())) || (TextUtils.isEmpty(mEditDefinitionView.getText()))){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    // If neither is empty, send the word back to MainActivity
                    String word = mEditWordView.getText().toString();
                    String definition = mEditDefinitionView.getText().toString();
                    // send word and definition back to MainActivity
                    replyIntent.putExtra(WORD_REPLY, word);
                    replyIntent.putExtra(DEFINITION_REPLY, definition);
                    setResult(RESULT_OK, replyIntent);
                }


                finish();
            }
        });
    }
}
