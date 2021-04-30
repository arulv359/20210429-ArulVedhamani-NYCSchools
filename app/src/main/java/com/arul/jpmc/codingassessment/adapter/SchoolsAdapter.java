package com.arul.jpmc.codingassessment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arul.jpmc.codingassessment.model.HighSchool;
import com.arul.jpmc.codingassessment.network.RecyclerViewClickListener;
import com.arul.jpmc.codingassessment.R;

import java.util.List;

public class SchoolsAdapter extends RecyclerView.Adapter<SchoolsAdapter.SchoolsViewHolder> {

    private List<HighSchool> dataList;
    private Context context;

    public SchoolsAdapter(Context context, List<HighSchool> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class SchoolsViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtTitle;
        TextView txtOverview;
        TextView txtLocation;
        TextView txtPhone;
        TextView txtEmail;


        SchoolsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.title);
            txtOverview = mView.findViewById(R.id.overview);
            txtLocation = mView.findViewById(R.id.location);
            txtPhone = mView.findViewById(R.id.phone);
            txtEmail = mView.findViewById(R.id.email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((RecyclerViewClickListener) context).OnSchoolSelected(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public SchoolsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new SchoolsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchoolsViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getSchool_name());
        holder.txtOverview.setText(dataList.get(position).getOverview_paragraph());
        holder.txtLocation.setText((dataList.get(position).getLocation().split("\\("))[0]);
        holder.txtPhone.setText(dataList.get(position).getPhone_number());
        holder.txtEmail.setText(dataList.get(position).getSchool_email());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
