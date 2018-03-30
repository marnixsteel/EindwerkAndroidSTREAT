package steel.marnix.eindwerkandroidstreats.dao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.List;

import steel.marnix.eindwerkandroidstreats.R;
import steel.marnix.eindwerkandroidstreats.fragments.DetailFragment;
import steel.marnix.eindwerkandroidstreats.model.FoodTruck;
import steel.marnix.eindwerkandroidstreats.model.StreetArt;

/**
 * Created by michaelhollants on 19/03/2018.
 */

public class StreetArtCardAdapter extends RecyclerView.Adapter<StreetArtCardAdapter.ViewHolder> {
    private List<StreetArt> streetArtDataset;
    private Context context;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // each data item is just a string in this case
            public TextView tvArtistName;
            public ImageView ivArt;
            public TextView tvArtDescription;
            CardView card;

            public ViewHolder(View v) {
                super(v);
                tvArtistName = v.findViewById(R.id.tv_listcontent_name);
                ivArt = v.findViewById(R.id.iv_listcontent_foto);
                tvArtDescription = v.findViewById(R.id.tv_listcontent_description);
                card = v.findViewById(R.id.cv_content);
                card.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                /*

                final StreetArt sa = streetArtDataset.get(getAdapterPosition());

                createViewHolder().tvArtistName.setText(sa.getArtistName());
                createViewHolder().tvLocation.setText(sa.);



                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, sa.getArtistName()), Toast.LENGTH_SHORT).show();
                        ((Activity)context).getFragmentManager().beginTransaction().replace(R.id.main_container, DetailFragment.newInstance(sa)).addToBackStack("back").commit();
                    }
                });

                */



            }
        }


    public StreetArtCardAdapter(List<StreetArt> streetArtDataset) {
        this.streetArtDataset = streetArtDataset;
        }

    // Create new views (invoked by the layout manager)
    @Override
    public StreetArtCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View rView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_content, parent, false);
        return new ViewHolder(rView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final StreetArt sa = streetArtDataset.get(position);



        holder.tvArtistName.setText(sa.getArtistName());
        Picasso.get()
                .load(sa.getImageUrl())
                .resize(200, 200)
                .into(holder.ivArt);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, sa.getArtistName(), Toast.LENGTH_SHORT).show();
                ((Activity)context).getFragmentManager().beginTransaction().replace(R.id.main_container, DetailFragment.newInstance(sa)).addToBackStack("back").commit();
            }
        });
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return streetArtDataset.size();
    }

}

