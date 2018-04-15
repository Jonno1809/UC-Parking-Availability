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
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CARPARK_PARAM = "carPark";

    private CarPark carPark;
    private String cpName;
    private int cpFree;
    private int cpOccupied;
    private String cpType;
    private int cpCapacity;

    private OnCarParkShapeSelectedListener mListener;

    public CarParkDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of this fragment
     *
     * @param carPark The carpark object (usually retrieved from tapping a polygon on the map)
     * @return A new instance of fragment CarParkDetailsFragment.
     */

    public static CarParkDetailsFragment newInstance(CarPark carPark) {
        CarParkDetailsFragment fragment = new CarParkDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(CARPARK_PARAM, carPark);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            carPark = getArguments().getParcelable(CARPARK_PARAM);
        }

        if (carPark != null) {
            cpName = carPark.getName();
            cpFree = carPark.getFree();
            cpOccupied = carPark.getOccupied();
            cpType = carPark.getType();
            cpCapacity = carPark.getCapacity();
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
            tvName.setText(getString(R.string.car_park_name, cpName));
            tvFree.setText(getString(R.string.car_park_free, cpFree));
            tvOccupied.setText(getString(R.string.car_park_occupied, cpOccupied));
            tvType.setText(getString(R.string.car_park_type, cpType));
            tvCapacity.setText(getString(R.string.car_park_capactity, cpCapacity));
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
