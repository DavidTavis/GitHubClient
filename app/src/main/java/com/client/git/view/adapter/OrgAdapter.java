package com.client.git.view.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.client.git.R;
import com.client.git.model.vo.Organization;
import com.client.git.presenter.OrgPresenter;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.animators.internal.ViewHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TechnoA on 22.09.2017.
 */

public class OrgAdapter extends UltimateViewAdapter<RecyclerView.ViewHolder> {

    private OrgPresenter presenter;
    private Picasso mPicasso;
    private List<Organization> organizationList;
    private Interpolator interpolator = new LinearInterpolator();
    private int lastPosition = 5;
    private final int ANIMATION_DURATION = 300;

    public OrgAdapter(Context context, List<Organization> list, OrgPresenter presenter) {
        this.organizationList = list;
        this.mPicasso = Picasso.with(context);
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new UltimateRecyclerviewViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.org_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return organizationList.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

//        if (position < getItemCount()
//                && (customHeaderView != null ? position <= organizationList.size() : position < organizationList.size())
//                && (customHeaderView == null || position > 0)) {
//            Organization organization;
//            organization = organizationList.get(customHeaderView != null ? position - 1 : position);
        Organization organization;
            organization = organizationList.get(position);
            // Set data to the view
            ((ViewHolder) holder).textName.setText(organization.getName());
            ((ViewHolder) holder).textLocation.setText(organization.getLocation());

            SpannableString content = new SpannableString(organization.getBlog());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            ((ViewHolder) holder).textBlog.setText(content);
            mPicasso.load(organization.getAvatar()).placeholder(R.mipmap.empty_photo).error(R.mipmap.empty_photo).into(((ViewHolder) holder).imgAvatar);

            ((ViewHolder) holder).orgItemContainer.setOnClickListener(v -> presenter.clickOrganization(organization));
//        }

//        if (position > lastPosition) {
//            // Add animation to the item
//            for (Animator anim : getAdapterAnimations(holder.itemView,
//                    AdapterAnimationType.SlideInLeft)) {
//                anim.setDuration(ANIMATION_DURATION).start();
//                anim.setInterpolator(interpolator);
//            }
//
//        } else {
//            ViewHelper.clear(holder.itemView);
//        }
//        lastPosition = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    public static class ViewHolder extends UltimateRecyclerviewViewHolder {
        LinearLayout orgItemContainer;
        TextView textName;
        TextView textLocation;
        TextView textBlog;
        ImageView imgAvatar;

        public ViewHolder(View v) {
            super(v);
            orgItemContainer = (LinearLayout) itemView.findViewById(R.id.org_item_container);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            textName = (TextView) itemView.findViewById(R.id.tv_name);
            textLocation = (TextView) itemView.findViewById(R.id.tv_location);
            textBlog = (TextView) itemView.findViewById(R.id.tv_blog);
        }
    }

    public void setOrgList(List<Organization> list) {
        this.organizationList = list;
        notifyDataSetChanged();
    }

}