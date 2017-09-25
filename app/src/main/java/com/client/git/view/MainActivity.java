package com.client.git.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.client.git.R;
import com.client.git.model.vo.Organization;
import com.client.git.model.vo.Repository;
import com.client.git.view.fragment.OrgListFragment;
import com.client.git.view.fragment.RepoListFragment;

import butterknife.ButterKnife;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private static String TAG_ORG = "TAG_ORG";
    private static String TAG_REPO = "TAG_REPO";

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG_ORG);
        if (fragment == null) replaceFragment(new OrgListFragment(), false, TAG_ORG);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack, String tug) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, tug);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void startRepoListFragment(Organization organization) {
        replaceFragment(RepoListFragment.newInstance(organization), true, TAG_REPO);
    }

}
