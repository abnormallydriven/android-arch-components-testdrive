package com.abnormallydriven.architecturecomponentspost.usermeasurements;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.abnormallydriven.architecturecomponentspost.common.data.entities.Measurement;
import com.abnormallydriven.architecturecomponentspost.databinding.AdapterMeasurementItemBinding;

import javax.inject.Inject;

class UserMeasurementsAdapter extends RecyclerView.Adapter<UserMeasurementsAdapter.UserMeasurementViewHolder>{

    @NonNull
    private Measurement[] measurements;

    @Inject
    UserMeasurementsAdapter(){
        measurements = new Measurement[0];
    }

    void update(@NonNull Measurement[] updatedMeasurements){
        measurements = updatedMeasurements;
        //TODO diff util
        notifyDataSetChanged();
    }

    @Override
    public UserMeasurementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterMeasurementItemBinding binding = AdapterMeasurementItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new UserMeasurementViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserMeasurementViewHolder holder, int position) {
        holder.onBind(measurements[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class UserMeasurementViewHolder extends RecyclerView.ViewHolder{

        private final AdapterMeasurementItemBinding binding;

        public UserMeasurementViewHolder(AdapterMeasurementItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(Measurement measurement){
            binding.setMeasurement(measurement);
        }
    }
}
