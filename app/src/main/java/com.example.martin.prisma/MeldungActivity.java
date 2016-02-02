package com.example.martin.prisma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MeldungActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meldung);

        Bundle meldungBundle = getIntent().getExtras();

        TextView votes = (TextView)findViewById(R.id.textViewVotes);
        votes.setText(meldungBundle.get("rating").toString());

        TextView comment = (TextView)findViewById(R.id.textViewComment);
        comment.setText(meldungBundle.get("comment").toString());

        TextView category = (TextView)findViewById(R.id.textViewCategory);
        category.setText(meldungBundle.get("category").toString());

        TextView user_id = (TextView)findViewById(R.id.textViewUser_id);
        user_id.setText(meldungBundle.get("user_id").toString());

        TextView status = (TextView)findViewById(R.id.textViewStatus);
        status.setText(meldungBundle.get("status").toString());

        //TextView date = (TextView)findViewById(R.id.textViewDate);
        //date.setText((String) meldungBundle.get("date"));

        final ImageButton buttonUpvote = (ImageButton) findViewById(R.id.buttonUpvote);
        final ImageButton buttonDownvote = (ImageButton) findViewById(R.id.buttonDownvote);

        buttonUpvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDownvote.setAlpha(0.5f);
                buttonUpvote.setAlpha(1f);
                //TODO Rating, refresh
            }
        });

        buttonDownvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDownvote.setAlpha(1f);
                buttonUpvote.setAlpha(0.5f);
                //TODO Rating, refresh
            }
        });
    }
}
