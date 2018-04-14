package com.jonno1809.ucparkingavailability;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarParkDetailsFragment.OnCarParkShapeSelectedListener} interface
 * to handle interaction events.
 * Use the {@link CarParkDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarParkDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private CarPark carPark;

    private OnCarParkShapeSelectedListener mListener;

    public CarParkDetailsFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CarParkDetailsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CarParkDetailsFragment newInstance(String param1, String param2) {
//        CarParkDetailsFragment fragment = new CarParkDetailsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            carPark = getArguments().getParcelable("carPark");
        }
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_park_details, container, false);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvFree = view.findViewById(R.id.tvFree);
        TextView tvOccupied = view.findViewById(R.id.tvOccupied);
        TextView tvType = view.findViewById(R.id.tvType);
        TextView tvCapacity = view.findViewById(R.id.tvCapacity);

        if (carPark != null) {
            tvName.setText(getString(R.string.car_park_name, carPark.getName()));
            tvFree.setText(getString(R.string.car_park_free, carPark.getFree()));
            tvOccupied.setText(getString(R.string.car_park_occupied, carPark.getOccupied()));
            tvType.setText(getString(R.string.car_park_type, carPark.getType()));
            tvCapacity.setText(getString(R.string.car_park_capactity, carPark.getCapacity()));
        } else {
            tvName.setText(getString(R.string.car_park_name, "N/A"));
            tvFree.setText(getString(R.string.car_park_free, 0));
            tvOccupied.setText(getString(R.string.car_park_occupied, 0));
            tvType.setText(getString(R.string.car_park_type, "N/A"));
            tvCapacity.setText(getString(R.string.car_park_capactity, 0));
        }
        view.setBackgroundColor(Color.WHITE);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCarParkShapeSelected(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCarParkShapeSelectedListener) {
            mListener = (OnCarParkShapeSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCarParkShapeSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCarParkShapeSelectedListener {
        // TODO: Update argument type and name
        void onCarParkShapeSelected(Uri uri);
    }
}
