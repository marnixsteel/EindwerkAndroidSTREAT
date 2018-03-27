package steel.marnix.eindwerkandroidstreats.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import steel.marnix.eindwerkandroidstreats.R;
import steel.marnix.eindwerkandroidstreats.dao.FoodTruckCardAdapter;
import steel.marnix.eindwerkandroidstreats.dao.StreetArtCardAdapter;
import steel.marnix.eindwerkandroidstreats.model.FoodTruck;
import steel.marnix.eindwerkandroidstreats.model.StreatDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends android.app.Fragment {

    private RecyclerView listRV;
    TextView tvTitle, tvDescription;
    ImageView ivPhoto;

    public RecyclerFragment() {
    }

    public static RecyclerFragment newInstance(){
        return new RecyclerFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);

        listRV = rootView.findViewById(R.id.rv_list);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        listRV.setLayoutManager(lm);

        //maak instantie van adapter
        StreetArtCardAdapter cardAdapter = new StreetArtCardAdapter(StreatDatabase.getInstance(getActivity()).getArtDAO().getAllStreetArt());
        FoodTruckCardAdapter cardAdapterFood = new FoodTruckCardAdapter(StreatDatabase.getInstance(getActivity()).getFoodDAO().getAllFoodTrucks());
        //setadapter op uw recyclerview
        listRV.setAdapter(cardAdapter);
        listRV.setAdapter(cardAdapterFood);

        return rootView;
    }

}
