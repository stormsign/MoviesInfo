package com.example.storm.moviesinfo.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.presenter.IMovieListPresenter;
import com.example.storm.moviesinfo.presenter.impl.MovieListPresenterImpl;
import com.example.storm.moviesinfo.view.activity.MainActivity;
import com.example.storm.moviesinfo.view.activity.MovieDetailActivity;
import com.example.storm.moviesinfo.view.adapter.MovieListAdapter;
import com.example.storm.moviesinfo.view.iview.IMovieListFragment;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.HeaderFooterWrapper;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.ItemClickListener;
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
    @BindView(R.id.contentview)
    LinearLayout contentView;

    private List<Visitor> list;
    private MovieListAdapter adapter;
    private HeaderFooterWrapper wrapper;

    private MainActivity activity;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (mMovieList.hasFooter){
                mMovieList.removeFooter();
            }
        }
    };

    public static MovieListFragment newInstance(int fragmentType){
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", fragmentType);
        fragment.setArguments(bundle);
        return fragment;
    }

    public int getDataType(){
        return dataType;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = new MovieListPresenterImpl();
        mPresenter.register(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movielist, container, false);
        ButterKnife.bind(this, view);
        list = new ArrayList<>();
//        SPUtils.saveCity(city);

        mMovieList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieListAdapter(getActivity(), list);
        wrapper = new HeaderFooterWrapper(adapter);
        wrapper.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.item_listheader, mMovieList, false));
        mMovieList.setAdapter(wrapper);
        mMovieList.setRefreshListListener(new MyRecyclerView.ListRefreshableListener() {
            @Override
            public void onListRefreshing() {
                mPresenter.loadData(dataType);
            }

            @Override
            public void onListLoadMore() {
//                测试代码
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        });
        mMovieList.setOnItemClickListener(new ItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                MovieBrief movie = (MovieBrief) list.get(position);
                Toast.makeText(getActivity(), "movie name = " + movie.getTvTitle(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), MovieDetailActivity.class)
                        .putExtra("posterUrl", movie.getIconaddress()));
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataType = getArguments().getInt("type");
        mPresenter.loadData(dataType);
        adapter.setFirstLoading(true);
    }

    @Override
    public void onAttach(Context context) {
        this.activity = (MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.unregister();
    }

    public void showPositioningDialog(){
        if (activity!=null){
            activity.showPositioningDialog();
        }
    }

    public void hidePositioningDialog(){
        if (activity!=null){
            activity.hidePositioningDialog();
        }
    }

    @Override
    public void onLoadData(List<MovieBrief> movieList) {
        adapter.setFirstLoading(false);
        list.clear();
        list.addAll(movieList);
        wrapper.notifyDataSetChanged();
        if (mMovieList.hasHeader){
            mMovieList.removeHeader();
        }
//        if (mMovieList.hasFooter){
//            mMovieList.removeFooter();
//        }
    }

    @Override
    public void onLoadFailed(int errCode, String msg) {
        mMovieList.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setHasNoData(true);
                list.clear();
                wrapper.notifyDataSetChanged();
                if (mMovieList.hasHeader){
                    mMovieList.removeHeader();
                }
            }
        }, 1000);
    }

    public void backToTop(){
        mMovieList.smoothScrollToPosition(0);
    }

    public void showLocatingDialog(String city){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.dialog_title_locating));
        builder.setMessage(getResources().getString(R.string.dialog_message_start_locating));
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (adapter!=null){
                    adapter.setFirstLoading(false);
                    onLoadFailed(0, null);
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mPresenter.getLocation();
            }
        });


    }

    public void hideLocatingDialog(){

    }

    public void onLocatingFailed(){
        Toast.makeText(getActivity(), "定位失败", Toast.LENGTH_SHORT).show();
        onLoadFailed(0, null);
    }

}
