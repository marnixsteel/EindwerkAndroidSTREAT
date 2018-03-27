package steel.marnix.eindwerkandroidstreats.fragments;


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
 */
public class ArtRecyclerFragment extends android.app.Fragment {

    private RecyclerView artListRv;

    public ArtRecyclerFragment() {
    }

    public static ArtRecyclerFragment newInstance(){
        return new ArtRecyclerFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);

        artListRv = rootView.findViewById(R.id.rv_list);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        artListRv.setLayoutManager(lm);

        //maak instantie van adapter
        StreetArtCardAdapter cardAdapter = new StreetArtCardAdapter(StreatDatabase.getInstance(getActivity()).getArtDAO().getAllStreetArt());

        //setadapter op uw recyclerview
        artListRv.setAdapter(cardAdapter);


        return rootView;
    }
}
