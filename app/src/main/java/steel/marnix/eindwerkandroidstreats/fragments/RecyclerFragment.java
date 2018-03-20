package steel.marnix.eindwerkandroidstreats.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import steel.marnix.eindwerkandroidstreats.R;
import steel.marnix.eindwerkandroidstreats.dao.CardAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends android.app.Fragment {

    private RecyclerView listRV;

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

        String[] data = {"Jan", "Piet", "Korneel"};
        CardAdapter cardAdapter = new CardAdapter(data);
        listRV.setAdapter(cardAdapter);

        return rootView;
    }

}
