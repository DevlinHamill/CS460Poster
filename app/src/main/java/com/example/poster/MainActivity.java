package com.example.poster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * CS 460 Poster
 * @author Devlin Hamill
 */

public class MainActivity extends AppCompatActivity implements PostersListener{
    /**
     * Creates a variable to hold the watch list button
     */
    private Button buttonAddToWatchlist;

    /**
     * helps create the GUI
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /**
         * creates the GUI view of the poster
         */
        RecyclerView posterRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchList);

        //prepare data
        /**
         * holds all posters on the app
         */
        List<Poster> posterList = new ArrayList<>();
        /**
         * Creates a poster object for the movie up and holds all info related to said movie
         */
        Poster up = new Poster();
        up.image = R.drawable.up;
        up.name = "Up";
        up.createdBy = "Pixar";
        up.rating = 4.8f;
        up.story = "Old man goes into flying house";
        posterList.add(up);
        /**
         * Creates a poster object for the movie big hero and holds all info related to said movie
         */
        Poster bighero = new Poster();
        bighero.image = R.drawable.bighero;
        bighero.name = "Big Hero";
        bighero.createdBy = "Disney";
        bighero.rating = 4.6f;
        bighero.story = "Robot saves the day!";
        posterList.add(bighero);
        /**
         * Creates a poster object for the movie venom and holds all info related to said movie
         */
        Poster venom = new Poster();
        venom.image = R.drawable.venom;
        venom.name = "Venom: the Last dance";
        venom.createdBy = "Marvel";
        venom.rating = 4f;
        venom.story = "Till death do they part";
        posterList.add(venom);

        /**
         * Creates a poster object for the movie aquaman and holds all info related to said movie
         */
        Poster aquaman = new Poster();
        aquaman.image = R.drawable.aquaman;
        aquaman.name = "Aquaman and the lost kingdom";
        aquaman.createdBy = "Warner Bros";
        aquaman.rating = 3f;
        aquaman.story = "Aquaman and his brother journey to stop a great evil";
        posterList.add(aquaman);

        /**
         * Creates a poster object for the movie batman and holds all info related to said movie
         */
        Poster batman = new Poster();
        batman.image = R.drawable.batmanbegins;
        batman.name = "Batman Begins";
        batman.createdBy = "Warner Bros";
        batman.rating = 4.7f;
        batman.story = "How the dark night starts his journey";
        posterList.add(batman);

        /**
         * Creates a poster object for the movie beetlejuice and holds all info related to said movie
         */
        Poster beetlejuice = new Poster();
        beetlejuice.image = R.drawable.beetlejuicebeetlejuice;
        beetlejuice.name = "Beetlejuice: Beetlejuice";
        beetlejuice.createdBy = "Tim Burton";
        beetlejuice.rating = 3.5f;
        beetlejuice.story = "The ghost with the most is back!";
        posterList.add(beetlejuice);

        /**
         * Creates a poster object for the movie incredibles and holds all info related to said movie
         */
        Poster incredibles = new Poster();
        incredibles.image = R.drawable.incredibles;
        incredibles.name = "The Incredibles";
        incredibles.createdBy = "Pixar";
        incredibles.rating = 5f;
        incredibles.story = "The incredibles save the day!";
        posterList.add(incredibles);

        /**
         * Creates a poster object for the movie it and holds all info related to said movie
         */
        Poster it = new Poster();
        it.image = R.drawable.it;
        it.name = "IT";
        it.createdBy = "Warner Bros";
        it.rating = 3.5f;
        it.story = "An evil clown called pennywise has come for blood";
        posterList.add(it);

        /**
         * Creates a poster object for the movie howls moving castle and holds all info related to said movie
         */

        Poster movingcastle = new Poster();
        movingcastle.image = R.drawable.movingcastle;
        movingcastle.name = "Howls Moving Castle";
        movingcastle.createdBy = "Studio Ghibli";
        movingcastle.rating = 4.8f;
        movingcastle.story = "A young women tries to break the curse of old age";
        posterList.add(movingcastle);

        /**
         * Creates a poster object for the movie wildrobot and holds all info related to said movie
         */
        Poster wildrobot = new Poster();
        wildrobot.image = R.drawable.wildrobot;
        wildrobot.name = "The Wildrobot";
        wildrobot.createdBy = "Dreamworks";
        wildrobot.rating = 4.9f;
        wildrobot.story = "A robot lost in the wilderness";
        posterList.add(wildrobot);

        /**
         * connects the main method to the poster adapter object in order to retrieve xml GUI information.
         */
        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        posterRecyclerView.setAdapter(posterAdapter);

        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();

                for(int i =0; i<selectPosters.size(); i++){
                    if(i==0){
                        posterNames.append(selectPosters.get(i).name);
                    }else{
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this,posterNames.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * sets the watchlist button visablity based on if the user selects it or not
     * @param isSelected updates a true or false statement if the user clicks a poster or not.
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        }else{
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}