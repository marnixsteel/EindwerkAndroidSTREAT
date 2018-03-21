package steel.marnix.eindwerkandroidstreats.dao;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import steel.marnix.eindwerkandroidstreats.R;

/**
 * Created by michaelhollants on 19/03/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private String[] mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView tvArtistName;
            public ImageView ivArt;

            public ViewHolder(View v) {
                super(v);
                tvArtistName = v.findViewById(R.id.tv_cv_artist_name);
                ivArt = v.findViewById(R.id.iv_listcontent_foto);
            }
        }


    public CardAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View rView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_content, parent, false);
        return new ViewHolder(rView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvArtistName.setText(mDataset[position]);
        Picasso.get().load(R.mipmap.streat_icons_round).into(holder.ivArt);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

