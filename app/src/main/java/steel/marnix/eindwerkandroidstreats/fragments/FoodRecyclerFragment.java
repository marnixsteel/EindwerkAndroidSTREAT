package steel.marnix.eindwerkandroidstreats.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import steel.marnix.eindwerkandroidstreats.R;
import steel.marnix.eindwerkandroidstreats.dao.FoodTruckCardAdapter;
import steel.marnix.eindwerkandroidstreats.dao.StreetArtCardAdapter;
import steel.marnix.eindwerkandroidstreats.model.StreatDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 */
public class FoodRecyclerFragment extends android.app.Fragment {

    private RecyclerView foodListRv;

    public FoodRecyclerFragment() {
    }

    public static FoodRecyclerFragment newInstance(){
        return new FoodRecyclerFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food_recycler, container, false);

        foodListRv = rootView.findViewById(R.id.rv_foodlist);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        foodListRv.setLayoutManager(lm);

        FoodTruckCardAdapter cardAdapter = new FoodTruckCardAdapter(StreatDatabase.getInstance(getActivity()).getFoodDAO().getAllFoodTrucks());

        foodListRv.setAdapter(cardAdapter);

        return rootView;
    }
}
