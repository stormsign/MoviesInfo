package com.example.storm.moviesinfo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.presenter.IMovieListPresenter;
import com.example.storm.moviesinfo.presenter.impl.MovieListPresenterImpl;
import com.example.storm.moviesinfo.view.adapter.MovieListAdapter;
import com.example.storm.moviesinfo.view.iview.IMovieListFragment;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.HeaderFooterWrapper;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.MyRecyclerView;
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
    MyRecyclerView mMovieList;
    private List<Visitor> list;
    private MovieListAdapter adapter;
    private HeaderFooterWrapper wrapper;

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
        ButterKnife.bind(this, view);
        list = new ArrayList<>();

        mMovieList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieListAdapter(list);
        wrapper = new HeaderFooterWrapper(adapter);
        wrapper.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.item_listfooter, mMovieList, false));
        mMovieList.setAdapter(wrapper);

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
        list.clear();
        list.addAll(movieList);
        wrapper.notifyDataSetChanged();
    }

    @Override
    public void onLoadFailed(int errCode, String msg) {

    }
}
