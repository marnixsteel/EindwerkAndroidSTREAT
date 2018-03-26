package steel.marnix.eindwerkandroidstreats.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Random;

import steel.marnix.eindwerkandroidstreats.R;
import steel.marnix.eindwerkandroidstreats.model.FoodTruck;
import steel.marnix.eindwerkandroidstreats.model.StreetArt;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    TextView tvTitle, tvDescription;
    ImageView ivPhoto;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(StreetArt selectedObject) {
        DetailFragment fragment = new DetailFragment();

        Bundle data = new Bundle();
        data.putSerializable("object", selectedObject);
        fragment.setArguments(data);

        return fragment;
    }

    public static DetailFragment newInstance(FoodTruck selectedObject) {
        DetailFragment fragment = new DetailFragment();

        Bundle data = new Bundle();
        data.putSerializable("object", selectedObject);
        fragment.setArguments(data);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        tvTitle = rootView.findViewById(R.id.tv_title_detail);
        tvDescription = rootView.findViewById(R.id.tv_description_detail);
        ivPhoto = rootView.findViewById(R.id.iv_detail_image);

        if (getArguments().getSerializable("object") instanceof StreetArt) {
            StreetArt streetArt = (StreetArt) getArguments().getSerializable("object");
            tvTitle.setText(streetArt.getArtistName());
            tvDescription.setText(streetArt.getDescription());
        } else {
            FoodTruck foodTruck = (FoodTruck) getArguments().getSerializable("object");
            tvTitle.setText(foodTruck.getName());
            tvDescription.setText(foodTruck.getLocation());

            Random r = new Random();
            switch (r.nextInt(3)) {
                case 0:
                    ivPhoto.setImageResource(R.drawable.foodtrucklogo1);
                    break;
                case 1:
                    ivPhoto.setImageResource(R.drawable.foodtrucklogo2);
                    break;
                case 2:
                    ivPhoto.setImageResource(R.drawable.foodtrucklogo3);
                    break;
            }
        }
        return rootView;
    }
}
