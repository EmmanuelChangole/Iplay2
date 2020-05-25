package com.hfad.iplay;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends Fragment {
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Context context;


    public LibraryFragment(Context context) {
        // Required empty public constructor
        this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view= inflater.inflate(R.layout.fragment_library, container, false);
        initWidgets(view);
        viewPagerSetUp();


        return view;
    }

    private void viewPagerSetUp()
    {
        Adapter adapter=new Adapter(getFragmentManager());
        adapter.addFragment(new SongFragment(context),"Songs");
        adapter.addFragment(new AlbumFragment(),"Album");
        adapter.addFragment(new ArtistFragment(),"Artist");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);





    }

    private void initWidgets(View view)
    {
        viewPager=view.findViewById(R.id.lib_viewPager);
        tabLayout=view.findViewById(R.id.lib_tab_layout);

    }






    public class Adapter extends FragmentPagerAdapter
    {
       List<Fragment> fragmentList=new ArrayList<>();
       List<String> titleList=new ArrayList<>();


        public Adapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        public void addFragment(Fragment fragment,String title)
        {
            fragmentList.add(fragment);
            titleList.add(title);


        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
           return titleList.get(position);
        }
    }




}
