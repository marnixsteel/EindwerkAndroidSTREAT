package steel.marnix.eindwerkandroidstreats.dao;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import steel.marnix.eindwerkandroidstreats.R;
import steel.marnix.eindwerkandroidstreats.fragments.DetailFragment;
import steel.marnix.eindwerkandroidstreats.model.FoodTruck;

/**
 * Created by michaelhollants on 27/03/2018.
 */

public class FoodTruckCardAdapter extends RecyclerView.Adapter<FoodTruckCardAdapter.ViewHolder> {

    private List<FoodTruck> foodTruckDataSet;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView tvName;
        public ImageView ivPhoto;
        public TextView tvLocation;
        CardView card;

        public ViewHolder(View v) {
            super(v);
            tvName = v.findViewById(R.id.tv_listcontent_name);
            ivPhoto = v.findViewById(R.id.iv_listcontent_foto);
            tvLocation = v.findViewById(R.id.tv_listcontent_description);
            card = v.findViewById(R.id.cv_content);
        }
    }

    public FoodTruckCardAdapter(List<FoodTruck> foodTruckDataSet) {
        this.foodTruckDataSet = foodTruckDataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FoodTruckCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        context = parent.getContext();
        View rView = LayoutInflater.from(context)
                .inflate(R.layout.list_content, parent, false);
        return new ViewHolder(rView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final FoodTruck ft = foodTruckDataSet.get(position);

        holder.tvName.setText(ft.getName());
        holder.tvLocation.setText(ft.getLocation());

        Random r = new Random();
        switch (r.nextInt(3)) {
            case 0:
                holder.ivPhoto.setImageResource(R.drawable.foodtrucklogo1);
                break;
            case 1:
                holder.ivPhoto.setImageResource(R.drawable.foodtrucklogo2);
                break;
            case 2:
                holder.ivPhoto.setImageResource(R.drawable.foodtrucklogo3);
                break;
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).getFragmentManager().beginTransaction().replace(R.id.main_container, DetailFragment.newInstance(ft)).addToBackStack("back").commit();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return foodTruckDataSet.size();
    }
}
