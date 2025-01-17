package com.example.poster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * CS 460 Poster
 * @author Devlin Hamill
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{

    /**
     * Returns the GUI view of all poster components
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster,parent, false));
    }

    /**
     * Binds the current poster object on the list to its proper position.
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     * Retrieves the amount of poster that are in use.
     * @return the size of the poster list
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    /**
     * Creates a variable that holds all poster objects in this instance
     */
    private List<Poster> posterList;

    /**
     * allows for both the poster list and the poster listener to be updated properly
     * @param posterList contains all poster objects
     * @param postersListener checks if the poster has been selected
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    /**
     * creates a variable that checks if the current list of posters have been selected
     */
    private PostersListener postersListener;

    /**
     * returns the select posters on the GUI if they have been selected
     * @return any selected posters as a list.
     */
    public List<Poster> getSelectedPosters() {
        /**
         * will contain the list of selected posters
         */
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    /**
     * holds the current GUI components of the poster
     */
    class PosterViewHolder extends RecyclerView.ViewHolder{
        /**
         * holds the layout constraints of the poster GUI
         */
        ConstraintLayout layoutPosters;
        /**
         * holds background of the GUI.
         */
        View viewBackground;
        /**
         * contains the image to be displayed on the poster object
         */
        RoundedImageView imagePoster;
        /**
         * Holds the name of the movie
         */
        TextView textName;
        /**
         * holds the author of the movie
         */
        TextView textCreatedBy;
        /**
         * holds the description of a movie
         */
        TextView textStory;
        /**
         * holds the current rating of the movie
         */
        RatingBar ratingBar;
        /**
         * Holds the GUI information that tells the user if the poster has been selected.
         */
        ImageView imageSelected;

        /**
         * connects the GUI information to the XML information by object ID.
         * @param itemView takes in a GUI container to update all relative information.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutposters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * Binds the poster GUI informate to their relative data.
         * @param poster the current poster object
         */
        void bindPosters(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            if(poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            }else{
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if(getSelectedPosters().isEmpty()){
                            postersListener.onPosterAction(false);
                        }

                    }else{
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected=true;
                        postersListener.onPosterAction(true);

                    }

                }
            });
        }
    }
}
