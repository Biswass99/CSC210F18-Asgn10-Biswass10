package com.example.roomwordsdef;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by salil on 12/5/18.
 */

// WordDefListAdapter that extends RecyclerView.Adapter.
// The adapter caches data and populates the RecyclerView with it.
// Inner class WordDefViewHolder holds and manages a view for one list item.

public class WordDefListAdapter extends RecyclerView.Adapter<WordDefListAdapter.WordDefViewHolder>{

    private final LayoutInflater mInflater;
    private List<WordDef> mWordDefs; // Cached copy of words and definitions


    // constructor
    WordDefListAdapter(Context context) {

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordDefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordDefViewHolder(itemView);
    }// end onCreateViewHolder


    // onBindViewHolder() method populates the view hierarchy within the ViewHolder object
    // with the data to be displayed.
    // integer parameter indicates the list item that is to be displayed

    @Override
    public void onBindViewHolder(WordDefViewHolder holder, int position) {
        if (mWordDefs != null) {
            WordDef current = mWordDefs.get(position);
            holder.wordItemView.setText(current.getWord());
            holder.defItemView.setText(current.getDefinition());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
            holder.defItemView.setText("No Definition");
        }
    }// end onBindViewHolder

    void setWordDefs(List<WordDef> wordDefs){
        mWordDefs = wordDefs;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWordDefs != null)
            return mWordDefs.size();
        else return 0;
    }

    // Inner class WordDefViewHolder holds and manages a view for one list item.

    class WordDefViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private final TextView defItemView;

        private WordDefViewHolder(View itemView) {
            super(itemView);

            // TextView1 holds the word
            wordItemView = itemView.findViewById(R.id.textView1);

            // TextView2 holds the definition
            defItemView = itemView.findViewById(R.id.textView2);
        }
    }
}
