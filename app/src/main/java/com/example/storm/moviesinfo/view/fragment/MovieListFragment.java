package com.example.storm.moviesinfo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.presenter.IMovieListPresenter;
import com.example.storm.moviesinfo.presenter.impl.MovieListPresenterImpl;
import com.example.storm.moviesinfo.view.iview.IMovieListFragment;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.adapter.MultiTypeAdapter;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.model.Visitor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends Fragment implements IMovieListFragment{

    private String fragmentName;
    private IMovieListPresenter mPresenter;
    private int dataType;
    private String city = "深圳";

    @BindView(R.id.movielist)
    RecyclerView mMovieList;
    private List<Visitor> list;

    public static MovieListFragment newInstance(int fragmentType){
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", fragmentType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = new MovieListPresenterImpl();
        mPresenter.register(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movielist, container, false);
        ButterKnife.bind(view);
        list = new ArrayList<>();
        MultiTypeAdapter adapter = new MultiTypeAdapter(list);
        mMovieList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataType = getArguments().getInt("type");
        mPresenter.loadData(city, dataType);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.unregister();
    }

    @Override
    public void onLoadData(List<MovieBrief> movieList) {

    }

    @Override
    public void onLoadFailed(int errCode, String msg) {

    }
}
