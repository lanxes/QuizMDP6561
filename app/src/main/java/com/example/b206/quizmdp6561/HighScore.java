package com.example.b206.quizmdp6561;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HighScore.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HighScore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighScore extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HighScore() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HighScore.
     */
    // TODO: Rename and change types and number of parameters
    public static HighScore newInstance(String param1, String param2) {
        HighScore fragment = new HighScore();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ArrayAdapter<DBHighScore> myadapter;
    appDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getActivity().getApplicationContext(),appDatabase.class,"highscoredb").build();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ArrayList<DBHighScore> data = new ArrayList<>();
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_high_score, container,false);
        lv = v.findViewById(R.id.lvHighScore);
        ArrayList<String> baru = new ArrayList<>();
        new AsyncTask<Void,Void, ArrayList<DBHighScore>>(){
            @Override
            protected ArrayList<DBHighScore> doInBackground(Void... voids) {
                ArrayList<DBHighScore> hasil = new ArrayList<>();
                hasil.addAll(Arrays.asList(db.highScoreDAO().selectAllHighScore()));
                return hasil;
            }

            @Override
            protected void onPostExecute(ArrayList<DBHighScore> dbHighScores) {
                //data = dbHighScores;
                myadapter = new ArrayAdapter<DBHighScore>(getActivity(),android.R.layout.simple_list_item_1,
                        dbHighScores);
                lv.setAdapter(myadapter);
                myadapter.notifyDataSetChanged();
            }
        }.execute();
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
